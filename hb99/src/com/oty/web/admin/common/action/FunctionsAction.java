package com.oty.web.admin.common.action;
 
import java.util.HashMap; 
import java.util.Map;
import java.util.concurrent.Callable;

import com.oty.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.SysNotice;

import pub.functions.PropsFuncs; 

@Controller("adminCommonFunctions")
@RequestMapping("/admin/common/functions")
public class FunctionsAction {  

	@RequestMapping("/mainParameter")
	@ResponseBody
	public Callable<Map<String, Object>> mainParameter() {

		  return new Callable<Map<String, Object>>() {

			@Override
			public Map<String, Object> call() throws Exception { 
				Map<String, Object> resultMap = new HashMap<String, Object>();  
			    try {  
					resultMap.put("version", PropsFuncs.getProperty("/sys.properties", "version")); 
					resultMap.put("author", PropsFuncs.getProperty("/sys.properties", "author")); 
					resultMap.put("maxUpload", PropsFuncs.getProperty("/sys.properties", "maxUpload")); 
					resultMap.put("server", PropsFuncs.getProperty("/sys.properties", "server")); 
					resultMap.put("dataBase", PropsFuncs.getProperty("/sys.properties", "dataBase"));   
					resultMap.put("pagehelper", new PageInfo<SysNotice>(sysNoticeService.query(new SysNotice(), 1))); 
					resultMap.put("code", 200);
				} catch (Exception e) {
					resultMap.put("code", 500);
					resultMap.put("msg", "服务器异常,请联系客服");
					return resultMap;
				} 
				return resultMap;
			}
		  };
	} 

	@RequestMapping("/getNotice")
	@ResponseBody
	public Callable<Map<String, Object>> getNotice(final Integer id) {

		  return new Callable<Map<String, Object>>() {

			@Override
			public Map<String, Object> call() throws Exception { 
				Map<String, Object> resultMap = new HashMap<String, Object>();  
			    try {    
					resultMap.put("notice", sysNoticeService.get(id));
					resultMap.put("code", 200);
				} catch (Exception e) {
					resultMap.put("code", 500);
					resultMap.put("msg", "服务器异常,请联系客服");
					return resultMap;
				} 
				return resultMap;
			}
		  };
	} 
	   
	@Autowired 
	private SysNoticeService sysNoticeService; 
	
}
