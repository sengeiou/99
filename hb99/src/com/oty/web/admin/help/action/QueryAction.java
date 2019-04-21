package com.oty.web.admin.help.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.Help;
import com.oty.sys.service.HelpService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData;

@Controller("helpQuery")
@RequestMapping("/admin/help")
public class QueryAction extends BaseAction {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(Help.TABLE_NAME, Help.FIELD_TYPE));
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(Help help, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try {
			List<Help> pagehelper = helpService.query(help, page);
			request.setAttribute("pagehelper", new PageInfo<Help>(pagehelper));
			request.setAttribute("help", help);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private HelpService helpService;
	@Autowired
	private SysStatusService sysStatusService;

}
