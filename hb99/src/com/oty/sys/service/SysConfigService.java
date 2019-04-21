package com.oty.sys.service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.SysConfigDao;
import com.oty.sys.entity.SysConfig;
import com.oty.util.OperLogUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pub.functions.StrFuncs;

import java.util.List;

@Service("sysConfigService")
@Transactional(readOnly = true)
public class SysConfigService {

	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private OperLogUtils operLogUtils;

	@Transactional
	@CacheEvict(value = "defaultCache", key = "'SysConfig_id_'+#t.id")
	public void save(Integer sysUserId, SysConfig t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, sysConfigDao.selectByPrimaryKey(t.getId()), t);
			sysConfigDao.updateByPrimaryKey(t);
		} else {
			sysConfigDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	@Cacheable(value = "defaultCache", key = "'SysConfig_id_'+#id")
	public SysConfig get(Integer id) {
		return sysConfigDao.selectByPrimaryKey(id);
	}

	public SysConfig getByKey(String key) {
		return sysConfigDao.getByKey(key);
	}

	public List<SysConfig> query(SysConfig sysConfig, Integer page) {
		Example example = new Example(SysConfig.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysConfig.getConfigKey())) {
			criteria.andLike("configKey", StrFuncs.anyLike(sysConfig.getConfigKey()));
		}
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10);
		return sysConfigDao.selectByExample(example);
	}

}
