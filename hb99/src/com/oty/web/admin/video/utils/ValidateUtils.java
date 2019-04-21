package com.oty.web.admin.video.utils;

import pub.spring.ValidationRules; 

public class ValidateUtils {
	
	public static void setRules(ValidationRules rules) { 
        rules.on("projectId").required("请选择项目");
		rules.on("tel").required("手机号必填").on("tel").telno()
			 .on("contacts").required("联系人必填").maxLength(50, "最大长度50")
			 .on("address").required("地址必填").maxLength(50, "最大长度50")
			 .on("gpsX").required("经度必填").number("经度为数值类型").maxLength(20, "最大长度20")
			 .on("gpsY").required("纬度必填").number("纬度为数值类型").maxLength(20, "最大长度20")
			 .on("rtmpUrl").regex(".*rtmp.*","RTMP无效").maxLength(150, "最大长度150")
			 .on("hlsUrl").regex(".*hls.*","HLS无效").maxLength(150, "最大长度150")
			 .on("deviceSerial").maxLength(20, "最大长度20")
			 .on("verificationCode").regex("^[A-Z]{6}$", "设备机身上的六位大写字母") 
			 .on("name").required("名称必填").maxLength(25, "最大长度25");
	}
	
}
