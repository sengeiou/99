package com.oty.web.admin.device.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("deviceid").required("设备id必填").maxLength(30, "最大长度30")
		    .on("name").required("设备名称必填").maxLength(20, "最大长度20")
			.on("code").required("设备唯一标识码必填").maxLength(50, "最大长度50")
			.on("typeId").required("设备类型必填")
			.on("property").required("属性必填")
			.on("cycle").digits("周期为整数类型")
			.on("projectId").required("所属项目必填")
			.on("moduleId").required("所属模块必填")
			.on("port").digits("设备端口为整数类型")
			.on("ip").maxLength(25, "最大长度25")
			.on("touchPort").digits("触屏端口为整数类型")
			.on("touchIp").maxLength(25, "最大长度25");
	} 
	
}
