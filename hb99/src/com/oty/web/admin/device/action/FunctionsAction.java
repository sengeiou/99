package com.oty.web.admin.device.action;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oty.sys.entity.DeviceCommand; 
import com.oty.sys.entity.TDevice;
import com.oty.sys.service.DeviceCommandService;
import com.oty.sys.service.DeviceService;
import com.oty.util.Log4jUtil;
import com.oty.web.base.action.BaseAction;
 
import pub.functions.DateFuncs;
import pub.functions.StrFuncs;

@Controller("deviceFunctions")
@RequestMapping("/admin/device")
public class FunctionsAction extends BaseAction {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceCommandService deviceCommandService;

	/***
	 * 获取相关的全部二维码图片Zip包
	 */
	@RequestMapping("getQRUrlZip")
	public void getQRUrlZip(HttpServletRequest request, HttpServletResponse response, TDevice device) {
		try {
			String downloadFilename = "设备二维码" + DateFuncs.toString(new Date(), "yyyy-MM-dd") + ".zip";
			List<TDevice> pagehelper = deviceService.query(device, 1, 0);
			downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");// 转换中文否则可能会产生乱码
			response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
			response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			for (TDevice d : pagehelper) {
				if (StrFuncs.notEmpty(d.getUrl())) {
					URL url = new URL(d.getUrl());
					URLConnection connection = url.openConnection();
					connection.setUseCaches(true);
					zos.putNextEntry(new ZipEntry(d.getName() + "-" + d.getId() + ".jpg"));
					InputStream fis = connection.getInputStream();
					byte[] buffer = new byte[1024];
					int r = 0;
					while ((r = fis.read(buffer)) != -1) {
						zos.write(buffer, 0, r);
					}
					fis.close();
				}
			}
			zos.flush();
			zos.close();
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("sendOrder")
	@ResponseBody
	public Callable<Map<String, Object>> sendOrder(final Integer deviceId, final Integer attributeId, final String commandValue) {

		return new Callable<Map<String, Object>>() {

			public Map<String, Object> call() throws Exception {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				try {
					DeviceCommand deviceCommand = new DeviceCommand();
					deviceCommand.setDeviceId(deviceId);
					deviceCommand.setDeviceAttributeId(attributeId);
					deviceCommand.setCommandValue(commandValue);
					deviceCommandService.save(getUserId(), deviceCommand); 
					resultMap.put("code", 200);
					resultMap.put("msg", "请求成功");
				} catch (Exception e) {
					Log4jUtil.error(e.getMessage());
					resultMap.put("code", 500);
					resultMap.put("msg", "服务器异常,请联系客服");
					return resultMap;
				}
				return resultMap;
			}
		};

	}
	
	
	@RequestMapping("sendCommand")
	@ResponseBody
	public Callable<Map<String, Object>> sendCommand(final Integer deviceId, final Integer index, final String commandValue) {
		
		return new Callable<Map<String, Object>>() {
			
			public Map<String, Object> call() throws Exception {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				try {
					DeviceCommand deviceCommand = new DeviceCommand();
					deviceCommand.setDeviceId(deviceId);
					deviceCommand.setCommandIndex(index);
					deviceCommand.setCommandValue(commandValue);
					deviceCommandService.save(getUserId(), deviceCommand); 
					resultMap.put("code", 200);
					resultMap.put("msg", "请求成功");
				} catch (Exception e) {
					Log4jUtil.error(e.getMessage());
					resultMap.put("code", 500);
					resultMap.put("msg", "服务器异常,请联系客服");
					return resultMap;
				}
				return resultMap;
			}
		};
		
	}
 
}