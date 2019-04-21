package com.oty.util;

import java.io.Serializable;

public class WordsResult implements Serializable {
 
	private static final long serialVersionUID = 8146979457894887344L;
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生日期
	 */
	private String day;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 身份证号码
	 */
	private String idCard;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
