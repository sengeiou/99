package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper; 
import com.oty.sys.dao.WarnPicDao; 
import com.oty.sys.entity.TWarnPic; 

import pub.functions.StrFuncs; 

@Service("warnPicService")
@Transactional(readOnly = true)
public class WarnPicService {

	@Autowired
	private WarnPicDao warnPicDao; 

	public TWarnPic get(Integer id) {
		return warnPicDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, TWarnPic t) {
		if (t.getId() != null) { 
			warnPicDao.updateByPrimaryKey(t);
		} else {
			warnPicDao.insertUseGeneratedKeys(t); 
		}
	}

	public List<TWarnPic> query(TWarnPic t, Integer page) {
		Example example = new Example(TWarnPic.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", t.getName());
		}  
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10);
		return warnPicDao.selectByExample(example);
	} 

}
