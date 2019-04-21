package com.oty.sys.dao;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.TOrg;

public interface OrgDao extends InsertUseGeneratedKeysMapper<TOrg>, Mapper<TOrg> {

}
