package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = TDeviceParamRecord.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TDeviceParamRecord extends BaseEntity {
	  
	private static final long serialVersionUID = 8412335606006856545L;

	public static final String TABLE_NAME = "t_device_param_record";

	@Getter
	@Setter
	private Integer deviceId;
	@Getter
	@Setter
	private Integer projectId;
	@Getter
	@Setter
	private Date createTime;
	@Getter
	@Setter
	private Date updateTime;
	@Getter
	@Setter
	private double pm25;
	@Getter
	@Setter
	private double pm10;
	@Getter
	@Setter
	private double tsp;
	@Getter
	@Setter
	private double temperature;
	@Getter
	@Setter
	private double humidity;
	@Getter
	@Setter
	private Integer noise;
	@Getter
	@Setter
	private Integer windDirection;
	@Getter
	@Setter
	private double windSpeed;
	@Getter
	@Setter
	private double ozone;
	@Getter
	@Setter
	private String uuid;

}
