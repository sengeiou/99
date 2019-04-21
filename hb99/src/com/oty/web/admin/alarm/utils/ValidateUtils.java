package com.oty.web.admin.alarm.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("sendUserId").required("发送用户必填").on("receiveUserId").required("接收用户必填");
	} 
	
}
