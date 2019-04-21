package com.oty.web.admin.system.role.action;
  
import java.util.List;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.SysRole; 
import com.oty.sys.service.SysRoleService;
import com.oty.web.base.action.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
 
@Controller("adminRoleQuery") 
@RequestMapping("/admin/system/role")
public class QueryAction extends BaseAction { 

	@RequestMapping("/query.html")
	public void execute(SysRole sysRole,@RequestParam(value="page",defaultValue="1")Integer page) { 
		try {  
			List<SysRole> pagehelper =  roleService.query(sysRole, page);
			request.setAttribute("pagehelper", new PageInfo<SysRole>(pagehelper));  
			request.setAttribute("sysRole", sysRole);  
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

	@Autowired
	private SysRoleService roleService;
	
}
