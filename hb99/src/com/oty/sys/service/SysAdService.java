package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pub.functions.DateFuncs;
import pub.functions.StrFuncs;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.SysAdDao;
import com.oty.sys.entity.SysAd;
import com.oty.util.OperLogUtils; 

@Service("sysAdService")
@Transactional(readOnly = true)
public class SysAdService {
	
	@Autowired
	private SysAdDao sysAdDao;
	@Autowired
	private OperLogUtils operLogUtils;

	@Transactional
	public void save(Integer sysUserId, SysAd t){
		if(t.getId()!=null){
			operLogUtils.updateLog(sysUserId, sysAdDao.selectByPrimaryKey(t.getId()), t);
			sysAdDao.updateByPrimaryKey(t);
		} else {
			sysAdDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}
	
	public SysAd get(Integer id){
		return sysAdDao.selectByPrimaryKey(id);
	} 

	public List<SysAd> query(SysAd sysAd, Integer page) {
		Example example = new Example(SysAd.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysAd.getTitle())) {
			criteria.andLike("title", StrFuncs.anyLike(sysAd.getTitle()));
		}
		if (sysAd.getDeadline()!=null) {  
			criteria.andLessThan("deadline", DateFuncs.stringToDate(sysAd.getDeadline()));
		}
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10); 
		return sysAdDao.selectByExample(example);
	}
	
}
