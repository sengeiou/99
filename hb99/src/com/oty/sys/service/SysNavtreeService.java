package com.oty.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.oty.sys.dao.SysNavtreeDao;
import com.oty.sys.dao.SysUserDao;
import com.oty.sys.entity.SysNavtree;
import com.oty.sys.model.admin.NavtreeData;
 
@Service
@Transactional(readOnly = true)
public class SysNavtreeService  { 
	
	@Autowired
	private SysNavtreeDao navtreeDao; 
	@Autowired
	private SysUserDao sysUserDao;
 
	public NavtreeData getUserNavTree(Integer userId) {
		Map<Integer, NavtreeData> dataMap = new HashMap<Integer, NavtreeData>();
		NavtreeData rootData = new NavtreeData();
		rootData.setId(0);
		dataMap.put(0, rootData);

		List<SysNavtree> navtrees;
		if (sysUserDao.isManager(userId).intValue()>0) {
			navtrees = navtreeDao.getAllVisible("id");
		} else {
			navtrees = navtreeDao.listVisibleByUser(userId);
		} 
		
		for (SysNavtree navtree : navtrees) {
			NavtreeData data = dataMap.get(navtree.getId());
			if (data == null) {
				data = new NavtreeData();
				dataMap.put(navtree.getId(), data);
			}
			data.setId(navtree.getId());
			data.setName(navtree.getName());
			data.setModuleId(navtree.getModuleId());
			data.setLink(navtree.getLink());
			data.setIcon(navtree.getIcon());
			data.setCode(navtree.getCode());

			NavtreeData parentData = dataMap.get(navtree.getParentId());
			if (parentData == null) {
				parentData = new NavtreeData();
				parentData.setId(navtree.getParentId());
				parentData.setSubDatas(new ArrayList<NavtreeData>());
				dataMap.put(navtree.getParentId(), parentData);
			}
			List<NavtreeData> subDatas = parentData.getSubDatas();
			if (subDatas == null) {
				subDatas = new ArrayList<NavtreeData>();
				parentData.setSubDatas(subDatas);
			}
			subDatas.add(data);
		}
		return rootData;
	} 
	
}
