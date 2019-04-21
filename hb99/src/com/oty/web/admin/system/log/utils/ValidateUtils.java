package com.oty.web.admin.system.log.utils;
 
import com.oty.sys.entity.SysUpdateLog;

import pub.spring.ValidationRules;

public class ValidateUtils {
	
	public static void setRules(ValidationRules rules, SysUpdateLog sysUpdateLog) { 
		rules.on("version").required("版本号必填").maxLength(25, "最大长度25")
			 .on("content").required("版本发布内容必填")
			 .on("releaseTime").required("发布时间必填");
	}
	
}
