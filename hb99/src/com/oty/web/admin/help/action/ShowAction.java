package com.oty.web.admin.help.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.oty.sys.entity.Help;
import com.oty.sys.service.HelpService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.help.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.JsValidator;
import pub.types.RefData;

@Controller("helpShow")
@SessionAttributes("help")
@RequestMapping("/admin/help")
public class ShowAction extends BaseAction {
	
	@ModelAttribute
	RefData createRefData() {
		RefData refData = new RefData(); 
		refData.put("types", sysStatusService.list(Help.TABLE_NAME, Help.FIELD_TYPE));
		return refData;
	}

	public JsValidator createJsValidator(Help help) {
		JsValidator validator = new JsValidator(help);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		Help help = (Help) model.get(Help.class.getName());
		if (help != null) {
			 
		} else if (id != null) {
			help = helpService.get(id);
		} else {
			help = new Help();   
		}
		model.addAttribute("help", help); 
		JsValidator jsValidator = createJsValidator(help);
		model.addAttribute(jsValidator);  
	}

	@Autowired
	private HelpService helpService; 
	@Autowired
	private SysStatusService sysStatusService;
}
