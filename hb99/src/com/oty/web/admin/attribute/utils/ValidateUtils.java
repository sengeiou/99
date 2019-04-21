package com.oty.web.admin.attribute.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("name").required("参数名称必填").maxLength(20, "参数名称长度不超过20").on("type").required("类型必填");
	} 
	
}
