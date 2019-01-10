package com.zyp.erp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyp.erp.dao.SysResourceRoleMapper;
import com.zyp.erp.dao.SysRoleMapper;
import com.zyp.erp.dao.SysRoleUserMapper;
import com.zyp.erp.domain.SysResource;
import com.zyp.erp.domain.SysResourceRole;
import com.zyp.erp.domain.SysRole;
import com.zyp.erp.domain.SysRoleUser;
import com.zyp.erp.formbean.back.SysRoleFormBean;
import com.zyp.erp.service.SysRoleService;
import com.zyp.erp.utils.BeanCopierUtil;
import com.zyp.erp.utils.MenuContext;
import com.zyp.erp.vo.back.SysRoleVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysResourceRoleMapper sysResourceRoleMapper;

    @Autowired
    private MenuContext menuContext;

    @Override
    public SysRoleVO getById(Integer id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);

        if ( sysRole != null ) {
            SysRoleVO vo = new SysRoleVO();
            BeanCopierUtil.copy(sysRole, vo);

            return vo;
        }

        return null;
    }

    @Override
    public List<SysRole> listAll() {
        List<SysRole> sysRoleVOS = sysRoleMapper.selectAll();
        return sysRoleVOS;
    }

    @Override
    public List<SysRoleVO> listPage(SysRoleFormBean query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<SysRoleVO> list = sysRoleMapper.listPage(query);
        return list;
    }


    @Override
    public PageInfo<SysRoleVO> getPageInfo(SysRoleFormBean query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<SysRoleVO> list = sysRoleMapper.listPage(query);
        return new PageInfo<>(list);
    }

    @Transactional
    @Override
    public void saveOrUpdate(SysRoleVO roleVO) {
        SysRole sysRole = new SysRole();
        BeanCopierUtil.copy(roleVO, sysRole);

        if (null != sysRole.getId() && -1 != sysRole.getId()) {
            sysRole.setUpdateTime(new Date());

            sysRoleMapper.updateByPrimaryKeySelective(sysRole);

            // 删除角色-资源关系.
            unCorrelationResources(roleVO.getAppType(), sysRole.getId());

        } else {

            sysRole.setId(null);
            sysRole.setAddTime(new Date());
            sysRole.setUpdateTime(new Date());
            sysRoleMapper.insertSelective(sysRole);

        }

        // 添加角色-资源关系
        if ( StringUtils.isNotBlank(roleVO.getResourceIds()) ) {
            String[] resourcesIdArr = roleVO.getResourceIds().split(",");
            Integer[] resourceArray = new Integer[resourcesIdArr.length];
            for (int i = 0; i < resourcesIdArr.length; i++) {
                resourceArray[i] = Integer.parseInt(resourcesIdArr[i]);
            }
            correlationResources(sysRole.getId(), resourceArray);
        }

        menuContext.clearAll();

    }

    private void unCorrelationResources(Integer appType, Integer roleId) {
        List<SysResource> sysResources = sysResourceRoleMapper.listResource(appType, roleId);
        if ( CollectionUtils.isNotEmpty(sysResources) ) {
            for ( SysResource sysResource : sysResources ) {
                SysResourceRole query = new SysResourceRole();
                query.setResourceId(sysResource.getId());
                query.setRoleId(roleId);
                sysResourceRoleMapper.delete(query);
            }
        }

    }

    private void correlationResources(Integer roleId, Integer[] resourceIds) {
        for (Integer resourceId : resourceIds) {
            SysResourceRole resourceRole = new SysResourceRole();
            resourceRole.setRoleId(roleId);
            resourceRole.setResourceId(resourceId);
            sysResourceRoleMapper.insertSelective(resourceRole);
        }
    }

    @Transactional
    @Override
    public void deleteById(Integer roleId) {
        sysRoleMapper.deleteByPrimaryKey(roleId);

        SysResourceRole resourceRoleQuery = new SysResourceRole();
        resourceRoleQuery.setRoleId(roleId);
        sysResourceRoleMapper.delete(resourceRoleQuery);

        SysRoleUser roleUserQuery = new SysRoleUser();
        roleUserQuery.setRoleId(roleId);
        sysRoleUserMapper.delete(roleUserQuery);

        menuContext.clearAll();

    }

}