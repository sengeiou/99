package com.oty.web.admin.device_attribute.action;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oty.sys.entity.DeviceAttribute;
import com.oty.sys.service.DeviceAttributeService;
import com.oty.sys.service.DeviceTypeService;
import com.oty.web.admin.device_attribute.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.JsValidator;
import pub.types.RefData; 
 
@Controller("deviceAttributeShow") 
@SessionAttributes("deviceAttribute")
@RequestMapping("/admin/device_attribute")
public class ShowAction extends BaseAction {  

	@ModelAttribute
	RefData createRefData() {
		RefData refData = new RefData(); 
		refData.put("deviceTypes", deviceTypeService.getAll());
		return refData;
	}

	public JsValidator createJsValidator(DeviceAttribute deviceAttribute) {
		JsValidator validator = new JsValidator(deviceAttribute);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		DeviceAttribute deviceAttribute = (DeviceAttribute) model.get(DeviceAttribute.class.getName());
		if (deviceAttribute != null) {
			 
		} else if (id != null) {
			deviceAttribute = deviceAttributeService.get(id);
		} else {
			deviceAttribute = new DeviceAttribute();   
		}
		model.addAttribute("deviceAttribute", deviceAttribute); 
		JsValidator jsValidator = createJsValidator(deviceAttribute);
		model.addAttribute(jsValidator);  
	}

	@Autowired
	private DeviceAttributeService deviceAttributeService; 
	@Autowired
	private DeviceTypeService deviceTypeService;
	
}
