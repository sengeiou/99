package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备类型表
 * 
 * @author lu
 *
 */
@Table(name = TDeviceType.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TDeviceType extends BaseEntity {

	private static final long serialVersionUID = -6429858665252587315L;
	public static final String TABLE_NAME = "t_device_type";
 
	/**
	 * 名称
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
	 * 说明
	 */
	@Getter
	@Setter
	private String remark;
	/**
	 * 删除标记 1否1是
	 */
	@Getter
	@Setter
	private Integer delFlag = 0;
	/**
	 * 固定式设备0 检测仪 1
	 */
	@Getter
	@Setter
	private Integer type =1;

}
