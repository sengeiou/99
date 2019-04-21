package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统管理员表
 */
@Table(name = SysUser.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 4323060219510835212L;
	public static final String TABLE_NAME = "sys_user";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_STATUS = "status";
 
	/** 帐号 */
	@Getter
	@Setter
	private String account;
	/** 密码 */
	@Getter
	@Setter
	private String password;
	/** 姓名 */
	@Getter
	@Setter
	private String name;
	/** 手机号码 */
	@Getter
	@Setter
	private String mobile;
	/** 是否激活 0未激活1已激活 */
	@Getter
	@Setter
	private Integer status = 1;
	/** 是否管理员0否1是 */
	@Getter
	@Setter
	private Integer isManager = 0;
	/** 省级ID */
	@Getter
	@Setter
	private Integer provinceId;
	/** 城市ID */
	@Getter
	@Setter
	private Integer cityId;
	/** 区域ID */
	@Getter
	@Setter
	private Integer areaId;
	/** 详细地址 */
	@Getter
	@Setter
	private String address;
	/** 邮箱 */
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private Date createTime;
	@Getter
	@Setter
	private Date updateTime;
	/**
	 * 微信号
	 */
	@Getter
	@Setter
	private String weixin;
	/**
	 * 所在部门
	 */
	@Getter
	@Setter
	private String dept;
	/**
	 * 职位
	 */
	@Getter
	@Setter
	private String job;
	/**
	 * 删除标记 0否1是
	 */
	@Getter
	@Setter
	private Integer delFlag;
	/**
	 * 所在机构类型 0系统管理员 1客户机构 2运维机构
	 */
	@Getter
	@Setter
	private Integer type;
	/**
	 * 头像地址
	 */
	@Getter
	@Setter
	private String avatar; 

}
