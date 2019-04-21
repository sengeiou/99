package com.oty.web.admin.system.config.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.oty.sys.entity.SysConfig;
import com.oty.sys.service.SysConfigService;
import com.oty.web.admin.system.config.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("configOperate")
@SessionAttributes("sysConfig")
@RequestMapping("/admin/system/config")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(SysConfig sysConfig, Errors errors) {
		Validator validator = Validator.of(sysConfig, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			sysConfigService.save(getSysUser().getId(), sysConfig);
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private SysConfigService sysConfigService;

}
