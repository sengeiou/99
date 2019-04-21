package com.oty.web.admin.task.action;
       
import com.oty.sys.entity.TMaintainTask;
import com.oty.sys.model.admin.task.MaintainTaskData;
import com.oty.sys.service.DeviceService;
import com.oty.sys.service.MaintainAttributeService;
import com.oty.sys.service.MaintainTaskService;
import com.oty.sys.service.ProjectService; 
import com.oty.web.admin.task.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator;
import pub.types.RefData;  
 
@Controller("taskShow") 
@SessionAttributes("TMaintainTask")
@RequestMapping("/admin/task")
public class ShowAction extends BaseAction {   
	
	@ModelAttribute
    RefData createRefData() {
        RefData refData = new RefData(); 
		refData.put("projects", projectService.getAll());
        refData.put("devices", deviceService.getAll()); 
		refData.put("maintainAttributes", maintainAttributeService.getAll());
        return refData;
    } 

	public JsValidator createJsValidator(MaintainTaskData maintainTaskData) {
		JsValidator validator = new JsValidator(maintainTaskData);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		MaintainTaskData maintainTaskData = (MaintainTaskData) model.get(MaintainTaskData.class.getName());
		if (maintainTaskData != null) {
			 
		} else if (id != null) {
			maintainTaskData = maintainTaskService.load(id);
		} else {
			maintainTaskData = new MaintainTaskData();   
			TMaintainTask maintainTask = new TMaintainTask();
			maintainTask.setStatus(0);
		}
		model.addAttribute("maintainTaskData", maintainTaskData); 
		JsValidator jsValidator = createJsValidator(maintainTaskData);
		model.addAttribute(jsValidator);  
	}

	@Autowired
	private MaintainTaskService maintainTaskService; 
	@Autowired
	private ProjectService projectService;
	@Autowired
	private MaintainAttributeService maintainAttributeService;
	@Autowired
	private DeviceService deviceService;
	
}
