package com.oty.web.admin.log.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.RefData;
 
import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.SysOperLog; 
import com.oty.sys.service.SysOperLogService;
import com.oty.sys.service.SysStatusService;
import com.oty.sys.service.SysUserService;
import com.oty.web.base.action.BaseAction; 
 
@Controller("logQuery")
@RequestMapping("/admin/log")
public class QueryAction extends BaseAction { 

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("operTypes", sysStatusService.list(SysOperLog.TABLE_NAME, SysOperLog.FIELD_OPER_TYPE)); 
		refData.put("sysUsers", sysUserService.getAll()); 
		return refData;
	}
 
	@RequestMapping("/query.html")
	public void execute(SysOperLog sysOperLog, @RequestParam(value="page",defaultValue="1")Integer page) {   
		try { 
			List<SysOperLog> pagehelper = sysOperLogService.query(sysOperLog, page);  
			request.setAttribute("pagehelper", new PageInfo<SysOperLog>(pagehelper)); 
			request.setAttribute("sysOperLog", sysOperLog);  
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Autowired
	private SysOperLogService sysOperLogService;  
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private SysUserService sysUserService;

}
