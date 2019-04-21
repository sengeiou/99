package com.oty.web.admin.project.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.IdText;
import pub.types.RefData;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TProject; 
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysAreaService;
import com.oty.sys.service.SysIndustryService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;

@Controller("projectQuery")
@RequestMapping("/admin/project")
public class QueryAction extends BaseAction {
 
	public RefData createRefData(TProject project) {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(TProject.TABLE_NAME, TProject.FIELD_TYPE));
		refData.put("sysIndustrys", sysIndustryService.list()); 
		List<IdText> provinces = new ArrayList<IdText>();
		provinces = areaService.listSubAreas(0); // 获取所有的省
		refData.put("provinces", provinces);
		if (project.getProvinceId() != null && project.getCityId() != null) {
			List<IdText> cities = new ArrayList<IdText>();
			List<IdText> areas = new ArrayList<IdText>();
			cities = areaService.listSubAreas(project.getProvinceId()); // 获取所有的市
			areas = areaService.listSubAreas(project.getCityId()); // 获取所有的区
			refData.put("citys", cities);
			refData.put("areas", areas);
		}
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(TProject project, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<TProject> pagehelper = projectService.query(project, page, 10);
			request.setAttribute("pagehelper", new PageInfo<TProject>(pagehelper));
			request.setAttribute("project", project);
			request.setAttribute("refData", createRefData(project)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ProjectService projectService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private SysIndustryService sysIndustryService;
	@Autowired
	private SysAreaService areaService; 

}
