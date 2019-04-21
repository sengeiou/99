package com.oty.web.admin.resource.device_type.action;
   
import com.oty.sys.entity.TDeviceType;
import com.oty.sys.service.DeviceTypeService; 
import com.oty.web.admin.resource.device_type.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pub.spring.JsValidator; 
 
@Controller("deviceTypeShow")
@SessionAttributes("TDeviceType")
@RequestMapping("/admin/resource/device_type")
public class ShowAction extends BaseAction { 

	public JsValidator createJsValidator(TDeviceType deviceType) {
		JsValidator validator = new JsValidator(deviceType);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		TDeviceType deviceType = (TDeviceType) model.get(TDeviceType.class.getName());
		if (deviceType != null) {
			 
		} else if (id != null) {
			deviceType = deviceTypeService.get(id);
		} else {
			deviceType = new TDeviceType();  
		}
		model.addAttribute(deviceType); 
		JsValidator jsValidator = createJsValidator(deviceType);
		model.addAttribute(jsValidator); 
	}

	@Autowired
	private DeviceTypeService deviceTypeService;    
	
}
