package com.oty.web.admin.device.module.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.RefData;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TDeviceModule; 
import com.oty.sys.service.DeviceModuleService; 
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;

@Controller("moduleQuery")
@RequestMapping("/admin/device/module")
public class QueryAction extends BaseAction {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("statuses", sysStatusService.list(TDeviceModule.TABLE_NAME, TDeviceModule.FIELD_STATUS));
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(TDeviceModule deviceModule, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<TDeviceModule> pagehelper = deviceModuleService.query(deviceModule, page);
			request.setAttribute("pagehelper", new PageInfo<TDeviceModule>(pagehelper));
			request.setAttribute("deviceModule", deviceModule);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Autowired
	private DeviceModuleService deviceModuleService;
	@Autowired
	private SysStatusService sysStatusService;

}
