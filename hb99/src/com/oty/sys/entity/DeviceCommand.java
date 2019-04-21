package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 下发到设备的命令
 */
@Table(name = DeviceCommand.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class DeviceCommand extends BaseEntity {

	private static final long serialVersionUID = -3205312475185108633L;
	public static final String TABLE_NAME = "t_device_command";
 
	/** 创建时间 */
	@Getter
	@Setter
	private Date createTime;
	/** 修改时间 */
	@Getter
	@Setter
	private Date updateTime;
	/** 设备ID */
	@Getter
	@Setter
	private Integer deviceId;
	/** 设备属性ID */
	@Getter
	@Setter
	private Integer deviceAttributeId;
	/** 命令编号 */
	@Getter
	@Setter
	private Integer commandIndex;
	/** 命令值 */
	@Getter
	@Setter
	private String commandValue;
	/** 状态0未下发1已下发 */
	@Getter
	@Setter
	private Integer status = 0;
	
}
