package com.oty.web.admin.task.action;
     
import com.oty.sys.model.admin.task.MaintainTaskData;
import com.oty.sys.service.MaintainTaskService;
import com.oty.web.admin.task.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;
 
import pub.spring.ActionResult; 
import pub.spring.Validator;

@Controller("taskOperate") 
@SessionAttributes("maintainTaskData")
@RequestMapping("/admin/task")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(MaintainTaskData maintainTaskData, Errors errors) { 
		Validator validator = Validator.of(maintainTaskData, errors);
		ValidateUtils.setRules(validator); 
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try { 
			maintainTaskService.save(maintainTaskData);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		} 
		return ActionResult.ok("保存成功", "query.html");
	} 

	@Autowired
	private MaintainTaskService maintainTaskService; 

}
