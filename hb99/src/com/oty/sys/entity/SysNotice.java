package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 通知公告表
 */
@Table(name = SysNotice.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class SysNotice extends BaseEntity {

	private static final long serialVersionUID = 9168120688519003776L;

	public static final String TABLE_NAME = "sys_notice";
 
	/**
	 * 内容
	 */
	@Getter
	@Setter
	private String contents;
	/**
	 * 标题
	 */
	@Getter
	@Setter
	private String title;
	/**
	 * 是否置顶显示0:否 1:是
	 */
	@Getter
	@Setter
	private Integer isTop = 0;
	/**
	 * 管理员id
	 */
	@Getter
	@Setter
	private Integer sysUserId;
	/**
	 * 截止时间
	 */
	@Getter
	@Setter
	private String deadline;
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
