package com.oty.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.DeviceAttribute;

public interface DeviceAttributeDao extends InsertUseGeneratedKeysMapper<DeviceAttribute>, Mapper<DeviceAttribute> {

	public DeviceAttribute findByName(@Param("name") String name);
	
	public DeviceAttribute findByNameAndExcludeId(@Param("name") String name, @Param("excludeId")Integer excludeId);
}
