package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.KnowledgeDao;
import com.oty.sys.entity.Knowledge;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;

@Service("knowledgeService")
@Transactional(readOnly = true)
public class KnowledgeService {

	@Autowired
	private KnowledgeDao knowledgeDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public Knowledge get(Integer id) {
		return knowledgeDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, Knowledge t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, knowledgeDao.selectByPrimaryKey(t.getId()), t);
			knowledgeDao.updateByPrimaryKey(t);
		} else {
			knowledgeDao.insertUseGeneratedKeys(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<Knowledge> query(Knowledge t, Integer page) {
		Example example = new Example(Knowledge.class);
		Example.Criteria criteria = example.createCriteria();
		if (t.getType() != null) {
			criteria.andEqualTo("type", t.getType());
		}
		if (StrFuncs.notEmpty(t.getTitle())) {
			criteria.andLike("title",  StrFuncs.anyLike(t.getTitle()));
		}
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10);
		return knowledgeDao.selectByExample(example);
	}

}
