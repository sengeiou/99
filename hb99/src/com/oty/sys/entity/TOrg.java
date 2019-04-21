package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = TOrg.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TOrg extends BaseEntity {

	private static final long serialVersionUID = 2924780178422208101L;

	public static final String TABLE_NAME = "t_org";
 
	/**
	 * 机构名称
	 */
	@Getter
	@Setter
	private String name;
	/**
	 * 机构描述
	 */
	@Getter
	@Setter
	private String remark;
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
	 * 删除标记 0否1是
	 */
	@Getter
	@Setter
	private Integer delFlag = 0;

}
