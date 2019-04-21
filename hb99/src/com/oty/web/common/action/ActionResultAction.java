package com.oty.web.common.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/common")
@ApiIgnore
public class ActionResultAction {

	@RequestMapping("/action_result.html")
	public String execute() {
		return "forward:/common/action_result.jsp";
	}

}
