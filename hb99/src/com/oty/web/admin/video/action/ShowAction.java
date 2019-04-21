package com.oty.web.admin.video.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oty.sys.entity.TVideoMonitor;
import com.oty.sys.service.ProjectService;
import com.oty.sys.service.VideoMonitorService;
import com.oty.util.Log4jUtil;
import com.oty.web.admin.video.utils.ValidateUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.JsValidator;
import pub.types.IdText;
import pub.types.RefData;

@Controller("videoShow")
@SessionAttributes("TVideoMonitor")
@RequestMapping("/admin/video")
public class ShowAction extends BaseAction {

	@ModelAttribute
	RefData createRefData() {
		RefData refData = new RefData();
		List<IdText> controls = new ArrayList<IdText>();
		controls.add(new IdText(1, "支持"));
		controls.add(new IdText(0, "不支持"));
		refData.put("controls", controls);
		refData.put("projects", projectService.getAll());
		return refData;
	}

	public JsValidator createJsValidator(TVideoMonitor videoMonitor) {
		JsValidator validator = new JsValidator(videoMonitor);
		ValidateUtils.setRules(validator);
		return validator;
	}

	@RequestMapping("/show.html")
	public void execute(ModelMap model, Integer id) {
		try {
			TVideoMonitor videoMonitor = (TVideoMonitor) model.get(TVideoMonitor.class.getName());
			if (videoMonitor != null) {

			} else if (id != null) {
				videoMonitor = videoMonitorService.get(id);
			} else {
				videoMonitor = new TVideoMonitor();
			}
			model.addAttribute("TVideoMonitor", videoMonitor);
			model.addAttribute(createJsValidator(videoMonitor));
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
		}
	}

	@Autowired
	private VideoMonitorService videoMonitorService;
	@Autowired
	private ProjectService projectService;

}
