package com.oty.sys.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.oty.sys.dao.SysAreaDao;
import com.oty.sys.entity.SysArea;

import pub.types.IdText;

@Service("sysAreaService")
@Transactional(readOnly = true)
public class SysAreaService {

	@Autowired
	private SysAreaDao sysAreaDao;

	public List<IdText> listSubAreas(Integer parentId) {
		if (parentId == null) {
			return Collections.emptyList();
		}
		return sysAreaDao.listSubAreas(parentId);
	}

	public SysArea get(Integer id) {
		return sysAreaDao.selectByPrimaryKey(id);
	}

	public SysArea getParentArea(Integer areaId) {
		if (areaId == null) {
			return null;
		}
		return sysAreaDao.getParent(areaId);
	}

	public List<IdText> getRoots() {
		return sysAreaDao.getRoots();
	}

	public List<SysArea> findParents(Integer parentId) {
		return sysAreaDao.findParents(parentId);
	}

	@Transactional
	public void save(SysArea t) {
		if (t.getId() != null) {
			sysAreaDao.updateByPrimaryKey(t);
		} else {
			sysAreaDao.insert(t);
		}
	}

	public List<SysArea> query(Integer page) {
		PageHelper.startPage(page, 100);
		return sysAreaDao.queryList();
	}

	public List<SysArea> findByLevel(Integer level) {
		return sysAreaDao.findByLevel(level);
	}
}
