package com.oty.web.admin.resource.industry.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.RefData;
 
import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.SysIndustry; 
import com.oty.sys.service.SysIndustryService; 
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction; 
 
@Controller("resourceIndustryQuery")
@RequestMapping("/admin/resource/industry")
public class QueryAction extends BaseAction { 

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("isEnables", sysStatusService.list(SysIndustry.TABLE_NAME, SysIndustry.FIELD_IS_ENABLE)); 
		return refData;
	}
 
	@RequestMapping("/query.html")
	public void execute(SysIndustry sysIndustry, @RequestParam(value="page",defaultValue="1")Integer page) {     
		try { 
			List<SysIndustry> pagehelper = sysIndustryService.query(sysIndustry, page);  
			request.setAttribute("pagehelper", new PageInfo<SysIndustry>(pagehelper)); 
			request.setAttribute("sysIndustry", sysIndustry); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	@Autowired
	private SysIndustryService sysIndustryService;  
	@Autowired
	private SysStatusService sysStatusService;

}
