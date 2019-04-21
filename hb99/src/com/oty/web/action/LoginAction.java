package com.oty.web.action;

import java.util.Date;
import java.util.Map;
   
import com.oty.constant.Constants;
import com.oty.sys.entity.SysUser; 
import com.oty.sys.entity.TFile;
import com.oty.sys.entity.TFileInfo;
import com.oty.sys.service.FileInfoService;
import com.oty.sys.service.FileService;
import com.oty.sys.service.SysUserService;  
import com.oty.web.base.action.BaseAction;
import com.oty.web.base.data.SessionData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  

import pub.functions.CryptFuncs;
import pub.functions.ReqFuncs; 
import pub.functions.RspFuncs;
import pub.spring.BeanUtils;
  
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
 
@Controller("adminLogin")
@RequestMapping
public class LoginAction extends BaseAction {

	private static final String codeCookieName = "otc_admin_code";
	public static final String pwdMd5CookieName = "otc_admin_pwdx"; 

	public static boolean checkRememberedLoginState(HttpServletRequest request) {
		Map<String, String> cookieMap = ReqFuncs.getCookieMap(request); 
		if (!cookieMap.containsKey(codeCookieName)) {
			return false;
		}
		String account = cookieMap.get(codeCookieName);
		String pwdMd5 = cookieMap.get(pwdMd5CookieName); 
		SysUserService sysUserService = BeanUtils.getBean(SysUserService.class);
		SysUser sysUser = sysUserService.getByAccount(account);
		if (sysUser == null) {
			return false;
		}
		String realPwdMd5 = sysUser.getPassword();
		if (realPwdMd5.equals(pwdMd5)) {
			SessionData data = new SessionData();
			data.setUser(sysUser);
			data.setIpAddr(request.getRemoteAddr()); 
			data.setLoginTime(new Date()); 
			FileInfoService fileInfoService = BeanUtils.getBean(FileInfoService.class);
			TFileInfo fileInfo = fileInfoService.getFileInfo(SysUser.TABLE_NAME, sysUser.getId(), 0);
			if (fileInfo != null) {
				FileService fileService = BeanUtils.getBean(FileService.class);
				TFile file = fileService.get(fileInfo.getFileId());
				if(file!=null){
					data.setImgUrl(file.getUrl()); 
				}
			}
			request.getSession().setAttribute(Constants.SESSION_ADMIN_DATA, data); 
			return true;
		}
		return false;
	} 

	@RequestMapping(value="/login.html", produces="text/html;charset=UTF-8")
	public String login(HttpServletResponse response) throws Exception { 
		RspFuncs.setNoCache(response); 
		request.getSession().removeAttribute(Constants.SESSION_ADMIN_DATA);    
		String home = request.getContextPath();
		String cookiePath = home + "/admin"; 
		SysUser sysUser;  
		String code = ReqFuncs.getStringParam(request, "code");
		String password = ReqFuncs.getStringParam(request, "password"); 
		if (request.getParameter("doLogin") == null) {
			RspFuncs.deleteCookie(response, codeCookieName, cookiePath);
			RspFuncs.deleteCookie(response, pwdMd5CookieName, cookiePath);
			return "/login";
		} 
		try {
			sysUser = sysUserService.login(code, CryptFuncs.getMd5(password)); 
			setSessionData(sysUser);  
			TFileInfo fileInfo = fileInfoService.getFileInfo(SysUser.TABLE_NAME, sysUser.getId(), 0);
			if (fileInfo != null) {
				TFile file = fileService.get(fileInfo.getFileId());
				if(file!=null){ 
					setImgUrl(file.getUrl());  
				}
			}
			Cookie cookieCode = new Cookie(codeCookieName, code);
			cookieCode.setMaxAge(3600 * 24 * 30); 
			cookieCode.setPath(cookiePath);
			response.addCookie(cookieCode);
			String pwdMd5 = CryptFuncs.getMd5(password);
			Cookie cookiePwd = new Cookie(pwdMd5CookieName, pwdMd5);
			cookiePwd.setMaxAge(3600 * 24 * 30);
			cookiePwd.setPath(cookiePath);
			response.addCookie(cookiePwd);
			return "redirect:/admin/index.html";
		} catch (Throwable e) {
			System.out.print(e.getMessage());
			request.setAttribute("errorInfo", e.getMessage());
			return "/login";
		}
	} 
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private FileService fileService;

}