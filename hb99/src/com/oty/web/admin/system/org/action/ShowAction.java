package com.oty.web.admin.system.org.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
 
import com.oty.sys.entity.TOrg;
import com.oty.sys.service.OrgService; 
import com.oty.web.admin.system.org.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;

import pub.spring.JsValidator;

@Controller("orgShow")
@SessionAttributes("TOrg")
@RequestMapping("/admin/system/org")
public class ShowAction extends BaseAction {

	public JsValidator createJsValidator(TOrg org) {
		JsValidator validator = new JsValidator(org);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("show.html")
	public void execute(ModelMap model, Integer id) {
		try {
			TOrg org = (TOrg) model.get(TOrg.class.getName());
			if (org != null) {

			} else if (id != null) {
				org = orgService.get(id);
			} else {
				org = new TOrg(); 
			}
			model.addAttribute(org);
			model.addAttribute(createJsValidator(org)); 
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

	@Autowired
	private OrgService orgService; 

}
