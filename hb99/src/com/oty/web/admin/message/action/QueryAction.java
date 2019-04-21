package com.oty.web.admin.message.action; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.RefData;
 
import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TMessage;
import com.oty.sys.service.MessageService; 
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction; 
 
@Controller("messageQuery")
@RequestMapping("/admin/message")
public class QueryAction extends BaseAction { 

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("statuses", sysStatusService.list(TMessage.TABLE_NAME, TMessage.FIELD_STATUS)); 
		refData.put("types", sysStatusService.list(TMessage.TABLE_NAME, TMessage.FIELD_TYPE)); 
		return refData;
	}
 
	@RequestMapping("/query.html")
	public void execute(TMessage message, @RequestParam(value="page",defaultValue="1")Integer page) {    
			try { 
				//message.setSendUserId(getUserId());
				//message.setReceiveUserId(getUserId());
				List<TMessage> pagehelper = messageService.query(message, page);  
				request.setAttribute("pagehelper", new PageInfo<TMessage>(pagehelper)); 
				request.setAttribute("message", message);  
			} catch (Exception e) {
				e.printStackTrace();
			} 
		
	}

	@Autowired
	private MessageService messageService;  
	@Autowired
	private SysStatusService sysStatusService;

}
