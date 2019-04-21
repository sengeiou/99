package com.oty.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

public class GetCityPMUtils {

	/**
	 * 请求接口
	 * 
	 * @param method
	 *            请求方法
	 * @param url
	 *            请求地址
	 * @param headers
	 *            请求头部
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String proxyToDesURL(String method, String url, Map<String, String> headers,
			Map<String, String> params) {
		try {
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			// 处理请求头部
			HttpHeaders requestHeaders = new HttpHeaders();
			if (headers != null && !headers.isEmpty()) {
				Set<String> set = headers.keySet();
				for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
					String key = iterator.next();
					String value = headers.get(key);
					requestHeaders.add(key, value);
				}
			}
			// 处理请求参数
			MultiValueMap<String, String> paramList = new LinkedMultiValueMap<String, String>();
			if (params != null && !params.isEmpty()) {
				if (method.equalsIgnoreCase("GET")) {
					url += "?";
					Set<String> set = params.keySet();
					for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
						String key = iterator.next();
						String value = params.get(key);
						url += key + "=" + value + "&";
					}
					url = url.substring(0, url.length() - 1);
				} else {
					Set<String> set = params.keySet();
					for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
						String key = iterator.next();
						String value = params.get(key);
						paramList.add(key, value);
					}
				}
			}
			requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					paramList, requestHeaders);
			// 处理请求方法
			HttpMethod requestType = HttpMethod.GET;
			method = method.toUpperCase();
			switch (method) {
			case "GET":
				requestType = HttpMethod.GET;
				break;
			case "POST":
				requestType = HttpMethod.POST;
				break;
			case "PUT":
				requestType = HttpMethod.PUT;
				break;
			case "DELETE":
				requestType = HttpMethod.DELETE;
				break;
			case "HEAD":
				requestType = HttpMethod.HEAD;
				break;
			case "OPTIONS":
				requestType = HttpMethod.OPTIONS;
				break;
			default:
				requestType = HttpMethod.GET;
				break;
			}
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, requestType, requestEntity, String.class,
					params);
			return responseEntity.getBody(); // 获取返回结果
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject getCityPM25Detail(String apiKey, String city) { 
		String url = "https://api.apishop.net/common/air/getCityPM25Detail";// 请求地址设置 
		String requestMethod = "POST";// 请求方法设置 
		Map<String, String> headers = new HashMap<String, String>();// 请求头部设置 
		Map<String, String> params = new HashMap<String, String>();// 请求参数设置
		params.put("apiKey", apiKey);
		params.put("city", city);
		String result = proxyToDesURL(requestMethod, url, headers, params);
		if (result != null) {
			JSONObject jsonObject = JSONObject.parseObject(result);
			String status_code = jsonObject.getString("statusCode");
			if (status_code.equals("000000")) { 
				System.out.println("请求成功：" + jsonObject.getString("result"));// 状态码为000000, 说明请求成功
			} else { 
				System.out.println("请求失败：" + city + jsonObject.getString("desc"));// 状态码非000000, 说明请求失败
				Log4jUtil.error("请求失败：" + city + jsonObject.getString("desc"));// 状态码非000000, 说明请求失败
			}
			return jsonObject;
		} else {
			System.out.println("发送请求失败"); // 返回内容异常，发送请求失败，以下可根据业务逻辑自行修改
		}
		return null;
	}

	public static JSONObject getPM25Top(String apiKey) { 
		String url = "https://api.apishop.net/common/air/getPM25Top";// 请求地址设置  
		String requestMethod = "POST";// 请求方法设置 
		Map<String, String> headers = new HashMap<String, String>();// 请求头部设置 
		Map<String, String> params = new HashMap<String, String>();// 请求参数设置
		params.put("apiKey", apiKey);
		String result = proxyToDesURL(requestMethod, url, headers, params);
		if (result != null) {
			JSONObject jsonObject = JSONObject.parseObject(result);
			String status_code = jsonObject.getString("statusCode");
			if (status_code.equals("000000")) { 
				System.out.println("请求成功：" + jsonObject.getString("result"));// 状态码为000000, 说明请求成功
			} else { 
				System.out.println("请求失败：" + jsonObject.getString("desc"));// 状态码非000000, 说明请求失败
			}
			return jsonObject;
		} else { 
			System.out.println("发送请求失败");// 返回内容异常，发送请求失败，以下可根据业务逻辑自行修改
		}
		return null;
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// 请求地址设置
		String url = "https://api.apishop.net/common/air/getCityPM25Detail";
		// 请求方法设置
		String requestMethod = "POST";
		// 请求头部设置
		Map<String, String> headers = new HashMap<String, String>();
		// 请求参数设置
		Map<String, String> params = new HashMap<String, String>();
		params.put("apiKey", "参数1");
		params.put("city", "参数2");
		String result = proxyToDesURL(requestMethod, url, headers, params);
		if (result != null) {
			JSONObject jsonObject = JSONObject.parseObject(result);
			String status_code = jsonObject.getString("statusCode");
			if (status_code.equals("000000")) {
				// 状态码为000000, 说明请求成功
				System.out.println("请求成功：" + jsonObject.getString("result"));
			} else {
				// 状态码非000000, 说明请求失败
				System.out.println("请求失败：" + jsonObject.getString("desc"));
			}
		} else {
			// 返回内容异常，发送请求失败，以下可根据业务逻辑自行修改
			System.out.println("发送请求失败");
		}
	}

}
