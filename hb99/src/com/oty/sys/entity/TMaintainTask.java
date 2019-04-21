package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 保养记录表
 * 
 * @author lu
 *
 */
@Table(name = TMaintainTask.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TMaintainTask extends BaseEntity {

	private static final long serialVersionUID = -3965544634052257644L;
	public static final String TABLE_NAME = "t_maintain_task";
	public static final String FIELD_STATUS = "status";
 
	/**
	 * 任务名称
	 */
	@Getter
	@Setter
	private String name;
	/**
	 * 设备id
	 */
	@Getter
	@Setter
	private Integer deviceId;
	/**
	 * 记录时间
	 */
	@Getter
	@Setter
	private Date recordTime;
	/**
	 * 记录人id
	 */
	@Getter
	@Setter
	private Integer recordUserId;
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
	 * 说明
	 */
	@Getter
	@Setter
	private String remark;
	/**
	 * 删除标记 0否1是
	 */
	@Getter
	@Setter
	private Integer delFlag = 0;
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
	 * 项目id
	 */
	@Getter
	@Setter
	private Integer projectId;
	/**
	 * 状态 0待完成 1已完成 -1已取消
	 */
	@Getter
	@Setter
	private Integer status = 0;
	/**
	 * 任务开始时间
	 */
	@Getter
	@Setter
	private Date sdate;
	/**
	 * 任务结束时间
	 */
	@Getter
	@Setter
	private Date edate;
	/**
	 * 是否告警
	 */
	@Getter
	@Setter
	private Integer isError = 0;

}
