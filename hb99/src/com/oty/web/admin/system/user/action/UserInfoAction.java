package com.oty.web.admin.system.user.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.SessionAttributes;

import pub.spring.JsValidator;
import pub.types.IdText;
import pub.types.RefData;

import com.oty.sys.entity.SysUser;
import com.oty.sys.entity.TFile;
import com.oty.sys.entity.TFileInfo;
import com.oty.sys.model.admin.user.UserData;
import com.oty.sys.service.SysAreaService;
import com.oty.sys.service.FileInfoService;
import com.oty.sys.service.FileService;
import com.oty.web.admin.system.user.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

@Controller("adminUserInfo")
@RequestMapping("/admin/system/user") 
@SessionAttributes("userData")
public class UserInfoAction extends BaseAction { 

	RefData createRefData(Integer provinceId, Integer cityId) {
		RefData refData = new RefData();
		List<IdText> provinces = new ArrayList<IdText>();
		provinces = areaService.listSubAreas(0); // 获取所有的省
		refData.put("provinces", provinces);
		if (provinceId != null && cityId != null) {
			List<IdText> cities = new ArrayList<IdText>();
			List<IdText> districts = new ArrayList<IdText>();
			cities = areaService.listSubAreas(provinceId); // 获取所有的市
			districts = areaService.listSubAreas(cityId); // 获取所有的区
			refData.put("cities", cities);
			refData.put("districts", districts);
		}
		return refData;
	}
	
	public JsValidator createJsValidator(UserData userData) {
		JsValidator validator = new JsValidator(userData);
		ValidateUtils.setUpdateRules(validator);
		return validator;
	}

	@RequestMapping("user_info.html")
	public void execute(Model model) {
		SysUser sysUser = this.getSysUser();
		UserData userData = new UserData();
		userData.setUser(sysUser);
		TFileInfo fileInfo = fileInfoService.getFileInfo(SysUser.TABLE_NAME, sysUser.getId(), 0);
		if (fileInfo != null) {
			TFile file = fileService.get(fileInfo.getFileId());
			if(file!=null){
				model.addAttribute("fileId", fileInfo.getFileId());
				model.addAttribute("url", file.getUrl());
			}
		}
		model.addAttribute(createRefData(sysUser.getProvinceId(), sysUser.getCityId()));
		model.addAttribute(createJsValidator(userData));
		model.addAttribute(userData);
	}

	@Autowired
	private SysAreaService areaService; 
	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private FileService fileService;
	
}
