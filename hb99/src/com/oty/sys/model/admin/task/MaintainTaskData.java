package com.oty.sys.model.admin.task;

import java.io.Serializable;
import java.util.List;

import com.oty.sys.entity.TMaintainTask; 

import lombok.Getter;
import lombok.Setter;

public class MaintainTaskData implements Serializable {
 
	private static final long serialVersionUID = 4523534294260151093L;
	@Getter
	@Setter
	private TMaintainTask maintainTask;
	@Getter
	@Setter
	private List<MaintainAttributeValue> maintainAttributeValues; 
	@Getter
	@Setter
	private List<Integer> maintainAttributeList; 

}
