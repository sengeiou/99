package com.oty.sys.entity;
 
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统区域信息表
 * 
 * @author lu
 *
 */
@Table(name = SysArea.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysArea extends BaseEntity {

	private static final long serialVersionUID = -1956530121173207065L;
	public static final String TABLE_NAME = "sys_area";
 
	@Getter
	@Setter
	private String areaname;
	@Getter
	@Setter
	private Integer parentid;
	@Getter
	@Setter
	private String shortname;
	@Getter
	@Setter
	private String lng;
	@Getter
	@Setter
	private String lat;
	@Getter
	@Setter
	private Integer level;
	@Getter
	@Setter
	private String position;
	@Getter
	@Setter
	private Integer sort;
	@Getter
	@Setter
	private String qqLng;
	@Getter
	@Setter
	private String qqLat;
	@Getter
	@Setter
	private String fullName;
	@Getter
	@Setter
	private String areaCode;

	@Transient
	private SysArea parents;

}
