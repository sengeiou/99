package com.oty.web.admin.attribute.action;

import com.oty.sys.entity.TMaintainAttribute;
import com.oty.sys.service.MaintainAttributeService;
import com.oty.web.admin.attribute.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("attributeOperate")
@SessionAttributes("TMaintainAttribute")
@RequestMapping("/admin/attribute")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(TMaintainAttribute TMaintainAttribute, Errors errors) {
		Validator validator = Validator.of(TMaintainAttribute, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			maintainAttributeService.save(getSysUser().getId(), TMaintainAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private MaintainAttributeService maintainAttributeService;

}
