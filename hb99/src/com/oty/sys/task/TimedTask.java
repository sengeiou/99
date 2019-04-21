package com.oty.sys.task;

import java.math.BigDecimal; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oty.sys.entity.SysArea;
import com.oty.sys.entity.TAqi;
import com.oty.sys.entity.TAqiSite;
import com.oty.sys.service.AqiService;
import com.oty.sys.service.AqiSiteService;
import com.oty.sys.service.SysAreaService;
import com.oty.util.GetCityPMUtils;
import com.oty.util.Log4jUtil;

import pub.functions.DateFuncs;
import pub.functions.StrFuncs; 

/**
 * Task -定时任务
 */
@Lazy(false)
@Component
public class TimedTask {

	@Autowired
	private AqiService aqiService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private AqiSiteService aqiSiteService;

	private static final String apiKey = "QU59Q2Hcac86bc739f8cd12c568de8bb89362891825a063";

	/**
	 * 获取所有地区空气质量最近一次观测值
	 */
	@Scheduled(cron = "0 10 * * * ?")
	@Transactional
	public void getAllAreaAQI() {
		try {
			Log4jUtil.error("开始---获取所有地区空气质量最近一次观测值---定时任务");
			List<SysArea> sysAreas = sysAreaService.findByLevel(2);
			for (SysArea sysArea : sysAreas) {
				JSONObject jsonObject = GetCityPMUtils.getCityPM25Detail(apiKey, sysArea.getAreaname());
				if (jsonObject != null) {
					if (jsonObject.containsKey("statusCode")) {
						String statusCode = jsonObject.getString("statusCode");
						if ("000000".equals(statusCode)) {
							if (jsonObject.containsKey("result")) {
								JSONObject result = jsonObject.getJSONObject("result");
								if (result.containsKey("pm")) {
									JSONObject pm = result.getJSONObject("pm");
									if (pm != null) {
										TAqi aqi = new TAqi();
										aqi.setAqi(Integer.valueOf(getStr(pm.getString("aqi"))));
										aqi.setAreaId(sysArea.getId());
										aqi.setCo(new BigDecimal(getStr(pm.getString("co"))));
										aqi.setCt(DateFuncs.strToDateTime(pm.getString("ct"), "yyyy-MM-dd HH:mm:ss"));
										aqi.setNo2(new BigDecimal(getStr(pm.getString("no2"))));
										aqi.setNum(pm.getInteger("num"));
										aqi.setO3(new BigDecimal(getStr(pm.getString("o3"))));
										aqi.setO3H8(new BigDecimal(getStr(pm.getString("o3_8h"))));
										aqi.setPm10(new BigDecimal(getStr(pm.getString("pm10"))));
										aqi.setPm25(new BigDecimal(getStr(pm.getString("pm2_5"))));
										aqi.setPrimaryPollutant(pm.getString("primary_pollutant"));
										aqi.setQuality(pm.getString("quality"));
										aqi.setSo2(new BigDecimal(getStr(pm.getString("so2"))));
										aqiService.save(aqi);

										if (result.containsKey("siteList")) {
											JSONArray siteList = result.getJSONArray("siteList");
											if (siteList != null && !siteList.isEmpty()) {
												for (int i = 0; i < siteList.size(); i++) {
													JSONObject site = siteList.getJSONObject(i);
													TAqiSite aqiSite = new TAqiSite();
													aqiSite.setAqi(Integer.valueOf(getStr(site.getString("aqi"))));
													aqiSite.setCo(new BigDecimal(getStr(site.getString("co"))));
													aqiSite.setCt(DateFuncs.strToDateTime(site.getString("ct"), "yyyy-MM-dd HH:mm:ss"));
													aqiSite.setNo2(new BigDecimal(getStr(site.getString("no2"))));
													aqiSite.setO3(new BigDecimal(getStr(site.getString("o3"))));
													aqiSite.setO3H8(new BigDecimal(getStr(site.getString("o3_8h"))));
													aqiSite.setPm10(new BigDecimal(getStr(site.getString("pm10"))));
													aqiSite.setPm25(new BigDecimal(getStr(site.getString("pm2_5"))));
													aqiSite.setPrimaryPollutant(site.getString("primary_pollutant"));
													aqiSite.setQuality(site.getString("quality"));
													aqiSite.setSiteName(site.getString("site_name"));
													aqiSite.setSo2(new BigDecimal(getStr(site.getString("so2"))));
													aqiSite.setAqiId(aqi.getId());
													aqiSiteService.save(aqiSite);
												}
											}
										}
									}
								}
							}
						} else {
							Log4jUtil.error("请求失败：" + jsonObject.getString("desc"));
						}
					}
				}
			}
			Log4jUtil.error("获取所有地区空气质量最近一次观测值---定时任务执行完成");
		} catch (Exception e) {
			Log4jUtil.error("异常信息：", e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String getStr(String str){
		if(!StrFuncs.notEmpty(str) || "-".equals(str) || "_".equals(str)){
			return "0";
		} else{
			return str.trim();
		}
	} 

}
