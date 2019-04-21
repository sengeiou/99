package com.oty.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 知识库
 *  
 */
@Table(name = Knowledge.TABLE_NAME)
@ToString(callSuper = false, includeFieldNames = true)
public class Knowledge extends BaseEntity {

	private static final long serialVersionUID = -4181040129781001289L;

	public static final String TABLE_NAME = "t_knowledge";
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
	 * 类型
	 */
	@Getter
	@Setter
	private Integer type;
	
	/**
	 * 标题
	 */
	@Getter
	@Setter
	private String title;
	
	/**
	 * 内容
	 */
	@Getter
	@Setter
	private String content;

}
