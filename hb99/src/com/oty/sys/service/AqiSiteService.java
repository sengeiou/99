package com.oty.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oty.sys.dao.AqiSiteDao;
import com.oty.sys.entity.TAqiSite;

@Service("aqiSiteService")
@Transactional(readOnly = true)
public class AqiSiteService {

	@Autowired
	private AqiSiteDao aqiSiteDao;

	public TAqiSite get(Integer id) {
		return aqiSiteDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(TAqiSite t) {
		if (t.getId() != null) {
			aqiSiteDao.updateByPrimaryKey(t);
		} else {
			aqiSiteDao.insertUseGeneratedKeys(t);
		}
	}

}
