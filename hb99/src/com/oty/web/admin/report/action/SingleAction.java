package com.oty.web.admin.report.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.web.base.action.BaseAction;

@Controller("singleReport")
@RequestMapping("/admin/report")
public class SingleAction extends BaseAction {

	@RequestMapping("/single.html")
	public void execute() {

	}

}