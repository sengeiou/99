package com.oty.sys.model.admin.role;

import java.io.Serializable;
import java.util.List;

import com.oty.sys.entity.SysRole;

import lombok.Getter;
import lombok.Setter;

public class RoleData implements Serializable {

	private static final long serialVersionUID = -3661152953303631275L;
	@Getter @Setter
	public SysRole role;
	@Getter @Setter
	public List<Integer> moduleIds;
 
}
