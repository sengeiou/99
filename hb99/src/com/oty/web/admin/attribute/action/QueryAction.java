package com.oty.web.admin.attribute.action;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TMaintainAttribute;
import com.oty.sys.service.MaintainAttributeService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;
 
import pub.types.RefData;

@Controller("attributeQuery")
@RequestMapping("/admin/attribute")
public class QueryAction extends BaseAction {

	@ModelAttribute
    RefData createRefData() {
        RefData refData = new RefData(); 
        refData.put("types", sysStatusService.list(TMaintainAttribute.TABLE_NAME, TMaintainAttribute.FIELD_TYPE)); 
        return refData;
    } 

	@RequestMapping("/query.html")
	public void execute(TMaintainAttribute maintainAttribute, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try {  
			List<TMaintainAttribute> pagehelper = maintainAttributeService.query(maintainAttribute, page);
			request.setAttribute("pagehelper", new PageInfo<TMaintainAttribute>(pagehelper));
			request.setAttribute("maintainAttribute", maintainAttribute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private MaintainAttributeService maintainAttributeService;
	@Autowired
	private SysStatusService sysStatusService;

}
