package com.oty.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.TDeviceModule;

import pub.types.IdText;

public interface DeviceModuleDao extends InsertUseGeneratedKeysMapper<TDeviceModule>, Mapper<TDeviceModule> {

	public List<IdText> getAll();

	public List<IdText> getAllNotRel(@Param("moduleId") Integer moduleId);
	
}
