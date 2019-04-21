package com.oty.web.admin.system.notice.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.oty.sys.entity.SysNotice;
import com.oty.sys.service.SysNoticeService;
import com.oty.web.admin.system.notice.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;

import pub.spring.ActionResult;
import pub.spring.Validator;

@Controller("adminNoticeOperate")
@SessionAttributes("notice")
@RequestMapping("/admin/system/notice")
public class OperateAction extends BaseAction {

	@RequestMapping("/operate.do")
	public View save(SysNotice notice, Errors errors) {
		Validator validator = Validator.of(notice, errors);
		ValidateUtils.setRules(validator, notice);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			noticeService.save(getSysUser().getId(), notice);
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private SysNoticeService noticeService;

}
