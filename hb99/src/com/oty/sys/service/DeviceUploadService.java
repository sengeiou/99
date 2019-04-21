package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oty.sys.dao.DeviceUploadDao;
import com.oty.sys.entity.DeviceUpload; 

@Service("deviceUploadService")
@Transactional(readOnly = true)
public class DeviceUploadService {

	@Autowired
	private DeviceUploadDao deviceUploadDao; 

	public DeviceUpload get(Integer id) {
		return deviceUploadDao.selectByPrimaryKey(id);
	}

	@Transactional
	public void save(Integer sysUserId, DeviceUpload t) {
		if (t.getId() != null) { 
			deviceUploadDao.updateByPrimaryKey(t);
		} else {
			deviceUploadDao.insertUseGeneratedKeys(t); 
		}
	}

	public List<DeviceUpload> getLast(Integer deviceId) {
		return deviceUploadDao.getLast(deviceId);
	}

}
