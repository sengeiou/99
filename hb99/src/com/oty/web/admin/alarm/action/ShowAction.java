package com.oty.web.admin.alarm.action;
   
import com.oty.sys.entity.TAlarm; 
import com.oty.sys.service.AlarmService;
import com.oty.sys.service.DeviceService;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.alarm.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pub.spring.JsValidator;
import pub.types.RefData; 
 
@Controller("alarmShow")
@SessionAttributes("TAlarm")
@RequestMapping("/admin/alarm")
public class ShowAction extends BaseAction { 
	
	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(TAlarm.TABLE_NAME, TAlarm.FIELD_TYPE)); 
		refData.put("statuses", sysStatusService.list(TAlarm.TABLE_NAME, TAlarm.FIELD_STATUS)); 
		refData.put("projects", projectService.getAll());
		refData.put("devices", deviceService.getAll());
		return refData;
	}

	public JsValidator createJsValidator(TAlarm alarm) {
		JsValidator validator = new JsValidator(alarm);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		TAlarm alarm = (TAlarm) model.get(TAlarm.class.getName());
		if (alarm != null) {
			 
		} else if (id != null) {
			alarm = alarmService.get(id);
		} else {
			alarm = new TAlarm();   
		}
		model.addAttribute(alarm);

		JsValidator jsValidator = createJsValidator(alarm);
		model.addAttribute(jsValidator); 
	}

	@Autowired
	private AlarmService alarmService;   
	@Autowired
	private SysStatusService sysStatusService; 
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DeviceService deviceService;
	
}
