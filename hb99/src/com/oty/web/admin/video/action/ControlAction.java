package com.oty.web.admin.video.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oty.web.base.action.BaseAction;

@Controller("video")
@RequestMapping("/admin/video")
public class ControlAction extends BaseAction {
	
	
	@RequestMapping("/video.html")
	public void execute(ModelMap model, Integer deviceId) { 
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
