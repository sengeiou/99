package com.oty.sys.dao;

import java.util.HashSet;
import java.util.List; 

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.SysModuleRoleRel;
  
public interface SysModuleRoleRelDao extends InsertUseGeneratedKeysMapper<SysModuleRoleRel>, Mapper<SysModuleRoleRel> { 

	public List<Integer> getRoleModuleIds(Integer roleId);

	public void logicDelete(Integer roleId, HashSet<Integer> moduleIds);

	public void logicDeleteByRoleId(Integer roleId);
	
}
