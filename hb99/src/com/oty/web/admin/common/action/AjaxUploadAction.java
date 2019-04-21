package com.oty.web.admin.common.action;
 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.commontemplate.util.coder.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
 
import com.oty.constant.Constants;
import com.oty.sys.entity.TFile;
import com.oty.sys.service.FileService; 
import com.oty.util.OssUtils;
import com.oty.web.base.action.BaseAction;

import pub.spring.mvc.view.JsonView;

@Controller("ajaxUpload") 
@RequestMapping("/admin/common/functions")
public class AjaxUploadAction extends BaseAction {

	@RequestMapping("ajax_upload")
	@ModelAttribute(JsonView.directResult)
	public Map<String, Object> execute(@RequestParam(required = false) MultipartFile file) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>(); 
		if (file == null || file.isEmpty()) {
			resultMap.put("code", 1);
			resultMap.put("msg", "上传文件为空");
			return resultMap;
		}
		try {
			byte[] bytes = file.getBytes();
			if (bytes.length > Constants.MAX_UPLOAD_SIZE) {
				throw new MaxUploadSizeExceededException(Constants.MAX_UPLOAD_SIZE);
			}
			String key = OssUtils.getKey(UUID.randomUUID().toString(), file.getOriginalFilename());
			new OssUtils().uploadBytes(file.getBytes(), key);
			TFile dbFile = new TFile();
			dbFile.setFileName(file.getOriginalFilename());
			dbFile.setFileSize(bytes.length);
			dbFile.setContentType(file.getContentType());
			dbFile.setUrl(OssUtils.getUrl(key));
			dbFile.setUploadTime(new Date());
			fileService.save(dbFile); 
			dataMap.put("src", dbFile.getUrl());
			dataMap.put("id", dbFile.getId());
			resultMap.put("data", dataMap);
		} catch (MaxUploadSizeExceededException ex) {
			resultMap.put("code", 1);
			resultMap.put("msg", "文件应不大于 " + getFileKB(ex.getMaxUploadSize()));
		}
		resultMap.put("code", 0);
		resultMap.put("msg", "上传成功");
		return resultMap;
	} 
	
	private String getFileKB(long byteFile) {
		if (byteFile == 0)
			return "0KB";
		long kb = 1024;
		return "" + byteFile / kb + "KB";
	}

	@Autowired
	private FileService fileService;  
	
}
