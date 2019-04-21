package com.oty.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper; 
import com.oty.sys.entity.TFileInfo;

public interface FileInfoDao extends InsertUseGeneratedKeysMapper<TFileInfo>, Mapper<TFileInfo> {

	public TFileInfo getFileInfoByFileId(Integer fileId);

	public Integer getImageId(String tableName, Integer referenceId, int type);

	public TFileInfo getFileInfo(@Param("tableName")String tableName,@Param("referenceId") Integer referenceId, @Param("type")int type);

	public void deleteOldInfos(TFileInfo currentFileInfo);

	public List<TFileInfo> list(String tableName, Integer referenceId);

	public List<Integer> listFileIds(String tableName, Integer referenceId);

	public void delRefId(Integer referenceId, String tableName);  

}
