package com.oty.sys.entity;
 
import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = SysOperLog.TABLE_NAME)
public class SysOperLog extends BaseEntity {

	private static final long serialVersionUID = -4157738547229392459L;

	public static final String TABLE_NAME = "sys_oper_log";
	public static final String FIELD_OPER_TYPE = "oper_type";

	public static final int OPER_TYPE_ADD = 0;
	public static final int OPER_TYPE_UPDATE = 1;
	public static final int OPER_TYPE_DELETE = 2;
 
	/**
	 * 操作用户id
	 */
	@Getter
	@Setter
	private Integer sysUserId;
	/**
	 * 操作类型 0新增1编辑2删除
	 */
	@Getter
	@Setter
	private Integer operType;
	/**
	 * 操作时间
	 */
	@Getter
	@Setter
	private Date createTime;
	/**
	 * 操作内容
	 */
	@Getter
	@Setter
	private String content;
	/**
	 * 操作对象id
	 */
	@Getter
	@Setter
	private Integer refId;
	/**
	 * 详细信息 json
	 */
	@Getter
	@Setter
	private String remark;

}
