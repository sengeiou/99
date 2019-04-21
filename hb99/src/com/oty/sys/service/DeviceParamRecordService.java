package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.DeviceParamRecordDao;
import com.oty.sys.entity.TDeviceParamRecord;

import pub.functions.DateFuncs;

@Service("devicePparamRecordService")
@Transactional(readOnly = true)
public class DeviceParamRecordService {

	@Autowired
	private DeviceParamRecordDao deviceParamRecordDao; 

	public TDeviceParamRecord get(Integer id) {
		return deviceParamRecordDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(TDeviceParamRecord t) {
		if (t.getId() != null) { 
			deviceParamRecordDao.updateByPrimaryKey(t);
		} else {
			deviceParamRecordDao.insertUseGeneratedKeys(t); 
		}
	}

	public List<TDeviceParamRecord> query(TDeviceParamRecord t, Integer page) {
		Example example = new Example(TDeviceParamRecord.class);
		Example.Criteria criteria = example.createCriteria(); 
		if (t.getDeviceId() != null) {
			criteria.andEqualTo("deviceId", t.getDeviceId());
		}
		if (t.getCreateTime() != null) { 
			criteria.andGreaterThanOrEqualTo("createTime", t.getCreateTime());
		} 
		if (t.getUpdateTime()!=null) { 
			criteria.andLessThanOrEqualTo("updateTime", DateFuncs.addSecond(DateFuncs.addDay(t.getUpdateTime(), 1), -1));
		}   
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, 10);
		return deviceParamRecordDao.selectByExample(example);
	}

}
