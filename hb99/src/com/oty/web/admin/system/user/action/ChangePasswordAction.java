package com.oty.web.admin.system.user.action;
 
import com.oty.sys.entity.SysUser; 
import com.oty.sys.model.admin.user.ChangePasswordData; 
import com.oty.web.admin.system.user.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator;
 
@Controller("adminUserChangePassword")
@RequestMapping("/admin/system/user")
@SessionAttributes("changePasswordData")
public class ChangePasswordAction extends BaseAction {

	public JsValidator createJsValidator(ChangePasswordData changePasswordData) {
		JsValidator validator = new JsValidator(changePasswordData);
		ValidateUtils.setChangePasswordRules(validator);
		return validator;
	}
	
	@RequestMapping("change_password.html")
	public void execute(Model model) {
		ChangePasswordData changePasswordData = new ChangePasswordData();
		SysUser sysUser = this.getSysUser();
		changePasswordData.setUserId(sysUser.getId()); 
		
		model.addAttribute(changePasswordData);
		model.addAttribute(createJsValidator(changePasswordData));
	}

}
