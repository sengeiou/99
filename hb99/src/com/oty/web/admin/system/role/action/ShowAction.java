package com.oty.web.admin.system.role.action;

import com.oty.sys.entity.SysModule;
import com.oty.sys.entity.SysRole;
import com.oty.sys.model.admin.role.RoleData;
import com.oty.sys.service.SysModuleService;
import com.oty.sys.service.SysRoleService;
import com.oty.web.base.action.BaseAction;
import com.oty.web.admin.system.role.data.HierarchicalModuleData;
import com.oty.web.admin.system.role.data.RelModuleData;
import com.oty.web.admin.system.role.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import pub.functions.ReqFuncs;
import pub.spring.ActionResult;
import pub.spring.JsValidator;
import pub.spring.Validator;
import pub.types.RefData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller("adminRoleShow")
@SessionAttributes("roleData")
@RequestMapping("/admin/system/role")
public class ShowAction extends BaseAction {

	RefData createRefData(RoleData roleData) {
		RefData refData = new RefData();
		Map<Integer, HierarchicalModuleData> dataMap = new HashMap<Integer, HierarchicalModuleData>();
		HierarchicalModuleData rootData = new HierarchicalModuleData();
		dataMap.put(0, rootData);
		List<SysModule> modules = moduleService.getAll();
		for (SysModule module : modules) {
			if (module.getIsVisible() == 0) {
				continue;
			}
			HierarchicalModuleData data = dataMap.get(module.getId());
			if (data == null) {
				data = new HierarchicalModuleData();
				dataMap.put(module.getId(), data);
			}
			data.setModule(module);
			HierarchicalModuleData parentData = dataMap.get(module.getParentId());
			if (parentData == null) {
				parentData = new HierarchicalModuleData();
				parentData.setSubDatas(new ArrayList<HierarchicalModuleData>());
				dataMap.put(module.getParentId(), parentData);
			}
			List<HierarchicalModuleData> subDatas = parentData.getSubDatas();
			if (subDatas == null) {
				subDatas = new ArrayList<HierarchicalModuleData>();
				parentData.setSubDatas(subDatas);
			}
			subDatas.add(data);
		}
		List<RelModuleData> moduleDatas = new ArrayList<RelModuleData>();
		for (HierarchicalModuleData hData : rootData.getSubDatas()) {
			addModuleData(moduleDatas, hData, 0, roleData.getModuleIds());
		}
		refData.put("moduleDatas", moduleDatas);
		return refData;
	}

	private void addModuleData(List<RelModuleData> moduleDatas, HierarchicalModuleData hData, int level,
			List<Integer> relModuleIds) {
		SysModule module = hData.getModule();
		String indent = "";
		for (int n = 0; n < level; n++) {
			indent += "　&nbsp;&nbsp;";
		}

		RelModuleData data = new RelModuleData();
		data.setModule(module);
		data.setIndent(indent);
		data.setHasRel(relModuleIds.contains(module.getId()));
		moduleDatas.add(data);

		if (hData.getSubDatas() == null) {
			return;
		}
		for (HierarchicalModuleData subData : hData.getSubDatas()) {
			addModuleData(moduleDatas, subData, level + 1, relModuleIds);
		}
	}

	public JsValidator createJsValidator(RoleData roleData) {
		JsValidator validator = new JsValidator(roleData);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) {
		RoleData roleData = (RoleData) model.get(RoleData.class.getName());
		if (roleData != null) {

		} else if (id != null) {
			roleData = roleService.load(id);
		} else {
			SysRole role = new SysRole();
			roleData = new RoleData();
			roleData.setRole(role);
			roleData.setModuleIds(new ArrayList<Integer>());
		}

		model.addAttribute(roleData);

		JsValidator jsValidator = createJsValidator(roleData);
		model.addAttribute(jsValidator);

		RefData refData = createRefData(roleData);
		model.addAttribute(refData);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public View save(RoleData roleData, Errors errors, HttpServletRequest request) {
		Validator validator = Validator.of(roleData, errors);
		ValidateUtils.setRules(validator);
		Integer[] ids = ReqFuncs.getIntegerParams(request, "moduleIds");
		List<Integer> moduleIds = new ArrayList<Integer>();
		for (Integer moduleId : ids) {
			moduleIds.add(moduleId);
		}
		roleData.setModuleIds(moduleIds);
		if (errors.hasErrors()) {
			return ActionResult.formView();
		}
		try {
			roleService.save(getSysUser().getId(), roleData);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.error("系统异常，请联系管理员");
		}
		return ActionResult.ok("保存成功", "query.html");
	}

	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysModuleService moduleService;

}
