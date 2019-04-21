package com.oty.web.api.action;
 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;
 
@RestController
@RequestMapping("/api")
@ApiIgnore
public class IndexAction {
	
	@RequestMapping("index.html")
	public String execute(String a,String b,ModelMap model) { 
	   
		return "welcome !"; 
	} 

	
}
