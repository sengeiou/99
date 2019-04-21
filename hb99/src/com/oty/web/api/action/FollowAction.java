package com.oty.web.api.action;
 
import com.oty.util.R;  
import com.oty.web.base.action.BaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 
 
import java.util.*; 

@RestController
@RequestMapping("/api/follow")
@Api(value = "/follow", description = "关注/留言接口")
public class FollowAction extends BaseAction { 
 
    /**
     * 用户关注活动 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/userActivityFollowRel")
    @ApiOperation(value = "用户关注或取消活动")
    public R userActivityFollowRel(@ApiParam(value = "活动ID", required = true) @RequestParam Integer activityId,
                                   @ApiParam(value = "用户ID", required = true) @RequestParam Integer weixinUserId) { 
        Map<String, Object> map = new HashMap<String, Object>();
        return R.ok("success", map);
    }
 
}