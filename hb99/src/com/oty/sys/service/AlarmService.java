package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.AlarmDao;
import com.oty.sys.entity.TAlarm;  

@Service("alarmService")
@Transactional(readOnly = true)
public class AlarmService {

	@Autowired
	private AlarmDao alarmDao; 

	public TAlarm get(Integer id) {
		return alarmDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TAlarm t) {
		if (t.getId() != null) { 
			alarmDao.updateByPrimaryKey(t);
		} else {
			alarmDao.insertUseGeneratedKeys(t); 
		}
	}

	public List<TAlarm> query(TAlarm t, Integer page) {
		Example example = new Example(TAlarm.class);
		Example.Criteria criteria = example.createCriteria(); 
		if (t.getStatus() != null) {
			criteria.andEqualTo("status", t.getStatus());
		}
		if (t.getType() != null) {
			criteria.andEqualTo("type", t.getType());
		}  
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10);
		return alarmDao.selectByExample(example);
	}

}
