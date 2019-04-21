package com.oty.web.admin.system.role.utils;

import pub.spring.ValidationRules;
 
public class ValidateUtils {

	public static void setRules(ValidationRules rules) {
		rules.getNestedRules("role").on("name").required();
	}
}
