package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.DeviceAttributeDao;
import com.oty.sys.entity.DeviceAttribute;
import com.oty.util.OperLogUtils;

import pub.functions.StrFuncs;

@Service("deviceAttributeService")
@Transactional(readOnly = true)
public class DeviceAttributeService {

	@Autowired
	private DeviceAttributeDao deviceAttribute;
	@Autowired
	private OperLogUtils operLogUtils;

	public DeviceAttribute get(Integer id) {
		return deviceAttribute.selectByPrimaryKey(id);
	}
	
	public DeviceAttribute findByName(String name) {
		return deviceAttribute.findByName(name);
	}
	public DeviceAttribute findByNameAndExcludeId(String name,Integer excludeId) {
		return deviceAttribute.findByNameAndExcludeId(name,excludeId);
	}

	@Transactional
	public void save(Integer sysUserId, DeviceAttribute t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, deviceAttribute.selectByPrimaryKey(t.getId()), t);
			deviceAttribute.updateByPrimaryKey(t);
		} else {
			deviceAttribute.insertUseGeneratedKeys(t);
			operLogUtils.insertLog(sysUserId, t);
		}
	}

	public List<DeviceAttribute> query(DeviceAttribute t, Integer page) {
		Example example = new Example(DeviceAttribute.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", StrFuncs.anyLike(t.getName()));
		}
		if (t.getAttributeCode() != null) {
			criteria.andLike("attributeCode", StrFuncs.anyLike(t.getAttributeCode()));
		}

		example.setOrderByClause(" id DESC ");
		PageHelper.startPage(page, 10);
		return deviceAttribute.selectByExample(example);
	}


}
