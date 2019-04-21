package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.DeviceTypeDao;
import com.oty.sys.entity.TDeviceType;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;
import pub.types.IdText;

@Service("deviceTypeService")
@Transactional(readOnly = true)
public class DeviceTypeService {

	@Autowired
	private DeviceTypeDao deviceTypeDao;
	@Autowired
	private OperLogUtils operLogUtils;

	@Transactional
	public void save(Integer sysUserId, TDeviceType t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, deviceTypeDao.selectByPrimaryKey(t.getId()), t);
			deviceTypeDao.updateByPrimaryKey(t);
		} else {
			deviceTypeDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public TDeviceType get(Integer id) {
		return deviceTypeDao.selectByPrimaryKey(id);
	}

	public List<TDeviceType> query(TDeviceType t, Integer page) {
		Example example = new Example(TDeviceType.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", StrFuncs.anyLike(t.getName()));
		}
		criteria.andEqualTo("delFlag", 0);
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10);
		return deviceTypeDao.selectByExample(example);
	}

	public List<IdText> getAll() {
		return deviceTypeDao.getAll();
	}

}
