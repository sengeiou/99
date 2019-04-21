package com.oty.web.admin.task.action;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo; 
import com.oty.sys.entity.TMaintainTask; 
import com.oty.sys.service.MaintainTaskService;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.SysStatusService;
import com.oty.web.base.action.BaseAction;
 
import pub.types.RefData;

@Controller("taskQuery")
@RequestMapping("/admin/task")
public class QueryAction extends BaseAction {

	@ModelAttribute
    RefData createRefData() {
        RefData refData = new RefData(); 
		refData.put("projects", projectService.getAll());
        refData.put("statuses", sysStatusService.list(TMaintainTask.TABLE_NAME, TMaintainTask.FIELD_STATUS)); 
        return refData;
    } 

	@RequestMapping("/query.html")
	public void execute(TMaintainTask maintainTask, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		try { 
			List<TMaintainTask> pagehelper = maintainTaskService.query(maintainTask, page);
			request.setAttribute("pagehelper", new PageInfo<TMaintainTask>(pagehelper));
			request.setAttribute("maintainTask", maintainTask);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	@Autowired
	private MaintainTaskService maintainTaskService;
	@Autowired
	private SysStatusService sysStatusService;
	@Autowired
	private ProjectService projectService;

}
