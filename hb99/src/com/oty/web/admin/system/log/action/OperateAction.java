package com.oty.web.admin.system.log.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.oty.sys.entity.SysUpdateLog;
import com.oty.sys.service.SysUpdateLogService;
import com.oty.web.admin.system.log.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("adminLogOperate")
@SessionAttributes("sysUpdateLog")
@RequestMapping("/admin/system/log")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(SysUpdateLog sysUpdateLog, Errors errors) {
		Validator validator = Validator.of(sysUpdateLog, errors);
		ValidateUtils.setRules(validator, sysUpdateLog);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			sysUpdateLogService.save(getSysUser().getId(), sysUpdateLog);
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private SysUpdateLogService sysUpdateLogService;

}
