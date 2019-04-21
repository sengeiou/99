package com.oty.web.common.area.action;
 
import com.oty.sys.entity.SysArea;
import com.oty.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 


import com.oty.sys.service.SysAreaService;  
import com.oty.web.base.action.BaseAction;
 


import pub.types.IdText;

@Controller
@RequestMapping("/common/")
@Api(value="area",description="地区接口")
public class FunctionsAction extends BaseAction {

	@RequestMapping("/area/listSubAreas")
	@ResponseBody
	@ApiOperation(value = "获取地区列表", notes = "获取地区列表",httpMethod="GET")
	public Callable<Map<String, Object>> listSubAreas(final Integer pid) {
	 
	  return new Callable<Map<String, Object>>() {
	  
	    public Map<String, Object> call() throws Exception {
			  Map<String, Object> resultMap = new HashMap<String, Object>(); 
		      try {
					if (pid == null) {
						resultMap.put("code", 500);
						resultMap.put("msg", "参数为空");
						return resultMap;
					}
					List<IdText> subAreas = areaService.listSubAreas(pid);
					resultMap.put("code", 200);
					resultMap.put("data", subAreas);
					resultMap.put("msg", "请求成功");
			} catch (Exception e) {
				resultMap.put("code", 500);
				resultMap.put("msg", "服务器异常,请联系客服");
				return resultMap;
			}
			return resultMap;
	    }
	  };
	 
	}

	@RequestMapping("/area/getArea")
	@ResponseBody
	@ApiOperation(value = "根据ID查找地区", notes = "根据ID查找地区",httpMethod="GET")
	public R getArea(@ApiParam(value = "地区ID", required = true) @RequestParam(required = true) Integer areaId) {
		SysArea area = areaService.get(areaId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("area",area);
		return R.ok(map);
	}

	@Autowired
	private SysAreaService areaService;

}
