package com.zyp.erp.service;


import com.github.pagehelper.PageInfo;
import com.zyp.erp.domain.SysRole;
import com.zyp.erp.formbean.back.SysRoleFormBean;
import com.zyp.erp.vo.back.SysRoleVO;

import java.util.List;

public interface SysRoleService {

    SysRoleVO getById(Integer id);

    List<SysRole> listAll();

    List<SysRoleVO> listPage(SysRoleFormBean query);

    PageInfo<SysRoleVO> getPageInfo(SysRoleFormBean query);

    void saveOrUpdate(SysRoleVO roleVO);

    void deleteById(Integer roleId);
}