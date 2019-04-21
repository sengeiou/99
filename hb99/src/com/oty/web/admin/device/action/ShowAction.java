package com.oty.web.admin.device.action;
   
import com.oty.sys.entity.TDevice;
import com.oty.sys.service.DeviceModuleService;
import com.oty.sys.service.DeviceService;
import com.oty.sys.service.DeviceTypeService;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.device.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator; 
import pub.types.RefData; 
 
@Controller("deviceShow") 
@SessionAttributes("TDevice")
@RequestMapping("/admin/device")
public class ShowAction extends BaseAction {  
 
	RefData createRefData(TDevice device) {
		RefData refData = new RefData(); 
		refData.put("propertys", sysStatusService.list(TDevice.TABLE_NAME, TDevice.FIELD_PROPERTY));
		refData.put("types", deviceTypeService.getAll());
		refData.put("projects", projectService.getAll());
		refData.put("deviceModules", deviceModuleService.getAllNotRel(device.getModuleId()));
		refData.put("isCameras", sysStatusService.list(TDevice.TABLE_NAME, TDevice.FIELD_IS_CAMERA));
		return refData;
	}

	public JsValidator createJsValidator(TDevice device) {
		JsValidator validator = new JsValidator(device);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		TDevice device = (TDevice) model.get(TDevice.class.getName());
		if (device != null) {
			 
		} else if (id != null) {
			device = deviceService.get(id);
		} else {
			device = new TDevice();   
		}
		model.addAttribute("TDevice", device); 
		JsValidator jsValidator = createJsValidator(device);
		model.addAttribute(jsValidator);  
		model.addAttribute(createRefData(device));  
	}

	@Autowired
	private DeviceService deviceService; 
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DeviceModuleService deviceModuleService;
	
}
