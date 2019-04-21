package com.oty.web.admin.alarm.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.RefData;
 
import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TAlarm; 
import com.oty.sys.service.AlarmService;
import com.oty.sys.service.DeviceService;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction; 
 
@Controller("alarmQuery")
@RequestMapping("/admin/alarm")
public class QueryAction extends BaseAction { 

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(TAlarm.TABLE_NAME, TAlarm.FIELD_TYPE)); 
		refData.put("statuses", sysStatusService.list(TAlarm.TABLE_NAME, TAlarm.FIELD_STATUS)); 
		refData.put("projects", projectService.getAll());
		refData.put("devices", deviceService.getAll());
		return refData;
	}
 
	@RequestMapping("/query.html")
	public void execute(TAlarm alarm, @RequestParam(value="page",defaultValue="1")Integer page) {  
		try {
			List<TAlarm> pagehelper = alarmService.query(alarm, page);  
			request.setAttribute("pagehelper", new PageInfo<TAlarm>(pagehelper)); 
			request.setAttribute("alarm", alarm);  
		} catch (Exception e) {
			e.printStackTrace();
		} 
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
