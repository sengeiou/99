package com.oty.sys.model.admin.user;

import com.oty.sys.entity.SysUser;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {

	private static final long serialVersionUID = -7144085950173722240L;
	@Getter @Setter
	private SysUser user;
	@Getter @Setter
	private List<Integer> roleIds;
 
}
