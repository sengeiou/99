package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.SysRole;   

import pub.types.IdText;

public interface SysRoleDao extends InsertUseGeneratedKeysMapper<SysRole>, Mapper<SysRole> {

	public List<IdText> list(); 
	
	public int save(SysRole t);

}
