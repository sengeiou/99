package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统管理员角色关联表
 */
@Table(name = SysUserRoleRel.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysUserRoleRel extends BaseEntity {

	private static final long serialVersionUID = 6290982524001567023L;
	public static final String TABLE_NAME = "sys_user_role_rel";
 
	/** 系统管理员ID */
	@Getter
	@Setter
	private Integer userId;
	/** 角色ID */
	@Getter
	@Setter
	private Integer roleId;
	@Getter
	@Setter
	private Date createTime;
	@Getter
	@Setter
	private Date updateTime;

}
