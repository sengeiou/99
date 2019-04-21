package com.oty.web.admin.action;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.SysUpdateLog;
import com.oty.sys.service.SysUpdateLogService;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;

@Controller("adminMain")
public class MainAction extends BaseAction {

	@RequestMapping("/admin/main.html")
	public void execute() { 
		try {
			List<SysUpdateLog> pagehelper = sysUpdateLogService.query(new SysUpdateLog(), 1);  
			request.setAttribute("pagehelper", new PageInfo<SysUpdateLog>(pagehelper));  
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}
	
	@Autowired
	private SysUpdateLogService sysUpdateLogService;
	
}
