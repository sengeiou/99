package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.SysUser;

import pub.types.IdText;

public interface SysUserDao extends InsertUseGeneratedKeysMapper<SysUser>, Mapper<SysUser> {

	public SysUser getByAccount(String account);

	public Integer isManager(Integer id);

	public Integer checkAccount(String account);

	public List<IdText> getAll();
	
	public List<IdText> getAllexME(String account);
	
}