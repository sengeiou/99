package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 设备上传数据
 */
@Table(name = DeviceUpload.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class DeviceUpload extends BaseEntity {

	private static final long serialVersionUID = -5418394709221127897L;
	public static final String TABLE_NAME = "t_device_upload";
 
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
	/** 请求ID */
	@Getter
	@Setter
	private String requestId;
	/** 设备属性ID */
	@Getter
	@Setter
	private Integer deviceAttributeId;
	/** 参数名称 */
	@Getter
	@Setter
	private String paramName;
	/** 参数值 */
	@Getter
	@Setter
	private String paramValue;
	
}
