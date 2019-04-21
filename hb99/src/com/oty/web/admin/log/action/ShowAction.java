package com.oty.web.admin.log.action;
   
import com.oty.sys.entity.SysOperLog; 
import com.oty.sys.service.SysOperLogService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes; 
 
@Controller("logShow")
@SessionAttributes("SysOperLog")
@RequestMapping("/admin/log")
public class ShowAction extends BaseAction { 
	
	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("operTypes", sysStatusService.list(SysOperLog.TABLE_NAME, SysOperLog.FIELD_OPER_TYPE)); 
		return refData;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		SysOperLog sysOperLog = (SysOperLog) model.get(SysOperLog.class.getName());
		if (sysOperLog != null) {
			 
		} else if (id != null) {
			sysOperLog = sysOperLogService.get(id);
		} else {
			sysOperLog = new SysOperLog();   
		}
		model.addAttribute("SysOperLog",sysOperLog); 
	}

	@Autowired
	private SysOperLogService sysOperLogService;   
	@Autowired
	private SysStatusService sysStatusService; 
	
}
