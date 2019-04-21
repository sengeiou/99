package com.oty.web.admin.system.config.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oty.sys.entity.SysConfig; 
import com.oty.sys.service.SysConfigService; 
import com.oty.web.admin.system.config.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.util.Log4jUtil;
 
import pub.spring.JsValidator; 

@Controller("configShow")
@SessionAttributes("sysConfig")
@RequestMapping("/admin/system/config")
public class ShowAction extends BaseAction {

	public JsValidator createJsValidator(SysConfig sysConfig) {
		JsValidator validator = new JsValidator(sysConfig);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("show.html")
	public void execute(ModelMap model, Integer id) {
		try {
			SysConfig sysConfig = (SysConfig) model.get(SysConfig.class.getName());
			if (sysConfig != null) {

			} else if (id != null) {
				sysConfig = sysConfigService.get(id);
			} else {
				sysConfig = new SysConfig(); 
			}
			model.addAttribute(sysConfig);
			model.addAttribute(createJsValidator(sysConfig)); 
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	} 

	@Autowired
	private SysConfigService sysConfigService;

}
