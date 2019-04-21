package com.oty.web.admin.system.role.data;

import com.oty.sys.entity.SysModule;
 
public class RelModuleData {

	private SysModule module;
	private boolean hasRel;
	private String indent;

	public SysModule getModule() {
		return module;
	}

	public void setModule(SysModule module) {
		this.module = module;
	}

	public boolean isHasRel() {
		return hasRel;
	}

	public void setHasRel(boolean hasRel) {
		this.hasRel = hasRel;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}
}
