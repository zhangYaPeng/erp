package com.zyp.erp.service;

import com.zyp.erp.vo.back.SysResourceVO;

import java.util.List;

public interface SysResourceService {
    Boolean save(SysResourceVO sysResourceVO);

    Boolean update(SysResourceVO sysResourceVO);

    SysResourceVO getById(Integer id);

    List<SysResourceVO> findByAppType(Integer appId);

    void deleteByIdAndSons(Integer resourceId);

}