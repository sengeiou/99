package com.oty.web.admin.report.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.web.base.action.BaseAction;

@Controller("yearReport")
@RequestMapping("/admin/report")
public class YearAction extends BaseAction {

	@RequestMapping("/year.html")
	public void execute() {

	}

}
