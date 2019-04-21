package com.oty.web.admin.device.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pub.types.RefData;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.TDevice; 
import com.oty.sys.service.DeviceModuleService;
import com.oty.sys.service.DeviceService;
import com.oty.sys.service.DeviceTypeService;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;

@Controller("deviceQuery")
@RequestMapping("/admin/device")
public class QueryAction extends BaseAction {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("propertys", sysStatusService.list(TDevice.TABLE_NAME, TDevice.FIELD_PROPERTY));
		refData.put("types", deviceTypeService.getAll());
		refData.put("projects", projectService.getAll());
		refData.put("deviceModules", deviceModuleService.getAll());
		refData.put("isCameras", sysStatusService.list(TDevice.TABLE_NAME, TDevice.FIELD_IS_CAMERA));
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(TDevice device, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<TDevice> pagehelper = deviceService.query(device, page, 10);
			request.setAttribute("pagehelper", new PageInfo<TDevice>(pagehelper));
			request.setAttribute("device", device);  
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private DeviceTypeService deviceTypeService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private DeviceModuleService deviceModuleService;

}
