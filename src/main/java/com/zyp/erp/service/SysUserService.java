package com.zyp.erp.service;

import com.github.pagehelper.PageInfo;
import com.zyp.erp.formbean.back.SysUserFormBean;
import com.zyp.erp.vo.back.SysUserVO;

public interface SysUserService {

    SysUserVO getById(Integer id);


    PageInfo<SysUserVO> getPageInfo(SysUserFormBean query);


    void saveOrUpdate(SysUserVO userVO);


    void updateState(Integer id, Integer state);

    void delete(Integer id);

}