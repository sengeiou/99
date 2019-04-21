package com.oty.web.admin.knowledge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.oty.sys.entity.Knowledge;
import com.oty.sys.service.KnowledgeService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;

import pub.types.RefData;

@Controller("knowledgeQuery")
@RequestMapping("/admin/knowledge")
public class QueryAction extends BaseAction {

	@ModelAttribute
	public RefData createRefData() {
		RefData refData = new RefData();
		refData.put("types", sysStatusService.list(Knowledge.TABLE_NAME, Knowledge.FIELD_TYPE));
		return refData;
	}

	@RequestMapping("/query.html")
	public void execute(Knowledge knowledge, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try {
			List<Knowledge> pagehelper = knowledgeService.query(knowledge, page);
			request.setAttribute("pagehelper", new PageInfo<Knowledge>(pagehelper));
			request.setAttribute("knowledge", knowledge);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private SysStatusService sysStatusService;

}
