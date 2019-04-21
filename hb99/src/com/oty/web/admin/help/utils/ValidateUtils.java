package com.oty.web.admin.help.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("title").required("标题必填").maxLength(20, "最大长度20")
			.on("type").required("类型必填")
			.on("content").required("内容必填");

	} 
	
}
