package com.oty.web.admin.device_attribute.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.DeviceAttribute; 
import com.oty.sys.service.DeviceAttributeService;
import com.oty.sys.service.DeviceTypeService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData;

@Controller("deviceAttributeQuery")
@RequestMapping("/admin/device_attribute")
public class QueryAction extends BaseAction {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("deviceTypes", deviceTypeService.getAll());
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(DeviceAttribute deviceAttribute, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<DeviceAttribute> pagehelper = deviceAttributeService.query(deviceAttribute, page);
			request.setAttribute("pagehelper", new PageInfo<DeviceAttribute>(pagehelper));
			request.setAttribute("deviceAttribute", deviceAttribute);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Autowired
	private DeviceAttributeService deviceAttributeService;
	@Autowired
	private DeviceTypeService deviceTypeService;

}
