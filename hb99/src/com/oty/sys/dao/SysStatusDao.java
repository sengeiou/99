package com.oty.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.SysStatus;

import pub.types.IdText; 
  
public interface SysStatusDao extends InsertUseGeneratedKeysMapper<SysStatus>, Mapper<SysStatus>  { 

	public SysStatus get(String tableName, String fieldName, Integer code);

	public List<IdText> list(@Param("tableName") String tableName, @Param("fieldName") String fieldName);

	public List<IdText> list2(@Param("tableName") String tableName, @Param("fieldName") String fieldName, @Param("excludeCodes") Integer[] excludeCodes);

	public String getDescribe(@Param("tableName") String tableName, @Param("fieldName") String fieldName, @Param("code") Integer code);

}
