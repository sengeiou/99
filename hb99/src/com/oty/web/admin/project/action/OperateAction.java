package com.oty.web.admin.project.action;

import com.oty.sys.entity.TProject;
import com.oty.sys.service.ProjectService;
import com.oty.web.admin.project.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("projectOperate")
@SessionAttributes("TProject")
@RequestMapping("/admin/project")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(Integer fileId, TProject project, Errors errors) {
		Validator validator = Validator.of(project, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			projectService.save(getSysUser().getId(), fileId, project);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private ProjectService projectService;

}
