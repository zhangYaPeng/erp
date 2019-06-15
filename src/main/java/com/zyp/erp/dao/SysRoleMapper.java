package com.zyp.erp.dao;

import com.zyp.erp.domain.SysRole;
import com.zyp.erp.formbean.back.SysRoleFormBean;
import com.zyp.erp.utils.BaseMyBatisMapper;
import com.zyp.erp.vo.back.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMyBatisMapper<SysRole> {

    List<SysRoleVO> listPage(@Param("query") SysRoleFormBean query);

}