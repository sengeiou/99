package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.oty.sys.dao.SysModuleDao;
import com.oty.sys.entity.SysModule;
 
@Service
@Transactional(readOnly = true)
public class SysModuleService  { 

	@Autowired
	private SysModuleDao moduleDao;
	
	public SysModule get(Integer moduleId) {
		return moduleDao.selectByPrimaryKey(moduleId);
	} 
	
	public List<SysModule> getAll() {
		return moduleDao.selectByExample(null);
	}
	
}
