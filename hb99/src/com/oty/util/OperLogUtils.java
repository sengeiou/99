package com.oty.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oty.sys.entity.BaseEntity;
import com.oty.sys.entity.SysOperLog;
import com.oty.sys.service.SysOperLogService;

@Component("operLogUtils")
public class OperLogUtils {

	public void insertLog(Integer sysUserId, Object newObj) {
		if (newObj instanceof BaseEntity) {
			SysOperLog sysOperLog = new SysOperLog();
			sysOperLog.setRemark(newObj.toString());
			sysOperLog.setOperType(SysOperLog.OPER_TYPE_ADD);
			sysOperLog.setSysUserId(sysUserId);
			sysOperLog.setRefId(((BaseEntity)newObj).getId());
			sysOperLogService.save(sysOperLog);
		}
	}

	public void updateLog(Integer sysUserId, Object oldObj, Object newObj) {
		if (oldObj instanceof BaseEntity && newObj instanceof BaseEntity) {
			SysOperLog sysOperLog = new SysOperLog();
			sysOperLog.setRemark(" 修改前: " + oldObj.toString() + " 修改后:  " + newObj.toString());
			sysOperLog.setOperType(SysOperLog.OPER_TYPE_UPDATE);
			sysOperLog.setSysUserId(sysUserId);
			sysOperLog.setRefId(((BaseEntity)oldObj).getId());
			sysOperLogService.save(sysOperLog);
		}
	}

	public void deleteLog(Integer sysUserId, Object oldObj) {
		if (oldObj instanceof BaseEntity) {
			SysOperLog sysOperLog = new SysOperLog();
			sysOperLog.setRemark(oldObj.toString());
			sysOperLog.setOperType(SysOperLog.OPER_TYPE_DELETE);
			sysOperLog.setSysUserId(sysUserId);
			sysOperLog.setRefId(((BaseEntity)oldObj).getId());
			sysOperLogService.save(sysOperLog);
		}
	}

	@Autowired
	private SysOperLogService sysOperLogService;

}
