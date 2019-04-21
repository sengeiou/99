package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import pub.functions.StrFuncs;
import pub.types.IdText;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.SysIndustryDao;
import com.oty.sys.entity.SysIndustry;
import com.oty.util.OperLogUtils; 

@Service("sysIndustryService")
@Transactional(readOnly = true) 
public class SysIndustryService {
	
	@Autowired
	private SysIndustryDao sysIndustryDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public SysIndustry get(Integer id) {
		return sysIndustryDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Integer sysUserId, SysIndustry t) {
		if(t.getId()!=null){
			operLogUtils.updateLog(sysUserId, sysIndustryDao.selectByPrimaryKey(t.getId()), t);
			sysIndustryDao.updateByPrimaryKey(t);
		} else {
			sysIndustryDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		} 
	}   

	public List<SysIndustry> query(SysIndustry sysIndustry, Integer page) {
		Example example = new Example(SysIndustry.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysIndustry.getName())) {
			criteria.andLike("name", sysIndustry.getName());
		}  
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, 10); 
		return sysIndustryDao.selectByExample(example);
	}

	public List<IdText> list() {
		return sysIndustryDao.list();
	} 
	
}
