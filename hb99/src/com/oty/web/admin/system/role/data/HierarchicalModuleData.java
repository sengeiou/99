package com.oty.web.admin.system.role.data;

import com.oty.sys.entity.SysModule;

import java.util.List;
 
public class HierarchicalModuleData {

	private SysModule module;
	private List<HierarchicalModuleData> subDatas;

	public SysModule getModule() {
		return module;
	}

	public void setModule(SysModule module) {
		this.module = module;
	}

	public List<HierarchicalModuleData> getSubDatas() {
		return subDatas;
	}

	public void setSubDatas(List<HierarchicalModuleData> subDatas) {
		this.subDatas = subDatas;
	}
}
