package com.oty.web.common.image.action;

import com.oty.constant.Constants;
import com.oty.sys.entity.TFile;
import com.oty.sys.service.FileService;
import com.oty.util.OssUtils;
import com.oty.web.base.action.BaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.commontemplate.util.coder.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/common/image")
@Api(value="AjaxUpload",description="文件上传接口")
public class AjaxUploadAction extends BaseAction { 

	@RequestMapping(value="/ajax_upload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(value = "文件上传", notes = "文件上传",httpMethod="POST")
	public Map<String, Object> regFirst(final @RequestParam(required = false) MultipartFile file) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (file == null || file.isEmpty()) {
				resultMap.put("code", 1);
				resultMap.put("msg", "上传文件为空");
				return resultMap;
			}
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
			Integer id = fileService.save(dbFile);
			resultMap.put("code", 200);
			resultMap.put("src", dbFile.getUrl());
			resultMap.put("id", id);
			resultMap.put("msg", "上传成功");
			return resultMap;
		} catch (MaxUploadSizeExceededException ex) {
			resultMap.put("code", 500);
			resultMap.put("msg", "文件应不大于 " + getFileKB(ex.getMaxUploadSize()));
			return resultMap;
		} catch (Exception e) {
			resultMap.put("code", 500);
			resultMap.put("msg", "服务器异常,请联系客服");
			return resultMap;
		}

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
