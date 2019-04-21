package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper; 
import com.oty.sys.dao.SysOperLogDao;
import com.oty.sys.entity.SysOperLog; 

@Service("sysOperLogService")
@Transactional(readOnly = true)
public class SysOperLogService {

	@Autowired
	private SysOperLogDao sysOperLogDao;

	@Transactional 
	public void save(SysOperLog t) {
		if (t.getId() != null) {
			sysOperLogDao.updateByPrimaryKey(t);
		} else {
			sysOperLogDao.insert(t);
		}
	}
 
	public SysOperLog get(Integer id) {
		return sysOperLogDao.selectByPrimaryKey(id);
	}

	public List<SysOperLog> query(SysOperLog t, Integer page) {
		Example example = new Example(SysOperLog.class);
		Example.Criteria criteria = example.createCriteria(); 
		if(t.getOperType()!=null){
			criteria.andEqualTo("operType", t.getOperType());
		}
		if(t.getSysUserId()!=null){
			criteria.andEqualTo("sysUserId", t.getSysUserId());
		}
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10);
		return sysOperLogDao.selectByExample(example);
	}

}
