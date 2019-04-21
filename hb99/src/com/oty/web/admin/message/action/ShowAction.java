package com.oty.web.admin.message.action;
   
import com.oty.sys.entity.TMessage;
import com.oty.sys.model.admin.message.MessageData;
import com.oty.sys.service.MessageService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.admin.message.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pub.spring.JsValidator;
import pub.types.RefData; 
 
@Controller("messageShow")
@SessionAttributes("messageData")
@RequestMapping("/admin/message")
public class ShowAction extends BaseAction { 
	
	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(TMessage.TABLE_NAME, TMessage.FIELD_TYPE));
		refData.put("allUserList", messageService.selectAllUser());
		return refData;
	}

	public JsValidator createJsValidator(MessageData messageData) {
		JsValidator validator = new JsValidator(messageData);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) {
		MessageData messageData = (MessageData) model.get(MessageData.class.getName());
		if (messageData != null) {	
			 
		}else if (id != null) {	//点击详情——>进入预览页面(不修改)
			TMessage tMessage = messageService.get(id);
			messageData = new MessageData();
			messageData.setMessage(tMessage);
			messageService.save(getSysUser().getId(), messageData);
			
		} else {	//点击新增——>进入新增页面
			TMessage tMessage = new TMessage();
			messageData = new MessageData();
			tMessage.setSendUserId(getSysUser().getId());
			messageData.setMessage(tMessage);
		}
		model.addAttribute("messageData",messageData);

		JsValidator jsValidator = createJsValidator(messageData);
		model.addAttribute(jsValidator); 
	}

	@Autowired
	private MessageService messageService;
	@Autowired
	private SysStatusService sysStatusService;
	
}
