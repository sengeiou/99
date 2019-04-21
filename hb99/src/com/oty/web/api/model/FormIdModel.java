package com.oty.web.api.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
 
@ApiModel(value = "formId", description = "发送模板消息")
public class FormIdModel implements Serializable {

	private static final long serialVersionUID = 1928416896001106910L;
	private String formId = "";
	private Long createDateTime;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Long getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Long createDateTime) {
		this.createDateTime = createDateTime;
	}

	// @Override
	// public int compareTo(FormIdModel o) {
	// return o.getFormId().compareTo(this.getFormId());
	// }
	//
	//
	// @Override
	// public boolean equals(Object o) {
	// if (this == o) return true;
	// if (o == null || getClass() != o.getClass()) return false;
	// FormIdModel that = (FormIdModel) o;
	// return that.getFormId().equals(this.getFormId());
	// }
	//
	// @Override
	// public int hashCode() {
	// return this.getFormId().hashCode();
	// }
}
