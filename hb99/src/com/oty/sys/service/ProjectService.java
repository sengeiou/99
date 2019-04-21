package com.oty.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.ProjectDao; 
import com.oty.sys.entity.TFileInfo;
import com.oty.sys.entity.TProject;
import com.oty.util.OperLogUtils;
import com.oty.sys.dao.FileDao;
import com.oty.sys.dao.FileInfoDao;

import pub.functions.StrFuncs;
import pub.types.IdText;

@Service
@Transactional(readOnly = true)
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private OperLogUtils operLogUtils;
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private FileDao fileDao;

	@Transactional
	public void save(Integer sysUserId, Integer fileId, TProject t) {
		if (t.getId() != null) {
			operLogUtils.updateLog(sysUserId, projectDao.selectByPrimaryKey(t.getId()), t);
			projectDao.updateByPrimaryKey(t);
		} else {
			projectDao.insert(t);
			operLogUtils.insertLog(sysUserId, t);
		}
		if (fileId != null) {
			TFileInfo fileInfo = fileInfoDao.getFileInfo(TProject.TABLE_NAME, t.getId(), 0);
			if (fileInfo != null) {
				if (fileInfo.getFileId() != null) { 
					fileDao.deleteByExample(fileInfo.getFileId());
				}
				fileInfo.setFileId(fileId);
				fileInfoDao.insertUseGeneratedKeys(fileInfo);
			} else {
				fileInfo = new TFileInfo();
				fileInfo.setReferenceId(t.getId());
				fileInfo.setTableName(TProject.TABLE_NAME);
				fileInfo.setType(0);
				fileInfo.setFileId(fileId);
				fileInfoDao.insertUseGeneratedKeys(fileInfo);
			}
		}
	}

	public TProject get(Integer id) {
		return projectDao.selectByPrimaryKey(id);
	}

	public List<TProject> query(TProject t, int page, int pageSize) {
		Example example = new Example(TProject.class);
		Example.Criteria criteria = example.createCriteria();
		if (StrFuncs.notEmpty(t.getName())) {
			criteria.andLike("name", StrFuncs.anyLike(t.getName()));
		}
		if (t.getIndustryId() != null) {
			criteria.andEqualTo("industryId", t.getIndustryId());
		}
		if (t.getType() != null) {
			criteria.andEqualTo("type", t.getType());
		}
		if (t.getProvinceId() != null) {
			criteria.andEqualTo("provinceId", t.getProvinceId());
		}
		if (t.getCityId() != null) {
			criteria.andEqualTo("cityId", t.getCityId());
		}
		if (t.getAreaId() != null) {
			criteria.andEqualTo("areaId", t.getAreaId());
		}
		example.setOrderByClause(" id ASC ");
		PageHelper.startPage(page, pageSize);
		return projectDao.selectByExample(example);
	}

	public List<IdText> getAll() {
		return projectDao.getAll();
	}

}
