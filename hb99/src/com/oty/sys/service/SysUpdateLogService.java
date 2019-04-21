package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pub.functions.StrFuncs;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.SysUpdateLogDao;
import com.oty.sys.entity.SysUpdateLog;
import com.oty.util.OperLogUtils;

@Service
@Transactional(readOnly = true)
public class SysUpdateLogService {

	@Autowired
	private SysUpdateLogDao sysUpdateLogDao;
	@Autowired
	private OperLogUtils operLogUtils;

	@Transactional
	public void save(Integer sysUserId, SysUpdateLog t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, sysUpdateLogDao.selectByPrimaryKey(t.getId()), t);
			sysUpdateLogDao.updateByPrimaryKey(t);
		} else {
			sysUpdateLogDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}

	}

	public SysUpdateLog get(Integer id) {
		return sysUpdateLogDao.selectByPrimaryKey(id);
	}

	public List<SysUpdateLog> query(SysUpdateLog sysUpdateLog, Integer page) {
		Example example = new Example(SysUpdateLog.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysUpdateLog.getVersion())) {
			criteria.andLike("version", StrFuncs.anyLike(sysUpdateLog.getVersion()));
		}
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10);
		return sysUpdateLogDao.selectByExample(example);
	}

}
