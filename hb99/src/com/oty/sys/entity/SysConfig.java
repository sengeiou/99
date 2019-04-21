package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = SysConfig.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysConfig extends BaseEntity {

	private static final long serialVersionUID = -2646489606739362188L;

	public static final String TABLE_NAME = "sys_config";
 
	@Getter
	@Setter
	private String configKey;
	@Getter
	@Setter
	private double configValue;
	@Getter
	@Setter
	private Date createTime;
	@Getter
	@Setter
	private Date updateTime;
	@Getter
	@Setter
	private String remark;

}
