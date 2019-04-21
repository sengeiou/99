package com.oty.web.admin.system.notice.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oty.sys.entity.SysNotice;
import com.oty.sys.service.SysNoticeService;
import com.oty.web.admin.system.notice.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;

import pub.spring.JsValidator;

@Controller("adminNoticeShow")
@SessionAttributes("notice")
@RequestMapping("/admin/system/notice")
public class ShowAction extends BaseAction {

	public JsValidator createJsValidator(SysNotice notice) {
		JsValidator validator = new JsValidator(notice);
		ValidateUtils.setRules(validator, notice);
		return validator;
	}

	@RequestMapping("show.html")
	public void execute(ModelMap model, Integer id) {
		try {
			SysNotice notice = (SysNotice) model.get(SysNotice.class.getName());
			if (notice != null) {

			} else if (id != null) {
				notice = noticeService.get(id);
			} else {
				notice = new SysNotice();
				notice.setSysUserId(this.getUserId());
			}
			model.addAttribute(notice);
			model.addAttribute(createJsValidator(notice));
			model.put("isEdit", notice.getId() != null);
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

	@Autowired
	private SysNoticeService noticeService;

}
