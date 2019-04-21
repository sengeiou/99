package com.oty.web.admin.device.set.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.sys.entity.TDevice;
import com.oty.sys.service.DeviceCommandService;
import com.oty.sys.service.DeviceService;
import com.oty.web.base.action.BaseAction;

@Controller("controlSet")
@RequestMapping("/admin/device/set")
public class ControlAction extends BaseAction {
	
	@Autowired
	private DeviceService deviceService; 
	@Autowired
	private DeviceCommandService deviceCommandService; 
	
	@RequestMapping("/control.html")
	public void execute(ModelMap model, Integer deviceId) { 
		try {
			TDevice device = deviceService.get(deviceId);
			model.addAttribute("isOpen", deviceCommandService.switchIsOpen(device.getId())); 
			model.addAttribute("device", device); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
