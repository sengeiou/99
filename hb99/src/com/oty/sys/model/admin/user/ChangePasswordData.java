package com.oty.sys.model.admin.user;

import java.io.Serializable; 

import lombok.Getter;
import lombok.Setter;

public class ChangePasswordData implements Serializable {

	private static final long serialVersionUID = 1033831176031892365L;

	@Getter @Setter
	private Integer userId;
	@Getter @Setter
	private String oldPassword;
	@Getter @Setter
	private String newPassword;
	@Getter @Setter
	private String confirmPassword;
 
}