package com.oty.web.admin.system.log.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.SysUpdateLog; 
import com.oty.sys.service.SysUpdateLogService;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;

@Controller("adminLogQuery") 
@RequestMapping("/admin/system/log")
public class QueryAction extends BaseAction {

	@RequestMapping("query.html")
	public void execute(SysUpdateLog sysUpdateLog, @RequestParam(value="page",defaultValue="1")Integer page) {
		try {
			List<SysUpdateLog> pagehelper = sysUpdateLogService.query(sysUpdateLog, page);  
			request.setAttribute("pagehelper", new PageInfo<SysUpdateLog>(pagehelper)); 
			request.setAttribute("sysUpdateLog", sysUpdateLog);   
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	} 

	@Autowired
	private SysUpdateLogService sysUpdateLogService;

}
