package com.zyp.erp.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.erp.dao.SysRoleUserMapper;
import com.zyp.erp.dao.SysUserMapper;
import com.zyp.erp.domain.SysRoleUser;
import com.zyp.erp.domain.SysUser;
import com.zyp.erp.formbean.back.SysUserFormBean;
import com.zyp.erp.service.SysUserService;
import com.zyp.erp.utils.BeanCopierUtil;
import com.zyp.erp.utils.MenuContext;
import com.zyp.erp.utils.PasswordUtil;
import com.zyp.erp.vo.back.SysUserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private MenuContext menuContext;

    @Override
    public SysUserVO getById(Integer id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);

        if ( sysUser != null ) {
            SysUserVO vo = new SysUserVO();
            BeanCopierUtil.copy(sysUser, vo);
            return vo;
        }

        return null;
    }

    @Override
    public PageInfo<SysUserVO> getPageInfo(SysUserFormBean query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<SysUserVO> list = sysUserMapper.listPage(query);
        return new PageInfo<>(list);
    }

    @Transactional
    @Override
    public void saveOrUpdate(SysUserVO userVO) {
        SysUser user = new SysUser();
        BeanCopierUtil.copy(userVO, user);

        if (null != user.getId() && -1 != user.getId()) {

            user.setUpdateTime(new Date());

            sysUserMapper.updateByPrimaryKeySelective(user);

            // 删除用户-角色关系.
            Example example = new Example(SysRoleUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("user_id=", user.getId());
            sysRoleUserMapper.deleteByExample(example);

        } else {

            user.setId(null);
            user.setAddTime(new Date());
            user.setUpdateTime(new Date());
            user.setPassword(PasswordUtil.encryptPassword(user.getPassword(), user.getUserName()));

            sysUserMapper.insertSelective(user);
        }

        // 简历用户-角色关系
        String roleIds = userVO.getRoleIds();
        if ( StringUtils.isNotEmpty(roleIds) ) {
            String[] roleIdStrArray = roleIds.split(",");
            for ( String roleIdStr : roleIdStrArray ) {
                SysRoleUser roleUser = new SysRoleUser();
                roleUser.setUserId(user.getId());
                roleUser.setRoleId(Integer.valueOf(roleIdStr));
                sysRoleUserMapper.insertSelective(roleUser);
            }
        }

        menuContext.refreshMenuList(user.getId());

    }


    @Override
    public void updateState(Integer id, Integer state) {
        SysUser user = new SysUser();
        user.setUpdateTime(new Date());
        user.setId(id);
        user.setState(state);

        sysUserMapper.updateByPrimaryKeySelective(user);

        menuContext.refreshMenuList(user.getId());
    }

    @Override
    public void delete(Integer id) {

        sysUserMapper.deleteByPrimaryKey(id);

        Example example = new Example(SysRoleUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("user_id=", id);

        sysRoleUserMapper.deleteByExample(example);

        menuContext.refreshMenuList(id);
    }
}