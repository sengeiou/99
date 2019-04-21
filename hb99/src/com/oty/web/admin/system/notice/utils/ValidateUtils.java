package com.oty.web.admin.system.notice.utils;
 
import com.oty.sys.entity.SysNotice; 

import pub.spring.ValidationRules;

public class ValidateUtils {
	
	public static void setRules(ValidationRules rules, SysNotice notice) { 
		rules.on("title").required("标题必填").maxLength(25, "最大长度25")
			 .on("contents").required("内容必填")
			 .on("deadline").required("截止时间必填");
	}
	
}
