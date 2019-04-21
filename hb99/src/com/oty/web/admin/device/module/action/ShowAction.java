package com.oty.web.admin.device.module.action;
   
import com.oty.sys.entity.TDeviceModule;
import com.oty.sys.entity.TProject;
import com.oty.sys.service.DeviceModuleService; 
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.device.module.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator; 
import pub.types.RefData; 
 
@Controller("moduleShow") 
@SessionAttributes("TDeviceModule")
@RequestMapping("/admin/device/module")
public class ShowAction extends BaseAction {  

	@ModelAttribute
	RefData createRefData() {
		RefData refData = new RefData(); 
		refData.put("types", sysStatusService.list(TProject.TABLE_NAME, TProject.FIELD_TYPE));
		return refData;
	}

	public JsValidator createJsValidator(TDeviceModule deviceModule) {
		JsValidator validator = new JsValidator(deviceModule);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		TDeviceModule deviceModule = (TDeviceModule) model.get(TDeviceModule.class.getName());
		if (deviceModule != null) {
			 
		} else if (id != null) {
			deviceModule = deviceModuleService.get(id);
		} else {
			deviceModule = new TDeviceModule();   
		}
		model.addAttribute("TDeviceModule", deviceModule);

		JsValidator jsValidator = createJsValidator(deviceModule);
		model.addAttribute(jsValidator);  
	}

	@Autowired
	private DeviceModuleService deviceModuleService; 
	@Autowired
	private SysStatusService sysStatusService;
	
}
