package com.oty.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
public class AuthService {

	static HashMap<String, String> errorCodes = new HashMap<String, String>();
	static HashMap<String, String> labels = new HashMap<String, String>();

	static {
		errorCodes.put("1", "服务器内部错误，请再次请求");
		errorCodes.put("2", "服务暂不可用，请再次请求");
		errorCodes.put("3", "调用的API不存在，请检查后重新尝试");
		errorCodes.put("4", "集群超限额");
		errorCodes.put("6", "无权限访问该用户数据");
		errorCodes.put("17", "每天请求量超限额");
		errorCodes.put("18", "QPS超限额");
		errorCodes.put("19", "请求总量超限额");
		errorCodes.put("100", "包含了无效或错误参数，请检查代码");
		errorCodes.put("110", "Access Token失效");
		errorCodes.put("111", "Access token过期");
		errorCodes.put("282000", "服务器内部错误，请再次请求");
		errorCodes.put("282002", "编码错误，请使用UTF-8编码");
		errorCodes.put("282131", "输入长度超限，请查看文档说明");
		errorCodes.put("282133", "接口参数缺失，如未填入content");
		errorCodes.put("282134", "输入为空");

		labels.put("1", "暴恐违禁");
		labels.put("2", "文本色情");
		labels.put("3", "政治敏感");
		labels.put("4", "恶意推广");
		labels.put("5", "低俗辱骂");
	}

	/**
	 * 获取权限token
	 * 
	 * @return 返回示例： { "access_token":
	 *         "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
	 *         "expires_in": 2592000 }
	 */
	public static String getAuth() {
		// 官网获取的 API Key 更新为你注册的
		String clientId = "erbvP0lFTEIlT7heN3yZxE8m";
		// 官网获取的 Secret Key 更新为你注册的
		String clientSecret = "6Cb6AEsKaLssclz1j2F6ZTA3zNkf87KS";
		return getAuth(clientId, clientSecret);
	}

	/**
	 * 获取API访问token 该token有一定的有效期，需要自行管理，当失效时需重新获取.
	 * 
	 * @param ak
	 *            - 百度云官网获取的 API Key
	 * @param sk
	 *            - 百度云官网获取的 Securet Key
	 * @return assess_token 示例：
	 *         "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
	 */
	public static String getAuth(String ak, String sk) {
		// 获取token地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// 1. grant_type为固定参数
				+ "grant_type=client_credentials"
				// 2. 官网获取的 API Key
				+ "&client_id=" + ak
				// 3. 官网获取的 Secret Key
				+ "&client_secret=" + sk;
		try {
			URL realUrl = new URL(getAccessTokenUrl);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.err.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = "";
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			/**
			 * 返回结果示例
			 */
			System.err.println("result:" + result);
			JSONObject jsonObject = new JSONObject(result);
			String access_token = jsonObject.getString("access_token");
			return access_token;
		} catch (Exception e) {
			System.err.printf("获取token失败！");
			e.printStackTrace(System.err);
		}
		return null;
	}

	public static void main(String args[]) {
		String accessToken = AuthService.getAuth();
		//
		/**
		 * {"access_token":"24.5ff48ffa355a991485107bcd69d4e1bb.2592000.1539238622.282335-11800067",
		 * "session_key":"9mzdDtQoIbMGvDyYqOlmTk2kibuiIMtpqdLRQDWpgUkHdxBEoHTBk8lYGjbvAZ6sHH6q7l6+hyJMDm3k9uHpoMoAdgraCw==",
		 * "scope":"public vis-antiporn_antiporn_v2 vis-classify_watermark
		 * brain_gif_antiporn vis-classify_terror brain_all_scope solution_face
		 * brain_antiporn brain_antiterror
		 * vis-classify_\u6076\u5fc3\u56fe\u8bc6\u522b\u670d\u52a1
		 * brain_politician brain_imgquality_general brain_watermark
		 * brain_disgust brain_antispam_spam wise_adapt lebo_resource_base
		 * lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian
		 * ApsMisTest_Test\u6743\u9650 vis-classify_flower lpq_\u5f00\u653e
		 * cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base
		 * iop_autocar oauth_tp_app smartapp_smart_game_openapi",
		 * "refresh_token":"25.54270c19e42cdc5e35756b7792d921aa.315360000.1852006622.282335-11800067",
		 * "session_secret":"afdda76da97729ab8ed21cb8c5958ef9",
		 * "expires_in":2592000}
		 */
		String url = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("access_token", accessToken);
		parameterMap.put("content", "共产党");
		String result = HttpUtils.postx(url, parameterMap);
		System.out.println(result);
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
		if (jsonObject.containsKey("error_code")) {
			System.out.println(jsonObject.get("error_code"));
			System.out.println(errorCodes.get(jsonObject.getString("error_code")));
			return;
		}
		if (jsonObject.containsKey("result")) {
			net.sf.json.JSONObject resultObject = jsonObject.getJSONObject("result");
			if (resultObject.containsKey("spam")) {
				String spam = resultObject.getString("spam");
				if ("0".equals(spam)) {
					System.out.println("非违禁");
				} else if ("1".equals(spam)) {
					System.out.println("违禁");
					net.sf.json.JSONArray rejects = resultObject.getJSONArray("reject");
					if (!rejects.isEmpty()) {
						for (int i = 0; i < rejects.size(); i++) {
							net.sf.json.JSONObject rejectObject = rejects.getJSONObject(i);
							double score = rejectObject.getDouble("score");
							if (score > 0.6) {
								String label = rejectObject.getString("label");
								System.out.println(labels.get(label));
							}
						}
					}
				} else if ("2".equals(spam)) {
					System.out.println("人工复审");
					net.sf.json.JSONArray reviews = resultObject.getJSONArray("review");
					if (!reviews.isEmpty()) {
						for (int i = 0; i < reviews.size(); i++) {
							net.sf.json.JSONObject reviewObject = reviews.getJSONObject(i);
							double score = reviewObject.getDouble("score");
							if (score > 0.6) {
								String label = reviewObject.getString("label");
								System.out.println(labels.get(label));
							}
						}
					}
				} else {
					System.out.println("数据解析异常");
				}
			}
		}
	}

}