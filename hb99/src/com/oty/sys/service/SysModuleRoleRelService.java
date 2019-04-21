package com.oty.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oty.sys.dao.SysModuleRoleRelDao;
import com.oty.sys.entity.SysModuleRoleRel;
import com.oty.util.OperLogUtils;

@Service
@Transactional(readOnly = true)
public class SysModuleRoleRelService {

	@Autowired
	private SysModuleRoleRelDao sysModuleRoleRelDao;
	@Autowired
	private OperLogUtils operLogUtils;

	@Transactional
	public void save(Integer sysUserId, SysModuleRoleRel t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, sysModuleRoleRelDao.selectByPrimaryKey(t.getId()), t);
			sysModuleRoleRelDao.updateByPrimaryKey(t);
		} else {
			sysModuleRoleRelDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	@Transactional
	public void logicDelete(Integer id) {
		sysModuleRoleRelDao.deleteByPrimaryKey(id);
	}

	public SysModuleRoleRel get(Integer id) {
		return sysModuleRoleRelDao.selectByPrimaryKey(id);
	}
}
