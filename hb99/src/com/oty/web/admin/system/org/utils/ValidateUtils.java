package com.oty.web.admin.system.org.utils; 

import pub.spring.ValidationRules;

public class ValidateUtils {
	
	public static void setRules(ValidationRules rules) { 
		rules.on("name").required("机构名称必填").maxLength(25, "最大长度25")
		     .on("remark").maxLength(100, "最大长度100");
	}
	
}
