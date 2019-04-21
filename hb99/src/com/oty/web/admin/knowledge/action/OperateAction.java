package com.oty.web.admin.knowledge.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.oty.sys.entity.Knowledge;
import com.oty.sys.service.KnowledgeService;
import com.oty.web.admin.knowledge.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("knowledgeOperate")
@SessionAttributes("Knowledge")
@RequestMapping("/admin/knowledge")
public class OperateAction extends BaseAction{
	
	@RequestMapping("/operate.do")
	public View save(Knowledge knowledge, Errors errors) {
		Validator validator = Validator.of(knowledge, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			knowledgeService.save(getSysUser().getId(), knowledge);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private KnowledgeService knowledgeService;

}
