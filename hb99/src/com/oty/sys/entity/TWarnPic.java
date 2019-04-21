package com.oty.sys.entity; 

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 告警图片表
 *
 */
@Table(name = TWarnPic.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TWarnPic extends BaseEntity {

	private static final long serialVersionUID = 3875876624113282285L;

	public static final String TABLE_NAME = "t_warn_pic";

	/**
	 * 设备id
	 */
	@Getter
	@Setter
	private Integer deviceId;
	/**
	 * 创建时间
	 */
	@Getter
	@Setter
	private Date createTime;
	/**
	 * 图片地址
	 */
	@Getter
	@Setter
	private String url;
	/**
	 * 项目id
	 */
	@Getter
	@Setter
	private Integer projectId;
	/**
	 * 名称
	 */
	@Getter
	@Setter
	private String name;

}
