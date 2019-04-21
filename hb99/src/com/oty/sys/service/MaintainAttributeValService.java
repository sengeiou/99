package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.MaintainAttributeValDao;
import com.oty.sys.entity.TMaintainAttributeVal;
import com.oty.util.OperLogUtils;

@Service("maintainAttributeValService")
@Transactional(readOnly = true)
public class MaintainAttributeValService {

	@Autowired
	private MaintainAttributeValDao maintainAttributeValDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TMaintainAttributeVal get(Integer id) {
		return maintainAttributeValDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TMaintainAttributeVal t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, maintainAttributeValDao.selectByPrimaryKey(t.getId()), t);
			maintainAttributeValDao.updateByPrimaryKey(t);
		} else {
			maintainAttributeValDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<TMaintainAttributeVal> query(TMaintainAttributeVal t, Integer page) {
		Example example = new Example(TMaintainAttributeVal.class);
		Example.Criteria criteria = example.createCriteria();
		if (t.getTaskId() != null) {
			criteria.andEqualTo("taskId", t.getTaskId());
		}
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, 10);
		return maintainAttributeValDao.selectByExample(example);
	}

}
