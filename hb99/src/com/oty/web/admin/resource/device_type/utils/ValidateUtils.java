package com.oty.web.admin.resource.device_type.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("name").required("名称必填").maxLength(20, "最大长度20");
	} 
	
}
