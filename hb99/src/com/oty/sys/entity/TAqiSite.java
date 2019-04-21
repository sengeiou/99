package com.oty.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = TAqiSite.TABLE_NAME)
public class TAqiSite extends BaseEntity {

	private static final long serialVersionUID = -1129787265636253434L;
	public static final String TABLE_NAME = "t_aqi_site";

	@Getter
	@Setter
	private Integer aqiId;
    /** 国控站点名称 */
	@Getter
	@Setter
	private String siteName;
    /** 一氧化碳1小时平均，mg/m3 */
	@Getter
	@Setter
	private BigDecimal co;
    /** 二氧化硫1小时平均浓度，ug/m3 */
	@Getter
	@Setter
	private BigDecimal so2;
    /** 臭氧1小时平均，μg/m3 */
	@Getter
	@Setter
	private BigDecimal o3;
    /** 二氧化氮1小时平均，μg/m3 */
	@Getter
	@Setter
	private BigDecimal no2;
	/**  空气质量指数(AQI)，即air quality index，是定量描述空气质量状况的无纲量指数 */
	@Getter
	@Setter
	private Integer aqi;
    /** 空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类 */
	@Getter
	@Setter
	private String quality;
    /** 颗粒物（粒径小于等于10μm）1小时平均，μg/m3 */
	@Getter
	@Setter
	private BigDecimal pm10;
    /** 颗粒物（粒径小于等于2.5μm）1小时平均，μg/m3 */
	@Getter
	@Setter
	private BigDecimal pm25;
	/** 臭氧8小时滑动平均 */
	@Getter
	@Setter
	private BigDecimal o3H8;
    /** 首要污染物 */
	@Getter
	@Setter
	private String primaryPollutant;
    /** 数据更新时间 */
	@Getter
	@Setter
	private Date ct;

}
