package com.oty.web.admin.pic.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TWarnPic;
import com.oty.sys.service.DeviceService;
import com.oty.sys.service.ProjectService;  
import com.oty.sys.service.WarnPicService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData;

@Controller("picQuery")
@RequestMapping("/admin/pic")
public class QueryAction extends BaseAction  {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData(); 
		refData.put("projects", projectService.getAll()); 
		refData.put("devices", deviceService.getAll());  
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(TWarnPic warnPic, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<TWarnPic> pagehelper = warnPicService.query(warnPic, page);
			request.setAttribute("pagehelper", new PageInfo<TWarnPic>(pagehelper));
			request.setAttribute("warnPic", warnPic); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Autowired
	private WarnPicService warnPicService;  
	@Autowired
	private ProjectService projectService; 
	@Autowired
	private DeviceService deviceService;
  
}
