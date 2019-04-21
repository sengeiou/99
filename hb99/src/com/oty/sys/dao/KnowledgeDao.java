package com.oty.sys.dao;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.Knowledge;
import com.oty.sys.entity.TAlarm;


public interface KnowledgeDao extends InsertUseGeneratedKeysMapper<Knowledge>, Mapper<Knowledge>{

}
