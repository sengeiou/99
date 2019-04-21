package com.oty.sys.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseModel implements Serializable {
 
	private static final long serialVersionUID = 2539516018935036745L;

	public abstract String toString();

	/**
	 * 总记录数
	 * */
	@Getter @Setter
	protected int totalResults;

	/**
	 * 标识是否分页，等于1 则为分页
	 */
	@Getter @Setter
	protected int pagerFlag;
	
	/**
	 * 起始行数
	 */
	@Getter @Setter
	protected int rows;
	/**
	 * 显示的记录数
	 */
	@Getter @Setter
	protected int rowsCount;

	/**
	 * 起始条数数
	 */
	@Getter @Setter
	protected Integer startsize;

	/**
	 * 结束条数
	 */
	@Getter @Setter
	protected int endsize;

	@Getter @Setter
	protected String keyWord;

	@Getter @Setter
	protected Long id;

	@Getter @Setter
	protected String createdate = "";

	@Getter @Setter
	protected Long createuser;

	@Getter @Setter
	protected String createuserName;

	@Getter @Setter
	protected String updatedate = "";

	@Getter @Setter
	protected Long updateuser;

	@Getter @Setter
	protected String updateuserName;
 
}
