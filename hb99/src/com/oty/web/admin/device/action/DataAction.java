package com.oty.web.admin.device.action; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.oty.sys.service.DeviceUploadService;
import com.oty.web.base.action.BaseAction;

@Controller("dataDevice")
@RequestMapping("/admin/device")
public class DataAction extends BaseAction {

	@RequestMapping("/data.html")
	public void execute(Integer deviceId) {
		try {  
			request.setAttribute("deviceUploads", deviceUploadService.getLast(deviceId)); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Autowired
	private DeviceUploadService deviceUploadService;
	
}
