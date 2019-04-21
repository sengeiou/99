package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.TDevice;

import pub.types.IdText;

public interface DeviceDao extends InsertUseGeneratedKeysMapper<TDevice>, Mapper<TDevice> {

	public List<IdText> getAll();

}
