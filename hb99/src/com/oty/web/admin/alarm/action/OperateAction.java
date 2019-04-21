package com.oty.web.admin.alarm.action;

import com.oty.sys.entity.TAlarm; 
import com.oty.sys.service.AlarmService; 
import com.oty.web.admin.alarm.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("alarmOperate")
@SessionAttributes("TAlarm")
@RequestMapping("/admin/alarm")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(TAlarm TAlarm, Errors errors) {
		Validator validator = Validator.of(TAlarm, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			alarmService.save(getSysUser().getId(), TAlarm);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private AlarmService alarmService;

}
