package com.oty.web.base.data;

import com.oty.sys.entity.SysUser; 

import java.io.Serializable;
import java.util.Date;

public class SessionData implements Serializable {

	private static final long serialVersionUID = 425283078856941227L;
	private SysUser user; 
	private String ipAddr;
	private Integer moduleId;
	private String imgUrl;
	private Date loginTime;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	} 

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}
