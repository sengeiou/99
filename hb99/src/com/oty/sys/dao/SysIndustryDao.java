package com.oty.sys.dao;

import java.util.List;

import pub.types.IdText;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.SysIndustry;

public interface SysIndustryDao extends InsertUseGeneratedKeysMapper<SysIndustry>, Mapper<SysIndustry> {
	
	public List<IdText> list(); 
	
}
