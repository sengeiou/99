package com.oty.sys.entity;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统管理员模块角色关联表
 */
@Table(name = SysModuleRoleRel.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysModuleRoleRel extends BaseEntity {

	private static final long serialVersionUID = -6560654562901685396L;
	public static final String TABLE_NAME = "sys_module_role_rel";
 
	/** 系统管理员模块ID */
	@Getter
	@Setter
	private Integer moduleId;
	/** 系统管理员角色ID */
	@Getter
	@Setter
	private Integer roleId;

}
