package com.oty.sys.model.admin;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class NavtreeData implements Serializable {

	private static final long serialVersionUID = 5951940688064652250L;
	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String link;
	@Getter @Setter
	private Integer moduleId;
	@Getter @Setter
	private List<NavtreeData> subDatas;
	@Getter @Setter
	private String icon;
	@Getter @Setter
	private String code;
 
}