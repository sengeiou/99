package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pub.types.IdText;

import com.oty.sys.dao.SysStatusDao;
import com.oty.sys.entity.SysStatus;
 
@Service("sysStatusService")
@Transactional(readOnly = true)
public class SysStatusService {
	
	@Autowired
	private SysStatusDao sysStatusDao;

	public List<IdText> list(String tableName, String fieldName) {
		return sysStatusDao.list(tableName, fieldName);
	}

	public String getDescribe(String tableName, String fieldName, Integer code) {
		return sysStatusDao.getDescribe(tableName, fieldName, code);
	}

	public List<IdText> list(String tableName, String fieldName,
			Integer[] excludeCodes) { 
		return sysStatusDao.list2(tableName, fieldName, excludeCodes);
	}

	public SysStatus get(String tableName, String fieldName, Integer code) {
		return sysStatusDao.get(tableName, fieldName, code);
	}

}
