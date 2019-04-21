package com.oty.sys.entity;

import java.util.Date;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 保养参数表
 * 
 * @author lu
 *
 */
@Table(name = TMaintainAttribute.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TMaintainAttribute extends BaseEntity {

	private static final long serialVersionUID = -4315527931379077776L;
	public static final String TABLE_NAME = "t_maintain_attribute";
	public static final String FIELD_TYPE = "type";
 
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
	 * 参数名称
	 */
	@Getter
	@Setter
	private String name;
	/**
	 * 说明
	 */
	@Getter
	@Setter
	private String remark;
	/**
	 * 起始值
	 */
	@Getter
	@Setter
	private double normalStartVal;
	/**
	 * 终止值
	 */
	@Getter
	@Setter
	private double normalEndVal;
	/**
	 * 参数类型 0数值类型 1文本类型 2单选类型
	 */
	@Getter
	@Setter
	private Integer type;

}
