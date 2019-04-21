package com.oty.web.admin.resource.industry.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("name").required("姓名必填").maxLength(20, "最大长度20");
	} 
	
}
