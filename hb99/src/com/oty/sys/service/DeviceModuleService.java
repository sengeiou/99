package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.DeviceModuleDao;
import com.oty.sys.entity.TDeviceModule;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;
import pub.types.IdText; 

@Service("deviceModuleService")
@Transactional(readOnly = true)
public class DeviceModuleService {

	@Autowired
	private DeviceModuleDao deviceModuleDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TDeviceModule get(Integer id) {
		return deviceModuleDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Integer sysUserId, TDeviceModule t) {
		if(t.getId()!=null){
			operLogUtils.updateLog(sysUserId, deviceModuleDao.selectByPrimaryKey(t.getId()), t);
			deviceModuleDao.updateByPrimaryKey(t);
		} else {
			deviceModuleDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		} 
	}   

	public List<TDeviceModule> query(TDeviceModule t, Integer page) {
		Example example = new Example(TDeviceModule.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getMac())) {
			criteria.andLike("mac", t.getMac());
		} 
		if(t.getStatus()!=null){
			criteria.andEqualTo("status", t.getStatus());
		}
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10); 
		return deviceModuleDao.selectByExample(example);
	}

	public List<IdText> getAll() {
		return deviceModuleDao.getAll();
	}

	public List<IdText> getAllNotRel(Integer moduleId) {
		return deviceModuleDao.getAllNotRel(moduleId);
	}

}
