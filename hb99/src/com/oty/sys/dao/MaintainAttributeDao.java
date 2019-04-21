package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.TMaintainAttribute;

import pub.types.IdText;

public interface MaintainAttributeDao extends InsertUseGeneratedKeysMapper<TMaintainAttribute>, Mapper<TMaintainAttribute> {
	
	public List<IdText> getAll();
	
}
