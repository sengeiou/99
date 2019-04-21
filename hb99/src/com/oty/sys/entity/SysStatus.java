package com.oty.sys.entity;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统状态表
 */
@Table(name = SysStatus.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysStatus extends BaseEntity {

	private static final long serialVersionUID = 3846114002231429208L;

	public static final String TABLE_NAME = "sys_status";
 
	/**
	 * 表名
	 */
	@Getter
	@Setter
	private String tableName;
	/**
	 * 字段名
	 */
	@Getter
	@Setter
	private String fieldName;
	/**
	 * 编码
	 */
	@Getter
	@Setter
	private Integer code;
	/**
	 * 值
	 */
	@Getter
	@Setter
	private String value;

}