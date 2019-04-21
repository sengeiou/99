package com.oty.web.admin.system.config.utils; 

import pub.spring.ValidationRules;

public class ValidateUtils {
	
	public static void setRules(ValidationRules rules) { 
		rules.on("configKey").required("key必填").maxLength(50, "最大长度50")
			 .on("configValue").required("值必填").number("值为数值类型");
	}
	
}
