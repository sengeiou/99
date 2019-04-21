package com.oty.sys.entity; 
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统管理员模块表
 */
@Table(name = SysModule.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysModule extends BaseEntity {

	private static final long serialVersionUID = 1656716836897102843L;
	public static final String TABLE_NAME = "sys_module";
 
	/** 父级ID */
	@Getter
	@Setter
	private Integer parentId;
	/** 模块名称 */
	@Getter
	@Setter
	private String name;
	/** 是否可见0不可见1可见 */
	@Getter
	@Setter
	private Integer isVisible = 0;

}
