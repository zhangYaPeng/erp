package com.zyp.erp.dao;

import com.zyp.erp.domain.SysUser;
import com.zyp.erp.formbean.back.SysUserFormBean;
import com.zyp.erp.utils.BaseMyBatisMapper;
import com.zyp.erp.vo.back.SysUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMyBatisMapper<SysUser> {

    List<SysUserVO> listPage(@Param("query") SysUserFormBean query);

}