package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 版本更新日志表
 */
@Table(name = SysUpdateLog.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysUpdateLog extends BaseEntity {

	private static final long serialVersionUID = 7768316691956744971L;

	public static final String TABLE_NAME = "sys_update_log";
 
	/** 版本号 */
	@Getter
	@Setter
	private String version;
	/** 更新内容 */
	@Getter
	@Setter
	private String content;
	/** 更新时间 */
	@Getter
	@Setter
	private String releaseTime;
	@Getter
	@Setter
	private Integer sysUserId;
	@Getter
	@Setter
	private Date createTime;
	@Getter
	@Setter
	private Date updateTime;

}
