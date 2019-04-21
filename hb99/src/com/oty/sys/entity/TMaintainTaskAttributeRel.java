package com.oty.sys.entity;
 
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 保养记录与保养参数关联表
 * 
 * @author lu
 *
 */
@Table(name = TMaintainTaskAttributeRel.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class TMaintainTaskAttributeRel extends BaseEntity {

	private static final long serialVersionUID = 900930022242635145L;
	public static final String TABLE_NAME = "t_maintain_task_attribute_rel";
 
	@Getter
	@Setter
	private Integer taskId;
	@Getter
	@Setter
	private Integer attributeId;
	@Getter
	@Setter
	private Integer createTime;
	@Getter
	@Setter
	private Integer updateTime;

}
