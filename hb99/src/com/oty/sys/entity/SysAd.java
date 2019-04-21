package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统公告
 */
@Table(name = SysAd.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysAd extends BaseEntity {

	private static final long serialVersionUID = 7390415621341443466L;

	public static final String TABLE_NAME = "sys_ad";

	/**
	 * 标题
	 */
	@Getter
	@Setter
	private String title;
	/**
	 * 内容
	 */
	@Getter
	@Setter
	private String contents;
	/**
	 * 创建时间
	 */
	@Getter
	@Setter
	private Date createTime;
	/**
	 * 修改时间
	 */
	@Getter
	@Setter
	private Date updateTime;
	/**
	 * 状态 0无效1有效
	 */
	@Getter
	@Setter
	private Integer status = 1;
	/**
	 * 截止时间 yyyy-MM-dd
	 */
	@Getter
	@Setter
	private String deadline;
	/**
	 * 创建用户Id
	 */
	@Getter
	@Setter
	private Integer sysUserId;

}
