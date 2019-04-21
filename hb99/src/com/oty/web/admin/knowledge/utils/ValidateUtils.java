package com.oty.web.admin.knowledge.utils; 

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules) { 
		rules.on("title").required("标题必填").maxLength(20, "最大长度20")
			.on("type").required("类型必填")
			.on("content").required("内容必填");
//			.on("cycle").digits("周期为整数类型")
//			.on("projectId").required("所属项目必填")
//			.on("moduleId").required("所属模块必填")
//			.on("port").digits("设备端口为整数类型")
//			.on("touchPort").digits("触屏端口为整数类型");
	} 
	
}
