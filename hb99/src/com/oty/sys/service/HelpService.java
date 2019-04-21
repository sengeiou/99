package com.oty.sys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.HelpDao;
import com.oty.sys.entity.Help;
import com.oty.util.OperLogUtils;
import pub.functions.StrFuncs;

@Service("helpService")
@Transactional(readOnly = true)
public class HelpService {

	@Autowired
	private HelpDao helpDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public Help get(Integer id) {
		return helpDao.selectByPrimaryKey(id);
	}
	
	@Transactional
	public void save(Integer sysUserId, Help t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, helpDao.selectByPrimaryKey(t.getId()), t);
			helpDao.updateByPrimaryKey(t);
		} else {
			helpDao.insertUseGeneratedKeys(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<Help> query(Help t, Integer page) {
		Example example = new Example(Help.class);
		Example.Criteria criteria = example.createCriteria();
		if (t.getType() != null) {
			criteria.andEqualTo("type", t.getType());
		}
		if (StrFuncs.notEmpty(t.getTitle())) {
			criteria.andLike("title",  StrFuncs.anyLike(t.getTitle()));
		}
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10);
		return helpDao.selectByExample(example);
	}

}
