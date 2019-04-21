package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.TMaintainTaskAttributeRel;

public interface MaintainTaskAttributeRelDao
		extends InsertUseGeneratedKeysMapper<TMaintainTaskAttributeRel>, Mapper<TMaintainTaskAttributeRel> {

	public List<Integer> loadAttributeList(Integer taskId);

	public void delAllByTaksId(Integer taskId);

}
