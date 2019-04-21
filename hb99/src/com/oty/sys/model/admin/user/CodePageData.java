package com.oty.sys.model.admin.user;
 
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class CodePageData implements Serializable {
 
	private static final long serialVersionUID = -8952132501079735057L;
	@Getter @Setter
	private Integer id;
	/**
	 * 邀请码
	 */
	@Getter @Setter
	private String code;
	/**
	 * 有状态（使用、未使用）
	 */
	@Getter @Setter
	private Integer status;

	/**
	 * 有效期
	 */
	@Getter @Setter
	private Date validDate;

	/**
	 * 创建日期
	 */
	@Getter @Setter
	private Date createTime;
 
}
