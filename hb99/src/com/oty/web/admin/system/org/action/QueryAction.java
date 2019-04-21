package com.oty.web.admin.system.org.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TOrg;
import com.oty.sys.service.OrgService; 
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;

@Controller("orgQuery")
@RequestMapping("/admin/system/org")
public class QueryAction extends BaseAction {

	@RequestMapping("query.html")
	public void execute(TOrg org, @RequestParam(value="page",defaultValue="1")Integer page) {
		try {
			List<TOrg> pagehelper = orgService.query(org, page);  
			request.setAttribute("pagehelper", new PageInfo<TOrg>(pagehelper)); 
			request.setAttribute("org", org);    
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

	@Autowired
	private OrgService orgService;

}
