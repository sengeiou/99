package com.oty.web.admin.project.action;
   
import com.oty.sys.entity.TFile;
import com.oty.sys.entity.TFileInfo;
import com.oty.sys.entity.TProject;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysAreaService;
import com.oty.sys.service.SysIndustryService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.project.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;
import com.oty.sys.service.FileInfoService;
import com.oty.sys.service.FileService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator;
import pub.types.IdText;
import pub.types.RefData; 
 
@Controller("projectShow") 
@SessionAttributes("TProject")
@RequestMapping("/admin/project")
public class ShowAction extends BaseAction {  

	RefData createRefData(TProject project) {
		RefData refData = new RefData();
		List<IdText> provinces = new ArrayList<IdText>();
		provinces = areaService.listSubAreas(0); // 获取所有的省
		refData.put("provinces", provinces);
		if (project.getProvinceId() != null && project.getCityId() != null) {
			List<IdText> cities = new ArrayList<IdText>();
			List<IdText> areas = new ArrayList<IdText>();
			cities = areaService.listSubAreas(project.getProvinceId()); // 获取所有的市
			areas = areaService.listSubAreas(project.getCityId()); // 获取所有的区
			refData.put("cities", cities);
			refData.put("areas", areas);
		}
		refData.put("types", sysStatusService.list(TProject.TABLE_NAME, TProject.FIELD_TYPE));
		refData.put("sysIndustrys", sysIndustryService.list());
		return refData;
	}

	public JsValidator createJsValidator(TProject project) {
		JsValidator validator = new JsValidator(project);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) { 
		TProject project = (TProject) model.get(TProject.class.getName());
		if (project != null) {
			 
		} else if (id != null) {
			project = projectService.get(id); 
			TFileInfo fileInfo = fileInfoService.getFileInfo(TProject.TABLE_NAME, id, 0);
			if (fileInfo != null) {
				TFile file = fileService.get(fileInfo.getFileId());
				model.addAttribute("fileId", fileInfo.getFileId());
				model.addAttribute("url", file.getUrl());
			}
		} else {
			project = new TProject();  
			project.setCreateBy(getUserId());
		}
		model.addAttribute(project);

		JsValidator jsValidator = createJsValidator(project);
		model.addAttribute(jsValidator); 
		model.addAttribute(createRefData(project)); 
	}

	@Autowired
	private ProjectService projectService;
	@Autowired
	private SysAreaService areaService; 
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private SysIndustryService sysIndustryService;
	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private FileService fileService;
	
}
