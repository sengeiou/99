package com.oty.web.admin.action;
 
import java.util.List; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

import pub.functions.StrFuncs;
 

import com.oty.sys.model.admin.NavtreeData;
import com.oty.sys.service.SysNavtreeService;  
import com.oty.web.base.action.BaseAction;  

@Controller("adminIndex")
@RequestMapping("/admin")
public class IndexAction extends BaseAction {

	@Autowired
	private SysNavtreeService navTreeService;

	@RequestMapping("/index.html")
	public String execute() {
		if (this.getSysUser() == null) {
			return "/login";
		}
		Integer userId = this.getSysUser().getId();
		NavtreeData rootData = navTreeService.getUserNavTree(userId); 
		request.setAttribute("rootData", rootData);
		return "/admin/index";
	}

	public class TreeNode {

		private List<NavtreeData> data;

		public TreeNode(List<NavtreeData> data) {
			this.data = data;
		}

		@Override
		public String toString() {
			String result = "";
			result += "[";
			boolean first = true;
			for (NavtreeData subData : data) {
				if (first) {
					first = false;
				} else {
					result += ",";
				}
				result += "{title: '" + subData.getName() + "',icon: '"
						+ (subData.getIcon() == null ? "" : subData.getIcon())
						+ "',";
				if (StrFuncs.notEmpty(subData.getLink())) {
					result += "href: '" + subData.getLink()
							+ "', spread: false";
				}
				if (subData.getSubDatas() != null
						&& subData.getSubDatas().size() > 0) {
					result += "children:";
					result += new TreeNode(subData.getSubDatas()).toString();
				}
				result += "}\n";
			}
			result += "]";
			return result;
		}
	}

}
