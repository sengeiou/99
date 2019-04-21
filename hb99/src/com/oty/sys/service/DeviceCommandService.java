package com.oty.sys.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.DeviceCommandDao;
import com.oty.sys.entity.DeviceCommand;
import com.oty.util.OperLogUtils;

@Service("deviceCommandService")
@Transactional(readOnly = true)
public class DeviceCommandService {

	@Autowired
	private DeviceCommandDao deviceCommandDao;
	@Autowired
	private OperLogUtils operLogUtils;

	public DeviceCommand get(Integer id) {
		return deviceCommandDao.selectByPrimaryKey(id);
	}
	
	public Boolean switchIsOpen(Integer deviceId) {
		 DeviceCommand newestSwitchCommand = deviceCommandDao.getNewestSwitchCommand(deviceId);
		 if(newestSwitchCommand != null && newestSwitchCommand.getCommandIndex().equals(1011)){
			 return true;
		 }
		return false;
	}

	@Transactional
	public void save(Integer sysUserId, DeviceCommand t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, deviceCommandDao.selectByPrimaryKey(t.getId()), t);
			deviceCommandDao.updateByPrimaryKey(t);
		} else {
			deviceCommandDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}
	
	public List<DeviceCommand> query(DeviceCommand t, Integer page, Integer pageSize) {
		Example example = new Example(DeviceCommand.class);
		Example.Criteria criteria = example.createCriteria();
		if (t.getCommandIndex() != null) {
			criteria.andEqualTo("commandIndex", t.getCommandIndex());
		}
		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, pageSize);
		return deviceCommandDao.selectByExample(example);
	}

}
