package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.MaintainAttributeDao;
import com.oty.sys.entity.TMaintainAttribute;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;
import pub.types.IdText;

@Service("maintainAttributeService")
@Transactional(readOnly = true)
public class MaintainAttributeService {

	@Autowired
	private MaintainAttributeDao maintainAttributeDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public TMaintainAttribute get(Integer id) {
		return maintainAttributeDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Integer sysUserId, TMaintainAttribute t) {
		if(t.getId()!=null){
			operLogUtils.updateLog(sysUserId, maintainAttributeDao.selectByPrimaryKey(t.getId()), t);
			maintainAttributeDao.updateByPrimaryKey(t);
		} else {
			maintainAttributeDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		} 
	}   

	public List<TMaintainAttribute> query(TMaintainAttribute t, Integer page) {
		Example example = new Example(TMaintainAttribute.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("mac", t.getName());
		} 
		if(t.getType()!=null){
			criteria.andEqualTo("type", t.getType());
		} 
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, 10); 
		return maintainAttributeDao.selectByExample(example);
	}

	public List<IdText> getAll() {
		return maintainAttributeDao.getAll();
	}

}
