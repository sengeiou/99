package com.oty.web.admin.device.module.action;
  
import com.oty.sys.entity.TDeviceModule; 
import com.oty.sys.service.DeviceModuleService; 
import com.oty.web.admin.device.module.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;
 
import pub.spring.ActionResult; 
import pub.spring.Validator;

@Controller("moduleOperate") 
@SessionAttributes("TDeviceModule")
@RequestMapping("/admin/device/module")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(TDeviceModule TDeviceModule, Errors errors) { 
		Validator validator = Validator.of(TDeviceModule, errors);
		ValidateUtils.setRules(validator); 
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try { 
			deviceModuleService.save(getSysUser().getId(), TDeviceModule);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		} 
		return ActionResult.ok("保存成功", "query.html");
	} 

	@Autowired
	private DeviceModuleService deviceModuleService; 

}
