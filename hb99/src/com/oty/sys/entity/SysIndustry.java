package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 行业表
 * 
 */
@Table(name = SysIndustry.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysIndustry extends BaseEntity {

	private static final long serialVersionUID = 564307705503818366L;
	public static final String TABLE_NAME = "sys_industry";
	public static final String FIELD_IS_ENABLE = "is_enable";
 
	/**
	 * 行业名称
	 */
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
	/**
	 * 是否有效
	 */
	@Getter
	@Setter
	private Integer isEnable = 1;
	/**
	 * 说明
	 */
	@Getter
	@Setter
	private String remark;

}
