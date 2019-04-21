package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = TMessage.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TMessage extends BaseEntity {

	private static final long serialVersionUID = 8366986335856578650L;

	public static final String TABLE_NAME = "t_message";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_TYPE = "type";
 
	/**
	 * 发送状态 0未读1已读
	 */
	@Getter
	@Setter
	private Integer status;
	/**
	 * 发送时间
	 */
	@Getter
	@Setter
	private Date preDate;
	/**
	 * 发送用户id sys_user.id
	 */
	@Getter
	@Setter
	private Integer sendUserId;
	/**
	 * 接收用户id sys_user.id
	 */
	@Getter
	@Setter
	private Integer receiveUserId;
	/**
	 * 设备id t_device.id
	 */
	@Getter
	@Setter
	private Integer deviceId;
	/**
	 * 消息类型
	 */
	@Getter
	@Setter
	private Integer type;
	/**
	 * 发送内容
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
