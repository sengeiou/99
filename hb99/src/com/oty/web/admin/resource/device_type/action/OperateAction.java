package com.oty.web.admin.resource.device_type.action;

import com.oty.sys.entity.TDeviceType;
import com.oty.sys.service.DeviceTypeService;
import com.oty.web.admin.resource.device_type.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;


@Controller("deviceTypeOperate")
@SessionAttributes("TDeviceType")
@RequestMapping("/admin/resource/device_type")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(TDeviceType TDeviceType, Errors errors) {
		Validator validator = Validator.of(TDeviceType, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			deviceTypeService.save(getSysUser().getId(), TDeviceType);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private DeviceTypeService deviceTypeService;

}
