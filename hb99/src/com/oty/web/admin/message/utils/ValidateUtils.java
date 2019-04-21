package com.oty.web.admin.message.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.getNestedRules("message")
			 .on("type").required("消息类型必填")
			 .on("content").required("内容必填").maxLength(250, "最大长度250");
	} 
	
}
