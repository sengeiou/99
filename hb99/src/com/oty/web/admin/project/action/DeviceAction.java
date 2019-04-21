package com.oty.web.admin.project.action; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
 
import com.oty.web.base.action.BaseAction;

@Controller("deviceSet")
@RequestMapping("/admin/project")
public class DeviceAction extends BaseAction {

	@RequestMapping("/device.html")
	public void execute() {
		 
	}
	
}
