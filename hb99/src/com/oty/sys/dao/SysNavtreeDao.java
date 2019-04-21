package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.SysNavtree;

public interface SysNavtreeDao extends InsertUseGeneratedKeysMapper<SysNavtree>, Mapper<SysNavtree> {

	public List<SysNavtree> getAllVisible(String orderBy);

	public List<SysNavtree> listVisibleByUser(Integer userId);

}
