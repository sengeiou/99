package com.oty.sys.service; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.SysNoticeDao;
import com.oty.sys.entity.SysNotice;
import com.oty.util.OperLogUtils;

import pub.functions.DateFuncs;
import pub.functions.StrFuncs;
import pub.spring.MultipartFileHolder;

@Service("noticeService")
@Transactional(readOnly = true)  
public class SysNoticeService {
 
	@Autowired
	private SysNoticeDao noticeDao; 
	@Autowired
	private OperLogUtils operLogUtils;
 
	public SysNotice get(Integer id) {
		return noticeDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Integer sysUserId, SysNotice t) {
		if(t.getId()!=null){
			operLogUtils.updateLog(sysUserId, noticeDao.selectByPrimaryKey(t.getId()), t);
			noticeDao.updateByPrimaryKey(t);
		} else {
			noticeDao.save(t);
			operLogUtils.insertLog(sysUserId, t);
		} 
	} 
	
	@Transactional
	public void save(SysNotice notice, MultipartFileHolder photo){ 
		noticeDao.insert(notice);  
	}   

	public List<SysNotice> query(SysNotice sysNotice, Integer page) {
		Example example = new Example(SysNotice.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysNotice.getTitle())) {
			criteria.andLike("title", StrFuncs.anyLike(sysNotice.getTitle()));
		}
		if (StrFuncs.notEmpty(sysNotice.getDeadline())) {  
			criteria.andLessThan("deadline", DateFuncs.stringToDate(sysNotice.getDeadline()));
		}
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10); 
		return noticeDao.selectByExample(example);
	}
	
}
