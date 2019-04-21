package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.TDeviceType;

import pub.types.IdText;

public interface DeviceTypeDao extends InsertUseGeneratedKeysMapper<TDeviceType>, Mapper<TDeviceType> {

	public List<IdText> getAll();

}
