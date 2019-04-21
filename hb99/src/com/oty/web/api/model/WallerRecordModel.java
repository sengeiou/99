package com.oty.web.api.model;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

/**
 * 余额记录模型（用于返回数据）
 * 
 * @author liuzheng
 *
 */
@ApiModel(value = "Waller", description = "余额记录")
public class WallerRecordModel {

	private String typeDesc = "";
	private BigDecimal amount = BigDecimal.ZERO;
	private String createDate = "";

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
