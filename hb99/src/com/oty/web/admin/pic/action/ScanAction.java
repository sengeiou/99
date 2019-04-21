package com.oty.web.admin.pic.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.TWarnPic; 
import com.oty.sys.service.WarnPicService; 
import com.oty.web.base.action.BaseAction; 

@Controller("scanQuery")
@RequestMapping("/admin/pic")
public class ScanAction extends BaseAction { 

	@RequestMapping("/scan.html")
	public void execute(TWarnPic warnPic, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		List<TWarnPic> pagehelper = warnPicService.query(warnPic, page);
		request.setAttribute("pagehelper", new PageInfo<TWarnPic>(pagehelper));
		request.setAttribute("warnPic", warnPic);
	}

	@Autowired
	private WarnPicService warnPicService; 

}
