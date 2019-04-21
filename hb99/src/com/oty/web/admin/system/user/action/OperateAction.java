package com.oty.web.admin.system.user.action;
 
import com.oty.sys.model.admin.user.UserData;
import com.oty.sys.service.SysUserService;
import com.oty.util.Log4jUtil;
import com.oty.web.admin.system.user.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.functions.CryptFuncs; 
import pub.spring.ActionResult; 
import pub.spring.Validator;

@Controller("adminUserOperate")
@SessionAttributes("userData")
@RequestMapping("/admin/system/user")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(UserData userData, Errors errors) { 
		Validator validator = Validator.of(userData, errors);
		ValidateUtils.setRules(validator,userData); 
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			if (userData.getUser().getPassword() != null
					&& !"".equals(userData.getUser().getPassword())) {
				userData.getUser().setPassword(CryptFuncs.getMd5(userData.getUser().getPassword()));
			} else {
				userData.getUser().setPassword(sysUserService.get(userData.getUser().getId()).getPassword());
			}
			sysUserService.save(userData);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		} 
		return ActionResult.ok("保存成功", "query.html");
	}
	
	@RequestMapping("/update")
	public View update(Integer fileId, UserData userData, Errors errors) {
		Validator validator = Validator.of(userData, errors);
		ValidateUtils.setUpdateRules(validator); 
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try { 
			sysUserService.update(userData.getUser(), fileId);
			setSessionData(userData.getUser());
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "user_info.html");
	} 

	@Autowired
	private SysUserService sysUserService;

}
