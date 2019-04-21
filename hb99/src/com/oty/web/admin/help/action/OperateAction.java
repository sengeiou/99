package com.oty.web.admin.help.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import com.oty.sys.entity.Help;
import com.oty.sys.service.HelpService;
import com.oty.web.admin.help.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("helpOperate")
@RequestMapping("/admin/help")
public class OperateAction extends BaseAction{
	
	@RequestMapping("/operate.do")
	public View save(Help help, Errors errors) {
		Validator validator = Validator.of(help, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			helpService.save(getSysUser().getId(), help);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private HelpService helpService;

}
