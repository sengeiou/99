package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 项目表
 * 
 * @author lu
 *
 */
@Table(name = TProject.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TProject extends BaseEntity {

	private static final long serialVersionUID = -3279185314971112778L;

	public static final String TABLE_NAME = "t_project";
	public static final String FIELD_TYPE = "type";
 
	/**
	 * 客户名称
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
	 * 创建人id
	 */
	@Getter
	@Setter
	private Integer createBy;
	/**
	 * 省份id
	 */
	@Getter
	@Setter
	private Integer provinceId;
	/**
	 * 城市id
	 */
	@Getter
	@Setter
	private Integer cityId;
	/**
	 * 城市id
	 */
	@Getter
	@Setter
	private Integer areaId;
	/**
	 * 详细地址
	 */
	@Getter
	@Setter
	private String address;
	/**
	 * 介绍
	 */
	@Getter
	@Setter
	private String introduce;
	/**
	 * 经度
	 */
	@Getter
	@Setter
	private String gpsX;
	/**
	 * 纬度
	 */
	@Getter
	@Setter
	private String gpsY;
	/**
	 * 行业id
	 */
	@Getter
	@Setter
	private Integer industryId;
	/**
	 * 类型id
	 */
	@Getter
	@Setter
	private Integer type;
	/**
	 * 负责人
	 */
	@Getter
	@Setter
	private String perLiable;

}
