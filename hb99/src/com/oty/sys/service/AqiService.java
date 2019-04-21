package com.oty.sys.service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.oty.sys.dao.AqiDao;
import com.oty.sys.entity.TAqi;

@Service("aqiService")
@Transactional(readOnly = true)
public class AqiService {

	@Autowired
	private AqiDao aqiDao; 

	public TAqi get(Integer id) {
		return aqiDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(TAqi t) {
		if (t.getId() != null) { 
			aqiDao.updateByPrimaryKey(t);
		} else {
			aqiDao.insertUseGeneratedKeys(t); 
		}
	}
 
}
