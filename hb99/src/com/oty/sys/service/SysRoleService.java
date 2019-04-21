package com.oty.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper; 
import com.oty.sys.dao.SysModuleRoleRelDao;
import com.oty.sys.dao.SysRoleDao;
import com.oty.sys.dao.SysUserRoleRelDao;  
import com.oty.sys.entity.SysModuleRoleRel;
import com.oty.sys.entity.SysRole;  
import com.oty.sys.model.admin.role.RoleData;
import com.oty.util.OperLogUtils;

import pub.functions.BeanFuncs;
import pub.functions.StrFuncs;
import pub.types.IdText;

@Service
@Transactional(readOnly = true) 
public class SysRoleService {

	@Autowired
	private SysRoleDao roleDao;
	@Autowired
	private SysModuleRoleRelDao sysModuleroleDao;
	@Autowired
	private SysUserRoleRelDao sysUserroleDao; 
	@Autowired
	private OperLogUtils operLogUtils;
 
	@Transactional
	@SuppressWarnings({"unchecked", "rawtypes"}) 
	public Integer save(Integer sysUserId, RoleData roleData) {
		roleData = BeanFuncs.deepClone(roleData);
		SysRole role = roleData.getRole();
		if (role.getId() != null) {
			operLogUtils.updateLog(sysUserId, roleDao.selectByPrimaryKey(role.getId()), role);
			roleDao.updateByPrimaryKey(role);
		} else {
			roleDao.save(role);
			operLogUtils.insertLog(sysUserId, role);
		}
		Integer roleId = role.getId();

		List<Integer> moduleIds = roleData.getModuleIds();
		List<Integer> oriModuleIds = sysModuleroleDao.getRoleModuleIds(roleId);
		HashSet<Integer> oriModuleIdSet = new HashSet<Integer>(oriModuleIds);
		HashSet<Integer> moduleIdSet = new HashSet<Integer>(moduleIds);

		moduleIdSet.removeAll(oriModuleIdSet);
		for (Integer moduleId : moduleIdSet) {
			SysModuleRoleRel rel = new SysModuleRoleRel();
			rel.setModuleId(moduleId);
			rel.setRoleId(roleId);
			sysModuleroleDao.insert(rel);
		}
		oriModuleIdSet.removeAll(moduleIds);  

		if (roleId != null && oriModuleIdSet.size()>0) { 
			Example example = new Example(SysModuleRoleRel.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("roleId", roleId); 
			List list = new ArrayList<Integer>(oriModuleIdSet); 
			criteria.andIn("moduleId", list); 
			sysModuleroleDao.deleteByExample(example);
		}

		return roleId;
	}

	@Transactional
	@CacheEvict(value="defaultCache",key="'SysRole_id_'+#id")  
	public void del(Integer id) {
		sysUserroleDao.deleteByPrimaryKey(id);
		sysModuleroleDao.logicDeleteByRoleId(id);
		roleDao.deleteByPrimaryKey(id);
	}
 
	public RoleData load(Integer id) {
		RoleData data = new RoleData();
		SysRole role = roleDao.selectByPrimaryKey(id);
		data.setRole(role);
		List<Integer> moduleIds = sysModuleroleDao.getRoleModuleIds(id);
		data.setModuleIds(moduleIds);
		return data;
	}

	public List<IdText> list() {
		return roleDao.list();
	}
 
	public List<SysRole> query(SysRole sysRole, Integer page) { 
		Example example = new Example(SysRole.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysRole.getName())) {
			criteria.andLike("name", StrFuncs.anyLike(sysRole.getName()));
		}
		PageHelper.startPage(page, 10); 
		return roleDao.selectByExample(example);
	}  
	
}
