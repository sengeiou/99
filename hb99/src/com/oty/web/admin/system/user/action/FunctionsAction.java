package com.oty.web.admin.system.user.action;

import java.util.HashMap; 
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pub.functions.CryptFuncs;
import pub.functions.StrFuncs;

import com.oty.sys.entity.SysUser; 
import com.oty.sys.service.SysUserService;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;

@Controller("adminUserFunctions")
@RequestMapping("/admin/system/user")
public class FunctionsAction extends BaseAction {

	@RequestMapping("validatePwd")
	@ModelAttribute("isok")
	public boolean validatePwd(String pwd) {
		boolean flag = false;
		if (StrFuncs.notEmpty(pwd)) {
			pwd = CryptFuncs.getMd5(pwd);
			SysUser sysUser = this.getSysUser();
			if (pwd.equalsIgnoreCase(sysUser.getPassword())) {
				flag = true;
			}
		}
		return flag;
	}

	@RequestMapping("changePassword")
	@ResponseBody
	public Callable<Map<String, Object>> changePassword(final String oldPassword, final String newPassword) {

		return new Callable<Map<String, Object>>() {

			public Map<String, Object> call() throws Exception {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				try {
					Integer userId = getSysUser().getId();
					SysUser user = sysUserService.get(userId);
					if (!StrFuncs.equalsIgnoreNullAndCase(user.getPassword(), CryptFuncs.getMd5(oldPassword))) {
						resultMap.put("code", 500);
						resultMap.put("msg", "原密码错误");
						return resultMap;
					}
					if (StrFuncs.equalsIgnoreNullAndCase(oldPassword, newPassword)) {
						resultMap.put("code", 500);
						resultMap.put("msg", "新密码不能和原密码相同");
						return resultMap;
					}
					user.setPassword(CryptFuncs.getMd5(newPassword));
					sysUserService.save(getSysUser().getId(), user);
					resultMap.put("code", 200);
					resultMap.put("msg", "请求成功");
				} catch (Exception e) {
					Log4jUtil.error(e.getMessage());
					resultMap.put("code", 500);
					resultMap.put("msg", "服务器异常,请联系客服");
					return resultMap;
				}
				return resultMap;
			}
		};

	}
 
	@Autowired
	private SysUserService sysUserService; 

}
