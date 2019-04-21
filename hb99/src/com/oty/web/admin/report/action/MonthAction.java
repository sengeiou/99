package com.oty.web.admin.report.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.web.base.action.BaseAction;

@Controller("monthReport")
@RequestMapping("/admin/report")
public class MonthAction extends BaseAction {

	@RequestMapping("/month.html")
	public void execute() {

	}

}