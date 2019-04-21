package com.oty.sys.dao;

import com.github.abel533.mapper.Mapper;
import com.oty.sys.entity.SysConfig;

public interface SysConfigDao extends Mapper<SysConfig> {

	public SysConfig getByKey(String key);

}
