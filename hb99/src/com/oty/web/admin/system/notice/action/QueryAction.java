package com.oty.web.admin.system.notice.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.SysNotice; 
import com.oty.sys.service.SysNoticeService;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;

@Controller("adminNoticeQuery")
@RequestMapping("/admin/system/notice")
public class QueryAction extends BaseAction {

	@RequestMapping("query.html")
	public void execute(SysNotice sysNotice, @RequestParam(value="page",defaultValue="1")Integer page) {
		try {
			List<SysNotice> pagehelper = noticeService.query(sysNotice, page);  
			request.setAttribute("pagehelper", new PageInfo<SysNotice>(pagehelper)); 
			request.setAttribute("sysNotice", sysNotice);    
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

	@Autowired
	private SysNoticeService noticeService;

}
