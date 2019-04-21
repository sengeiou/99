package com.oty.web.admin.task.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.getNestedRules("maintainTask")
		     .on("name").required("参数名称必填").maxLength(20, "参数名称长度不超过20")
		     .on("deviceId").required("设备必选")
		     .on("projectId").required("项目必选");
	} 
	
}
