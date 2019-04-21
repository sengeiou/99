package com.oty.web.admin.video.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.oty.sys.entity.TVideoMonitor;
import com.oty.sys.service.VideoMonitorService;
import com.oty.web.admin.video.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.ActionResult; 
import pub.spring.Validator;

@Controller("videoOperate")
@SessionAttributes("TVideoMonitor")
@RequestMapping("/admin/video")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(TVideoMonitor TVideoMonitor, Errors errors) {
		Validator validator = Validator.of(TVideoMonitor, errors);
		ValidateUtils.setRules(validator);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			videoMonitorService.save(getSysUser().getId(), TVideoMonitor);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private VideoMonitorService videoMonitorService;

}
