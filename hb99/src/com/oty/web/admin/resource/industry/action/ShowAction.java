package com.oty.web.admin.resource.industry.action;
  
import com.oty.sys.entity.SysIndustry; 
import com.oty.sys.service.SysIndustryService; 
import com.oty.web.admin.resource.industry.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pub.spring.JsValidator; 
 
@Controller("resourceIndustryShow")
@SessionAttributes("sysIndustry")
@RequestMapping("/admin/resource/industry")
public class ShowAction extends BaseAction { 

	public JsValidator createJsValidator(SysIndustry sysIndustry) {
		JsValidator validator = new JsValidator(sysIndustry);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		SysIndustry sysIndustry = (SysIndustry) model.get(SysIndustry.class.getName());
		if (sysIndustry != null) {
			 
		} else if (id != null) {
			sysIndustry = sysIndustryService.get(id);
		} else {
			sysIndustry = new SysIndustry();  
		}
		model.addAttribute(sysIndustry);

		JsValidator jsValidator = createJsValidator(sysIndustry);
		model.addAttribute(jsValidator); 
	}

	@Autowired
	private SysIndustryService sysIndustryService;  
	
}
