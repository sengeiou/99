package com.oty.sys.service;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.oty.sys.dao.FileDao;
import com.oty.sys.dao.FileInfoDao;
import com.oty.sys.entity.TFileInfo;
import com.oty.util.OperLogUtils;
 
@Service("fileInfoService")
@Transactional(readOnly = true) 
public class FileInfoService {
 
	@Autowired
	private FileDao fileDao; 
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private OperLogUtils operLogUtils;
	 
	public TFileInfo getFileInfo(String tableName, Integer refId, Integer type) {
		return fileInfoDao.getFileInfo(tableName, refId, type);
	}

	public TFileInfo get(Integer id) {
		return fileInfoDao.selectByPrimaryKey(id);
	}
 
	public TFileInfo getFileInfoByFileId(Integer fileId) {
		return fileInfoDao.getFileInfoByFileId(fileId);
	}

	@Transactional
	public Integer save(Integer sysUserId, TFileInfo fileInfo) {
		if(fileInfo.getId()!=null){
			operLogUtils.updateLog(sysUserId, fileInfoDao.selectByPrimaryKey(fileInfo.getId()), fileInfo);
			fileInfoDao.updateByPrimaryKey(fileInfo);
		} else {
			fileInfoDao.insert(fileInfo);
			operLogUtils.insertLog(sysUserId, fileInfo);
		} 
		return fileInfo.getId();
	} 

	@Transactional
	public void logicDelete(Integer id) {
		TFileInfo fileInfo = fileInfoDao.selectByPrimaryKey(id);
		if (fileInfo.getFileId() != null) {
			fileDao.deleteByPrimaryKey(fileInfo.getFileId());
		}
		fileInfoDao.delete(fileInfo);
	}

	@Transactional
	public void updateFile(Integer id, Integer fileId) {
		TFileInfo fileInfo = fileInfoDao.selectByPrimaryKey(id); 
		fileInfo.setFileId(fileId);
		fileInfoDao.updateByPrimaryKey(fileInfo);
	} 

}
