package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.TVideoMonitor;

import pub.types.IdText;

public interface VideoMonitorDao extends InsertUseGeneratedKeysMapper<TVideoMonitor>, Mapper<TVideoMonitor> {

	public List<IdText> getAll();

}
