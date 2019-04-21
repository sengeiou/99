package com.oty.sys.dao;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.DeviceCommand;

public interface DeviceCommandDao extends InsertUseGeneratedKeysMapper<DeviceCommand>, Mapper<DeviceCommand> {

	public DeviceCommand getNewestSwitchCommand(@Param("deviceId") Integer deviceId);
	
}
