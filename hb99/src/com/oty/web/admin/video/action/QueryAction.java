package com.oty.web.admin.video.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TVideoMonitor; 
import com.oty.sys.service.ProjectService; 
import com.oty.sys.service.VideoMonitorService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData;

@Controller("videoQuery")
@RequestMapping("/admin/video")
public class QueryAction extends BaseAction  {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData(); 
		refData.put("projects", projectService.getAll()); 
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(TVideoMonitor videoMonitor, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<TVideoMonitor> pagehelper = videoMonitorService.query(videoMonitor, page);
			request.setAttribute("pagehelper", new PageInfo<TVideoMonitor>(pagehelper));
			request.setAttribute("videoMonitor", videoMonitor);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	@Autowired
	private VideoMonitorService videoMonitorService;  
	@Autowired
	private ProjectService projectService; 
  
}
