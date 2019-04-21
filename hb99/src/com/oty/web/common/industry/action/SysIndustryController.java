package com.oty.web.common.industry.action;
 
import com.oty.sys.entity.SysIndustry;
import com.oty.sys.service.SysIndustryService;
import com.oty.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap; 
 
@RestController
@RequestMapping("/common/sysIndustry")
@Api(value="sysIndustry",description="行业接口")
public class SysIndustryController {

    @Autowired
    private SysIndustryService sysIndustryService; 

    @RequestMapping("/common/getIndustry")
    @ResponseBody
    @ApiOperation(value = "根据ID查找行业", notes = "根据ID查找行业",httpMethod="GET")
    public R getIndustry(@ApiParam(value = "行业ID", required = true) @RequestParam(required = true) Integer industryId) {
        SysIndustry industry = sysIndustryService.get(industryId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("industry",industry);
        return R.ok(map);
    }

}