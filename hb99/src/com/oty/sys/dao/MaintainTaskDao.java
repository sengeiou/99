package com.oty.sys.dao;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.TMaintainTask; 

public interface MaintainTaskDao extends InsertUseGeneratedKeysMapper<TMaintainTask>, Mapper<TMaintainTask> {
 
}
