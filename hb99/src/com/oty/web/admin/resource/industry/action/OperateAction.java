package com.oty.web.admin.resource.industry.action;

import com.oty.sys.entity.SysIndustry;
import com.oty.sys.service.SysIndustryService;
import com.oty.web.admin.resource.industry.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("resourceIndustryOperate")
@SessionAttributes("sysIndustry")
@RequestMapping("/admin/resource/industry")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(SysIndustry sysIndustry, Errors errors) {
		Validator validator = Validator.of(sysIndustry, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			sysIndustryService.save(getSysUser().getId(), sysIndustry);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private SysIndustryService sysIndustryService;

}
