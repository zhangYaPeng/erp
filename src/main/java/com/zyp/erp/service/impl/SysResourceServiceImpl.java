package com.zyp.erp.service.impl;


import com.zyp.erp.dao.SysResourceMapper;
import com.zyp.erp.dao.SysResourceRoleMapper;
import com.zyp.erp.domain.SysResource;
import com.zyp.erp.domain.SysResourceRole;
import com.zyp.erp.service.SysResourceService;
import com.zyp.erp.utils.BeanCopierUtil;
import com.zyp.erp.vo.back.SysResourceVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysResourceRoleMapper sysResourceRoleMapper;

    @Override
    public Boolean save(SysResourceVO sysResourceVO) {
        SysResource sysResource = new SysResource();
        BeanCopierUtil.copy(sysResourceVO, sysResource);

        sysResource.setAddTime(new Date());
        sysResource.setUpdateTime(new Date());

        int rows = sysResourceMapper.insertSelective(sysResource);
        return rows > 0;
    }

    @Transactional
    @Override
    public Boolean update(SysResourceVO sysResourceVO) {
        SysResource sysResource = new SysResource();
        BeanCopierUtil.copy(sysResourceVO, sysResource);

        sysResource.setUpdateTime(new Date());

        sysResourceMapper.updateByPrimaryKeySelective(sysResource);

        return true;
    }

    @Override
    public SysResourceVO getById(Integer id) {
        SysResource sysResource = sysResourceMapper.selectByPrimaryKey(id);

        if ( sysResource != null ) {
            SysResourceVO vo = new SysResourceVO();
            BeanCopierUtil.copy(sysResource, vo);

            return vo;
        }

        return null;
    }



    @Override
    public List<SysResourceVO> findByAppType(Integer appType) {
        return sysResourceMapper.findByAppType(appType);
    }

    @Transactional
    @Override
    public void deleteByIdAndSons(Integer resourceId) {
        sysResourceMapper.deleteByPrimaryKey(resourceId);

        SysResource query = new SysResource();
        query.setParentId(resourceId);

        List<SysResource> list = sysResourceMapper.select(query);
        if ( CollectionUtils.isNotEmpty(list) ) {
            for (SysResource son : list) {
                deleteByIdAndSons(son.getId());
            }
        }

        SysResourceRole resourceRoleQuery = new SysResourceRole();
        resourceRoleQuery.setResourceId(resourceId);
        sysResourceRoleMapper.delete(resourceRoleQuery);

    }

}