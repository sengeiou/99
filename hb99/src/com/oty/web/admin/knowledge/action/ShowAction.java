package com.oty.web.admin.knowledge.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oty.sys.entity.Knowledge;
import com.oty.sys.service.KnowledgeService;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.knowledge.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.JsValidator;
import pub.types.RefData;

@Controller("knowledgeShow")
@SessionAttributes("Knowledge")
@RequestMapping("/admin/knowledge")
public class ShowAction extends BaseAction {
	
	@ModelAttribute
	RefData createRefData() {
		RefData refData = new RefData(); 
		//refData.put("propertys", sysStatusService.list(TDevice.TABLE_NAME, TDevice.FIELD_PROPERTY));
//		refData.put("types", deviceTypeService.getAll());
//		refData.put("projects", projectService.getAll());
//		refData.put("deviceModules", deviceModuleService.getAll());
		refData.put("types", sysStatusService.list(Knowledge.TABLE_NAME, Knowledge.FIELD_TYPE));
		return refData;
	}

	public JsValidator createJsValidator(Knowledge knowledge) {
		JsValidator validator = new JsValidator(knowledge);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		Knowledge knowledge = (Knowledge) model.get(Knowledge.class.getName());
		if (knowledge != null) {
			 
		} else if (id != null) {
			knowledge = knowledgeService.get(id);
		} else {
			knowledge = new Knowledge();   
		}
		model.addAttribute("knowledge", knowledge); 
		JsValidator jsValidator = createJsValidator(knowledge);
		model.addAttribute(jsValidator);  
	}

	@Autowired
	private KnowledgeService knowledgeService; 
	@Autowired
	private SysStatusService sysStatusService;
//	@Autowired
//	private DeviceTypeService deviceTypeService;
//	@Autowired
//	private ProjectService projectService;
//	@Autowired
//	private DeviceModuleService deviceModuleService;
	
}
