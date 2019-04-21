package com.oty.web.admin.device.action;

import com.oty.sys.entity.TDevice;
import com.oty.sys.service.DeviceService;
import com.oty.web.admin.device.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("deviceOperate")
@SessionAttributes("TDevice")
@RequestMapping("/admin/device")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(TDevice device, Errors errors) {
		Validator validator = Validator.of(device, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			deviceService.save(getSysUser().getId(), device);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private DeviceService deviceService;

}
