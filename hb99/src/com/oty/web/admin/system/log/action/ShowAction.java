package com.oty.web.admin.system.log.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes; 

import com.oty.sys.entity.SysUpdateLog;
import com.oty.sys.service.SysUpdateLogService;
import com.oty.web.admin.system.log.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;
 
import pub.spring.JsValidator; 

@Controller("adminLogShow")
@SessionAttributes("sysUpdateLog")
@RequestMapping("/admin/system/log")
public class ShowAction extends BaseAction {

	public JsValidator createJsValidator(SysUpdateLog sysUpdateLog) {
		JsValidator validator = new JsValidator(sysUpdateLog);
		ValidateUtils.setRules(validator, sysUpdateLog);
		return validator;
	}

	@RequestMapping("show.html")
	public void execute(ModelMap model, Integer id) {
		try {
  			SysUpdateLog sysUpdateLog = (SysUpdateLog) model.get(SysUpdateLog.class.getName());
			if (sysUpdateLog != null) {

			} else if (id != null) {
				sysUpdateLog = sysUpdateLogService.get(id);
			} else {
				sysUpdateLog = new SysUpdateLog();
				sysUpdateLog.setSysUserId(this.getUserId());
			}
			model.addAttribute(sysUpdateLog);
			model.addAttribute(createJsValidator(sysUpdateLog)); 
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	} 

	@Autowired
	private SysUpdateLogService sysUpdateLogService;

}
