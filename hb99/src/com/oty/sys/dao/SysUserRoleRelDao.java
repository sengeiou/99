package com.oty.sys.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.SysUserRoleRel; 

public interface SysUserRoleRelDao extends InsertUseGeneratedKeysMapper<SysUserRoleRel>, Mapper<SysUserRoleRel> {

	public List<Integer> getUserRoleIds(@Param("userId") Integer userId);

	public void deleteByRoleId(@Param("userId") Integer roleId);

	public void deleteByUserId(@Param("userId") Integer userId);

	public void delete2(@Param("userId") Integer userId, @Param("roleIds") Collection<Integer> roleIds);

}
