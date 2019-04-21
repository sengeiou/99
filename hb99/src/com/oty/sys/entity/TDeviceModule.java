package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备模块表
 * 
 * @author lu
 *
 */
@Table(name = TDeviceModule.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TDeviceModule extends BaseEntity {

	private static final long serialVersionUID = 3761421375467488565L;

	public static final String TABLE_NAME = "t_device_module";
	public static final String FIELD_STATUS = "status";
 
	/**
	 * mac地址
	 */
	@Getter
	@Setter
	private String mac;
	/**
	 * ip
	 */
	@Getter
	@Setter
	private String ip = "192.168.0.15";
	/**
	 * sim卡号
	 */
	@Getter
	@Setter
	private String sim;
	/**
	 * 激活状态
	 */
	@Getter
	@Setter
	private Integer status;
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
	/**
	 * 有效期 开始时间
	 */
	@Getter
	@Setter
	private Date sdate;
	/**
	 * 有效期 截止时间
	 */
	@Getter
	@Setter
	private Date edate;

}
