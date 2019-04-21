package com.oty.sys.dao;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.SysAd;
import com.oty.sys.entity.TProject;

public interface SysAdDao extends InsertUseGeneratedKeysMapper<TProject>, Mapper<SysAd> {

}
