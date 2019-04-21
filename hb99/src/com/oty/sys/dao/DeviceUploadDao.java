package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.DeviceUpload; 

public interface DeviceUploadDao extends InsertUseGeneratedKeysMapper<DeviceUpload>, Mapper<DeviceUpload> {

	public List<DeviceUpload> getLast(Integer deviceId);

}
