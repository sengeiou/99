package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.MaintainTaskAttributeRelDao;
import com.oty.sys.entity.TMaintainTaskAttributeRel;
import com.oty.util.OperLogUtils;

@Service("maintainTaskAttributeRelService")
@Transactional(readOnly = true)
public class MaintainTaskAttributeRelService {

	@Autowired
	private MaintainTaskAttributeRelDao maintainTaskAttributeRelDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TMaintainTaskAttributeRel get(Integer id) {
		return maintainTaskAttributeRelDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TMaintainTaskAttributeRel t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, maintainTaskAttributeRelDao.selectByPrimaryKey(t.getId()), t);
			maintainTaskAttributeRelDao.updateByPrimaryKey(t);
		} else {
			maintainTaskAttributeRelDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<TMaintainTaskAttributeRel> query(TMaintainTaskAttributeRel t, Integer page) {
		Example example = new Example(TMaintainTaskAttributeRel.class);
		Example.Criteria criteria = example.createCriteria();
		if (t.getTaskId() != null) {
			criteria.andEqualTo("taskId", t.getTaskId());
		}
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, 10);
		return maintainTaskAttributeRelDao.selectByExample(example);
	}

}
