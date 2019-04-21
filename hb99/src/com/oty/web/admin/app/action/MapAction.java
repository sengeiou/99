package com.oty.web.admin.app.action;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.sys.entity.TProject;
import com.oty.sys.service.ProjectService;
import com.oty.util.JsonUtils;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction; 
  
@Controller("map")
@RequestMapping("/admin")
public class MapAction extends BaseAction { 

	@RequestMapping("/map.html")
	public String execute(ModelMap model) { 
		try { 
			model.put("jsonData", JsonUtils.toJson(projectService.query(new TProject(), 1, 0))); 
		} catch (Exception e) { 
			Log4jUtil.error("异常信息：", e);
		}
		return "/admin/app/map";
	}
	
	@Autowired
	private ProjectService projectService;
	
}
