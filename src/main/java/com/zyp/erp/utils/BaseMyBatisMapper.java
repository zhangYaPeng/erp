package com.zyp.erp.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface BaseMyBatisMapper<T> extends Mapper<T>, MySqlMapper<T>
{
}
