package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = TAlarm.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TAlarm extends BaseEntity {

	private static final long serialVersionUID = 1647353581193972886L;
	public static final String TABLE_NAME = "t_alarm"; 
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_TYPE = "type";
	public static final Integer TYPE_DEVICE = 0;
	public static final Integer TYPE_OVERRUN = 1;

	/**
	 * 设备id
	 */
	@Getter
	@Setter
	private Integer deviceId;
	/**
	 * 项目id
	 */
	@Getter
	@Setter
	private Integer projectId;
	/**
	 * 报警类型
	 */
	@Getter
	@Setter
	private Integer type;
	/**
	 * 状态 0未处理 1已处理
	 */
	@Getter
	@Setter
	private Integer status;
	/**
	 * 报警数值
	 */
	@Getter
	@Setter
	private String num;
	/**
	 * 报警内容
	 */
	@Getter
	@Setter
	private String content;
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

}
