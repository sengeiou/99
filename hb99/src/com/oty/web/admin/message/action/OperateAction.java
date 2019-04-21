package com.oty.web.admin.message.action;

import com.oty.sys.model.admin.message.MessageData;
import com.oty.sys.service.MessageService;
import com.oty.web.admin.message.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("messageOperate")
@SessionAttributes("messageData")
@RequestMapping("/admin/message")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(MessageData messageData,Errors errors) {
		Validator validator = Validator.of(messageData, errors);
		ValidateUtils.setRules(validator);
			if (errors.hasErrors()) {
				return ActionResult.formView();
			}
			try {
				messageService.save(getSysUser().getId(), messageData);
			} catch (Exception e) {
				e.printStackTrace();
				return ActionResult.error("系统异常，请联系管理员");
			}
		
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private MessageService messageService;

}
