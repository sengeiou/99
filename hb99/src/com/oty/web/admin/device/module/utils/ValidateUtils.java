package com.oty.web.admin.device.module.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("mac").required("MAC地址必填").maxLength(20, "最大长度20")
		     .on("ip").required("IP地址必填").maxLength(15, "最大长度15");
	} 
	
}
