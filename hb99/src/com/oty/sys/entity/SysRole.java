package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统管理员角色表
 */
@Table(name = SysRole.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = -5474492064011132375L;
	public static final String TABLE_NAME = "sys_role";
 
	/** 名称 */
	@Getter
	@Setter
	private String name;
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

}
