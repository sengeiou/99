package com.oty.web.admin.system.user.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.SysUser; 
import com.oty.sys.service.SysStatusService;
import com.oty.sys.service.SysUserService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData; 
 
@Controller("adminUserQuery")
@RequestMapping("/admin/system/user")
public class QueryAction extends BaseAction { 
	
	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(SysUser.TABLE_NAME, SysUser.FIELD_TYPE)); 
		refData.put("statuses", sysStatusService.list(SysUser.TABLE_NAME, SysUser.FIELD_STATUS)); 
		return refData;
	}
 
	@RequestMapping("/query.html")
	public void execute(SysUser sysUser, @RequestParam(value="page",defaultValue="1")Integer page) {   
		try { 
			List<SysUser> pagehelper = sysUserService.query(sysUser, page);  
			request.setAttribute("pagehelper", new PageInfo<SysUser>(pagehelper)); 
			request.setAttribute("sysUser", sysUser);   
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysStatusService sysStatusService;

}
