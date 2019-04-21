package com.oty.web.admin.device.set.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.web.base.action.BaseAction;

@Controller("monitorSet")
@RequestMapping("/admin/device/set")
public class MonitorAction extends BaseAction {

	@RequestMapping("/monitor.html")
	public void execute() {
		 
	}
}
