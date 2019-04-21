package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.OrgDao;
import com.oty.sys.entity.TOrg;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;

@Service("orgService")
@Transactional(readOnly = true) 
public class OrgService {

	@Autowired
	private OrgDao orgDao;
	@Autowired
	private OperLogUtils operLogUtils;

	@Transactional 
	public void save(Integer sysUserId, TOrg t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, orgDao.selectByPrimaryKey(t.getId()), t);
			orgDao.updateByPrimaryKey(t);
		} else {
			orgDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}
 
	public TOrg get(Integer id) {
		return orgDao.selectByPrimaryKey(id);
	}

	public List<TOrg> query(TOrg t, Integer page) {
		Example example = new Example(TOrg.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", t.getName());
		} 
		criteria.andEqualTo("delFlag", 0);
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10);
		return orgDao.selectByExample(example);
	}

}
