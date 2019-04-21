package com.oty.sys.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.MaintainTaskAttributeRelDao;
import com.oty.sys.dao.MaintainTaskDao;
import com.oty.sys.entity.TMaintainTask;
import com.oty.sys.entity.TMaintainTaskAttributeRel;
import com.oty.sys.model.admin.task.MaintainTaskData;
import com.oty.util.OperLogUtils;

@Service("maintainTaskService")
@Transactional(readOnly = true)
public class MaintainTaskService {

	@Autowired
	private MaintainTaskDao maintainTaskDao;
	@Autowired
	private MaintainTaskAttributeRelDao maintainRelDao;
	@Autowired
	private MaintainTaskAttributeRelDao maintainTaskAttributeRelDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TMaintainTask get(Integer id) {
		return maintainTaskDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TMaintainTask t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, maintainTaskDao.selectByPrimaryKey(t.getId()), t);
			maintainTaskDao.updateByPrimaryKey(t);
		} else {
			maintainTaskDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<TMaintainTask> query(TMaintainTask t, Integer page) {
		Example example = new Example(TMaintainTask.class);
		Example.Criteria criteria = example.createCriteria();
		if (t.getDeviceId() != null) {
			criteria.andEqualTo("deviceId", t.getDeviceId());
		}
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, 10);
		return maintainTaskDao.selectByExample(example);
	}

	public MaintainTaskData load(Integer id) {
		MaintainTaskData data = new MaintainTaskData();
		data.setMaintainTask(maintainTaskDao.selectByPrimaryKey(id));
		data.setMaintainAttributeList(maintainRelDao.loadAttributeList(id));
		return data;
	}

	@Transactional
	public void save(MaintainTaskData maintainTaskData) {
		TMaintainTask t = maintainTaskData.getMaintainTask();
		boolean isNew = t.getId() == null;
		if (t.getId() != null) {
			maintainTaskDao.updateByPrimaryKey(t);
		} else {
			maintainTaskDao.insertUseGeneratedKeys(t);
		}
		if (!isNew) {
			maintainTaskAttributeRelDao.delAllByTaksId(t.getId());
		}
		HashSet<Integer> set = new HashSet<Integer>(maintainTaskData.getMaintainAttributeList().size());
		set.addAll(maintainTaskData.getMaintainAttributeList());// 去重
		for (Integer id : set) {
			TMaintainTaskAttributeRel rel = new TMaintainTaskAttributeRel();
			rel.setAttributeId(id);
			rel.setTaskId(t.getId());
			maintainTaskAttributeRelDao.insert(rel);
		}
	}
}
