package com.zyp.erp.dao;

import com.zyp.erp.domain.SysResource;
import com.zyp.erp.utils.BaseMyBatisMapper;
import com.zyp.erp.vo.back.SysResourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper extends BaseMyBatisMapper<SysResource> {

    List<SysResource> listResourceByUserId4PC(@Param("userId") Integer userId);

    List<SysResourceVO> findByAppType(@Param("appType") Integer appType);
}