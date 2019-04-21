package com.oty.web.admin.system.user.action;
  
import com.oty.sys.entity.SysUser;
import com.oty.sys.model.admin.user.UserData;
import com.oty.sys.service.SysAreaService;
import com.oty.sys.service.SysRoleService;
import com.oty.sys.service.SysStatusService;
import com.oty.sys.service.SysUserService;
import com.oty.web.admin.system.user.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pub.spring.JsValidator;
import pub.types.IdText;
import pub.types.RefData;

import java.util.ArrayList;
import java.util.List;
 
@Controller("adminUserShow")
@SessionAttributes("userData")
@RequestMapping("/admin/system/user")
public class ShowAction extends BaseAction {

	public RefData createRefData(UserData userData) {
		RefData refData = new RefData();
		refData.put("roles", roleService.list());
		refData.put("types", sysStatusService.list(SysUser.TABLE_NAME, SysUser.FIELD_TYPE)); 
		List<IdText> provinces = new ArrayList<IdText>();
		provinces = areaService.listSubAreas(0); // 获取所有的省
		refData.put("provinces", provinces);
		if (userData.getUser().getProvinceId() != null && userData.getUser().getCityId() != null) {
			List<IdText> cities = new ArrayList<IdText>();
			List<IdText> areas = new ArrayList<IdText>();
			cities = areaService.listSubAreas(userData.getUser().getProvinceId()); // 获取所有的市
			areas = areaService.listSubAreas(userData.getUser().getCityId()); // 获取所有的区
			refData.put("cities", cities);
			refData.put("areas", areas);
		}
		return refData;
	}

	public JsValidator createJsValidator(UserData userData) {
		JsValidator validator = new JsValidator(userData);
		ValidateUtils.setRules(validator,userData);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		UserData userData = (UserData) model.get(UserData.class.getName());
		if (userData != null) {
			 
		} else if (id != null) {
			userData = userService.load(id);
		} else {
			userData = new UserData();
			SysUser user = new SysUser(); 
			user.setIsManager(0); 
			userData.setUser(user);
			userData.setRoleIds(new ArrayList<Integer>());
		}
		model.addAttribute(userData);

		JsValidator jsValidator = createJsValidator(userData);
		model.addAttribute(jsValidator);

		RefData refData = createRefData(userData);
		model.addAttribute(refData);
	}

	@Autowired
	private SysUserService userService; 
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysAreaService areaService; 
	@Autowired
	private SysStatusService sysStatusService;
	
}
