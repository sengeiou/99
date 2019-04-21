package com.oty.web.admin.system.user.utils;

import com.oty.sys.model.admin.user.UserData;

import pub.spring.ValidationRules;
 
public class ValidateUtils { 

	public static void setRules(ValidationRules rules, UserData userData) {
		if (userData.getUser().getId() == null) {
			rules.getNestedRules("user").on("password").required("密码必填").maxLength(15, "最大长度15");
		}
		rules.getNestedRules("user")
			 .on("account").required("账号必填").maxLength(15, "最大长度15")
			 .on("name").required("姓名必填").maxLength(10, "最大长度10")
			 .on("mobile").required("手机号必填").telno("手机号不合法").maxLength(11, "最大长度11");
	}

	public static void setChangePasswordRules(ValidationRules rules) { 
		rules.on("oldPassword").required("请输入原有密码").on("newPassword").required(
				"请输入新密码").on("confirmPassword").required("请输入确认密码");
	}

	public static void setUpdateRules(ValidationRules rules) { 
		rules.getNestedRules("user")
		     .on("name").required("姓名必填").maxLength(10, "最大长度10")
			 .on("mobile").required("手机号码必填").telno("手机号不合法").maxLength(11, "最大长度11")
			 .on("provinceId").required("省必选")
			 .on("cityId").required("市必选")
			 .on("areaId").required("区必选");
	}
	
}
