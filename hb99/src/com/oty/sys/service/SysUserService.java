package com.oty.sys.service;
 
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper; 
import com.oty.sys.dao.FileDao;
import com.oty.sys.dao.FileInfoDao; 
import com.oty.sys.dao.SysUserDao;
import com.oty.sys.dao.SysUserRoleRelDao;
import com.oty.sys.entity.SysUser;
import com.oty.sys.entity.SysUserRoleRel;
import com.oty.sys.entity.TFile;
import com.oty.sys.entity.TFileInfo; 
import com.oty.sys.model.admin.user.ChangePasswordData;
import com.oty.sys.model.admin.user.UserData;
import com.oty.util.OperLogUtils;

import pub.functions.BeanFuncs;
import pub.functions.StrFuncs;
import pub.types.IdText; 

@Service("sysUserService")
@Transactional(readOnly = true)
public class SysUserService {

	@Autowired
	private SysUserRoleRelDao sysUserRoleRelDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private FileDao fileDao; 
	@Autowired
	private OperLogUtils operLogUtils;

	public SysUser getByAccount(String account) {
		return sysUserDao.getByAccount(account);
	}

	@Transactional
	public SysUser login(String account, String password) throws Exception {
		SysUser sysUser = sysUserDao.getByAccount(account);
		if (sysUser == null) {
			throw new Exception("用户不存在");
		}
		if (sysUser.getStatus().intValue() != 1) {
			throw new Exception("用户无效");
		}
		if (!StrFuncs.equalsIgnoreNullAndCase(sysUser.getPassword(), password)) {
			throw new Exception("用户名/密码无效");
		}
		sysUserDao.updateByPrimaryKey(sysUser);
		return sysUser;
	}

	@Transactional
	@CachePut(value = "defaultCache", key = "'sysUser_id_'+#sysUser.id")
	public void save(Integer sysUserId, SysUser sysUser) {
		if (sysUser.getId() != null) {
			operLogUtils.updateLog(sysUserId, sysUserDao.selectByPrimaryKey(sysUser.getId()), sysUser);
			sysUserDao.updateByPrimaryKey(sysUser);
		} else {
			sysUserDao.insert(sysUser);
			operLogUtils.insertLog(sysUserId, sysUser);
		}
	}

	@Cacheable(value = "defaultCache", key = "'sysUser_id_'+#id")
	public SysUser get(Integer id) {
		return sysUserDao.selectByPrimaryKey(id);
	}

	@Cacheable(value = "defaultCache", key = "'UserData_id_'+#id")
	public UserData load(Integer id) {
		UserData userData = new UserData();
		userData.setUser(sysUserDao.selectByPrimaryKey(id));
		userData.setRoleIds(sysUserRoleRelDao.getUserRoleIds(id));
		return userData;
	}

	@Transactional
	public Integer save(UserData userData) {
		userData = BeanFuncs.deepClone(userData);
		if (userData.getRoleIds() == null) {
			userData.setRoleIds(Collections.<Integer>emptyList());
		}
		SysUser user = userData.getUser();
		if (user.getId() != null) {
			sysUserDao.updateByPrimaryKey(user);
		} else {
			sysUserDao.insert(user);
		}
		Integer id = user.getId();

		List<Integer> roleIds = userData.getRoleIds();
		List<Integer> oriRoleIds = sysUserRoleRelDao.getUserRoleIds(id);
		HashSet<Integer> oriRoleIdSet = new HashSet<Integer>(oriRoleIds);
		HashSet<Integer> roleIdSet = new HashSet<Integer>(roleIds);

		roleIdSet.removeAll(oriRoleIdSet);
		for (Integer roleId : roleIdSet) {
			SysUserRoleRel rel = new SysUserRoleRel();
			rel.setUserId(id);
			rel.setRoleId(roleId);
			sysUserRoleRelDao.insert(rel);
		}
		oriRoleIdSet.removeAll(roleIds);
		sysUserRoleRelDao.delete2(id, oriRoleIdSet);
		return id;
	}

	@Transactional
	@CachePut(value = "defaultCache", key = "'sysUser_id_'+#sysUser.id")
	public void update(SysUser sysUser, Integer fileId) {
		sysUserDao.updateByPrimaryKey(sysUser);
		if (fileId != null) {
			TFileInfo fileInfo = fileInfoDao.getFileInfo(SysUser.TABLE_NAME, sysUser.getId(), 0);
			if (fileInfo != null) {
				if (fileInfo.getFileId() != null) {
					TFile file = fileDao.selectByPrimaryKey(fileInfo.getFileId());
					fileDao.deleteByPrimaryKey(file);
				}
				fileInfo.setFileId(fileId);
				fileInfoDao.updateByPrimaryKey(fileInfo);
			} else {
				fileInfo = new TFileInfo();
				fileInfo.setReferenceId(sysUser.getId());
				fileInfo.setTableName(SysUser.TABLE_NAME);
				fileInfo.setType(0);
				fileInfo.setFileId(fileId);
				fileInfoDao.insert(fileInfo);
			}
		}
	}

	public List<SysUser> query(SysUser sysUser, Integer page) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(sysUser.getName())) {
			criteria.andLike("name", StrFuncs.anyLike(sysUser.getName()));
		}
		example.setOrderByClause("id DESC ");
		PageHelper.startPage(page, 10);
		return sysUserDao.selectByExample(example);
	}

	@Transactional
	public void changePassword(ChangePasswordData data) {
		SysUser user = sysUserDao.selectByPrimaryKey(data.getUserId());
		user.setPassword(data.getNewPassword());
		sysUserDao.updateByPrimaryKey(user);
	}
	
	public List<IdText> getAll() {
		return sysUserDao.getAll();
	}

}
