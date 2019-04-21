package com.oty.sys.entity; 
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统管理员菜单表
 */
@Table(name = SysNavtree.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysNavtree extends BaseEntity {

	private static final long serialVersionUID = 5103024879040205475L;
	public static final String TABLE_NAME = "sys_navtree";
 
	/** 父级ID */
	@Getter
	@Setter
	private Integer parentId;
	/** 名称 */
	@Getter
	@Setter
	private String name;
	/** 权限ID */
	@Getter
	@Setter
	private Integer moduleId;
	/** 是否可见0 不可见1可见 */
	@Getter
	@Setter
	private Integer isVisible;
	/** 链接 */
	@Getter
	@Setter
	private String link;
	/** 图标 */
	@Getter
	@Setter
	private String icon;
	/** 图标 */
	@Getter
	@Setter
	private String code;

}
