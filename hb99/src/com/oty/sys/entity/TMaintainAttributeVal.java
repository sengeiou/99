package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = TMaintainAttributeVal.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TMaintainAttributeVal extends BaseEntity {

	private static final long serialVersionUID = -284467829205683395L;
	public static final String TABLE_NAME = "t_maintain_attribute_val";
 
	/**
	 * 设备id
	 */
	@Getter
	@Setter
	private Integer deviceId;
	/**
	 * 保养记录id
	 */
	@Getter
	@Setter
	private Integer taskId;
	/**
	 * 类型
	 */
	@Getter
	@Setter
	private Integer type;
	/**
	 * 属性文本内容
	 */
	@Getter
	@Setter
	private String attributeTxt;
	/**
	 * 属性数值内容
	 */
	@Getter
	@Setter
	private double attributeNum;
	/**
	 * 属性单选值 0/1
	 */
	@Getter
	@Setter
	private Integer state;
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

}
