package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备表属性
 *
 */
@Table(name = DeviceAttribute.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class DeviceAttribute extends BaseEntity {

	private static final long serialVersionUID = -4181040129781001289L;

	public static final String TABLE_NAME = "t_device_attribute";
 
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
	 * 设备类型id
	 */
	@Getter
	@Setter
	private Integer deviceTypeId;
	
	/**
	 * 属性代码
	 */
	@Getter
	@Setter
	private String attributeCode;
	
	/**
	 * 数据名称
	 */
	@Getter
	@Setter
	private String name;
	
	/**
	 * 阈值起
	 */
	@Getter
	@Setter
	private String thresholdStart;
	
	/**
	 * 阈值止
	 */
	@Getter
	@Setter
	private String thresholdStop;
	


}
