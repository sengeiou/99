package com.oty.web.admin.attribute.action;
    
import com.oty.sys.entity.TMaintainAttribute; 
import com.oty.sys.service.MaintainAttributeService; 
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.attribute.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator; 
import pub.types.RefData; 
 
@Controller("attributeShow") 
@SessionAttributes("TMaintainAttribute")
@RequestMapping("/admin/attribute")
public class ShowAction extends BaseAction {  

	@ModelAttribute
	RefData createRefData() {
		RefData refData = new RefData();  
        refData.put("types", sysStatusService.list(TMaintainAttribute.TABLE_NAME, TMaintainAttribute.FIELD_TYPE)); 
		return refData;
	}

	public JsValidator createJsValidator(TMaintainAttribute maintainAttribute) {
		JsValidator validator = new JsValidator(maintainAttribute);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		TMaintainAttribute maintainAttribute = (TMaintainAttribute) model.get(TMaintainAttribute.class.getName());
		if (maintainAttribute != null) {
			 
		} else if (id != null) {
			maintainAttribute = maintainAttributeService.get(id);
		} else {
			maintainAttribute = new TMaintainAttribute();   
		}
		model.addAttribute("TMaintainAttribute", maintainAttribute); 
		JsValidator jsValidator = createJsValidator(maintainAttribute);
		model.addAttribute(jsValidator);  
	}

	@Autowired
	private MaintainAttributeService maintainAttributeService;
	@Autowired
	private SysStatusService sysStatusService; 
	
}
