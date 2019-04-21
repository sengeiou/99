package com.oty.sys.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.SysArea; 

import pub.types.IdText;

public interface SysAreaDao extends InsertUseGeneratedKeysMapper<SysArea>, Mapper<SysArea> {

	public List<IdText> listSubAreas(Integer parentId);

	public SysArea getParent(Integer areaId);

	public SysArea getById(Integer id);

	public List<IdText> getRoots();

	public List<SysArea> findParents(Integer parentId);

	public List<SysArea> queryList();

	public List<SysArea> findByLevel(Integer level);

}
