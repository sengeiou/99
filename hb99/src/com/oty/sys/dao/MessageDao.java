package com.oty.sys.dao; 

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.special.InsertUseGeneratedKeysMapper;
import com.oty.sys.entity.TMessage;

import pub.types.IdText;

public interface MessageDao extends InsertUseGeneratedKeysMapper<TMessage>, Mapper<TMessage> {
	
	public List<IdText> exceptMe(Integer id);
	
	public List<TMessage> getAboutMe(@Param("tMessage") TMessage tMessage,@Param("sysUserId")Integer sysUserId);
  
}
