package com.oty.web.admin.device_attribute.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.oty.sys.entity.DeviceAttribute;
import com.oty.sys.service.DeviceAttributeService;
import com.oty.web.admin.device_attribute.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.ActionResult;
import pub.spring.RuleNames;
import pub.spring.Validator;

@Controller("deviceAttributeOperate")
@SessionAttributes("deviceAttribute")
@RequestMapping("/admin/device_attribute")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(DeviceAttribute deviceAttribute, Errors errors) {
		Validator validator = Validator.of(deviceAttribute, errors);
		ValidateUtils.setRules(validator);
		if(!errors.hasErrors()){
			if(deviceAttribute.getId() == null && deviceAttributeService.findByName(deviceAttribute.getName()) != null){
				errors.rejectValue("name", RuleNames.general, "名称已存在");
			}else if(deviceAttribute.getId() != null && deviceAttributeService.findByNameAndExcludeId(deviceAttribute.getName(), deviceAttribute.getId()) != null){
				errors.rejectValue("name", RuleNames.general, "名称已存在");
			}
		}
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			deviceAttributeService.save(getSysUser().getId(), deviceAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private DeviceAttributeService deviceAttributeService;

}
