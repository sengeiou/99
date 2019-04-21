package com.oty.web.admin.project.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("name").required("项目名称必填").maxLength(20, "最大长度20")
		.on("provinceId").required("省份必填")
		.on("industryId").required("行业必填")
		.on("type").required("类型必填");
	} 
	
}
