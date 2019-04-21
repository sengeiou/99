package com.oty.web.admin.project.action;

import java.util.ArrayList; 
import java.util.List; 

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping; 
 
import com.oty.sys.entity.TProject;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysIndustryService;
import com.oty.sys.service.SysStatusService;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;
 
import pub.functions.VarFuncs;
import pub.models.listview.SimpleListViewModel;
import pub.types.IdText;
import pub.utils.ExportUtils;

@Controller("projectFunctions")
@RequestMapping("/admin/project")
public class FunctionsAction extends BaseAction { 

	@RequestMapping("export") 
	public void export(HttpServletResponse response , TProject project) {
		try {
			List<TProject> pagehelper = projectService.query(project, 1, 0); 
			List<Object[]> list = new ArrayList<Object[]>();
			List<IdText> types = sysStatusService.list(TProject.TABLE_NAME, TProject.FIELD_TYPE);
			List<IdText> sysIndustrys = sysIndustryService.list();
			ArrayList<Object> row = null;
			for (TProject t : pagehelper) {
				row = new ArrayList<Object>();
				row.add(t.getId()+"");
				row.add(VarFuncs.translate(t.getIndustryId(), sysIndustrys));
				row.add(t.getName());
				row.add(t.getAddress());
				row.add(t.getGpsX()+","+t.getGpsY());
				row.add(t.getPerLiable());
				row.add(VarFuncs.translate(t.getType(), types)); 
				list.add(row.toArray());
			}
			SimpleListViewModel simpleListViewModel = new SimpleListViewModel();
			simpleListViewModel.setTitles("项目编号", "所属行业", "项目名称", "地址", "位置", "责任人", "属性");
			simpleListViewModel.setData(list);
			ExportUtils.exportToExcel(response, simpleListViewModel, "项目数据"); 
		} catch (Exception e) {
			Log4jUtil.error(e.getMessage()); 
		} 
	}
	 
	@Autowired
	private ProjectService projectService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private SysIndustryService sysIndustryService;
	
}
