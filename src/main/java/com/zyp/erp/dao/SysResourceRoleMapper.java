package com.zyp.erp.dao;

import com.zyp.erp.domain.SysResource;
import com.zyp.erp.domain.SysResourceRole;
import com.zyp.erp.utils.BaseMyBatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceRoleMapper extends BaseMyBatisMapper<SysResourceRole> {

    List<SysResource> listResource(@Param("appType") Integer appType, @Param("roleId") Integer roleId);

}