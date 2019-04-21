package com.oty.sys.model.message;

import java.io.Serializable;
import java.util.List;

import com.oty.sys.entity.TMessage;

import lombok.Data;
import pub.types.IdText;

@Data
public class MessageData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2571378410052642455L;
	
	private TMessage tMessage; 
	private List<IdText> allUserList;
} 
