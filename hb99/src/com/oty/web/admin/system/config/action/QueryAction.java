package com.oty.web.admin.system.config.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.SysConfig; 
import com.oty.sys.service.SysConfigService; 
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;

@Controller("configQuery") 
@RequestMapping("/admin/system/config")
public class QueryAction extends BaseAction {

	@RequestMapping("query.html")
	public void execute(SysConfig sysConfig, @RequestParam(value="page",defaultValue="1")Integer page) {
		try {
			List<SysConfig> pagehelper = sysConfigService.query(sysConfig, page);  
			request.setAttribute("pagehelper", new PageInfo<SysConfig>(pagehelper)); 
			request.setAttribute("sysConfig", sysConfig);   
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	} 

	@Autowired
	private SysConfigService sysConfigService;

}
