package com.oty.web.api.action;
 
import com.oty.util.R;
import com.oty.web.base.action.BaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam; 
import org.springframework.web.bind.annotation.*;
 
import java.util.*; 
 
@RestController
@RequestMapping("/api/business")
@Api(value = "business", description = "商家信息相关接口")
public class BusinessController extends BaseAction {
 
	@ApiOperation(value = "成为商家和添加店铺", notes = "成为商家和添加店铺")
	@PostMapping(value = "/beBusiness")
	protected R beBusiness(
			@ApiParam(value = "用户ID", required = true) @RequestParam(required = true) Integer weixinUserId,
			@ApiParam(value = "商铺名称", required = true) @RequestParam(required = true) String name,
			@ApiParam(value = "商铺图片", required = true) @RequestParam(required = true) String factoryImage,
			@ApiParam(value = "行业ID", required = true) @RequestParam(required = true) Integer industrySecId,
			@ApiParam(value = "地区ID", required = true) @RequestParam(required = true) Integer areaId,
			@ApiParam(value = "地址", required = true) @RequestParam(required = true) String address,
			@ApiParam(value = "联系手机号", required = true) @RequestParam(required = true) String mobile,
			@ApiParam(value = "联系座机", required = false) @RequestParam(required = false) String tel,
			@ApiParam(value = "微信号", required = true) @RequestParam(required = true) String weixin,
			@ApiParam(value = "简介", required = false) @RequestParam(required = false) String summary,
			@ApiParam(value = "营业执照", required = true) @RequestParam(required = true) String businessLicence,
			@ApiParam(value = "房产证明", required = false) @RequestParam(required = false) String houseprove,
			@ApiParam(value = "租房合同", required = false) @RequestParam(required = false) String rentingcontract,
			@ApiParam(value = "商铺经度", required = true) @RequestParam(required = true) String gpsX,
			@ApiParam(value = "商铺纬度", required = true) @RequestParam(required = true) String gpsY) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		return R.ok("success", map);
	}
 
}