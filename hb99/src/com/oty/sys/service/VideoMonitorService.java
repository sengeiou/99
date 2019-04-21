package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.VideoMonitorDao;
import com.oty.sys.entity.TVideoMonitor;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;
import pub.types.IdText;

@Service("videoMonitorService")
@Transactional(readOnly = true)
public class VideoMonitorService {

	@Autowired
	private VideoMonitorDao videoMonitorDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TVideoMonitor get(Integer id) {
		return videoMonitorDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TVideoMonitor t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, videoMonitorDao.selectByPrimaryKey(t.getId()), t);
			videoMonitorDao.updateByPrimaryKey(t);
		} else {
			videoMonitorDao.insertUseGeneratedKeys(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<TVideoMonitor> query(TVideoMonitor t, Integer page) {
		Example example = new Example(TVideoMonitor.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", t.getName());
		}  
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10);
		return videoMonitorDao.selectByExample(example);
	}

	public List<IdText> getAll() {
		return videoMonitorDao.getAll();
	}

}
