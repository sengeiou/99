package com.oty.web.admin.device_attribute.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("attributeCode").required("数据编号必填").maxLength(50, "最大长度50")
			.on("name").required("数据名称必填").maxLength(50, "最大长度50")
			.on("deviceTypeId").required("请选择设备类型");
	} 
	
}
