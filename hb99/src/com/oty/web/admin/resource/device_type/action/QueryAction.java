package com.oty.web.admin.resource.device_type.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
 
import com.github.pagehelper.PageInfo;  
import com.oty.sys.entity.TDeviceType; 
import com.oty.sys.service.DeviceTypeService; 
import com.oty.web.base.action.BaseAction; 
 
@Controller("deviceTypeQuery")
@RequestMapping("/admin/resource/device_type")
public class QueryAction extends BaseAction {  
 
	@RequestMapping("/query.html")
	public void execute(TDeviceType deviceType, @RequestParam(value="page",defaultValue="1")Integer page) {    
		try { 
			List<TDeviceType> pagehelper = deviceTypeService.query(deviceType, page);  
			request.setAttribute("pagehelper", new PageInfo<TDeviceType>(pagehelper)); 
			request.setAttribute("deviceType", deviceType); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private DeviceTypeService deviceTypeService;   

}
