package com.oty.util;

import org.json.JSONObject;
import pub.types.ActionResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaiDuAiUtil {

	static HashMap<String, String> errorCodes = new HashMap<String, String>();
	static HashMap<String, String> labels = new HashMap<String, String>();
	static HashMap<String, String> imageStatus = new HashMap<String, String>();

	private static final String clientId = "erbvP0lFTEIlT7heN3yZxE8m";
	private static final String clientSecret = "6Cb6AEsKaLssclz1j2F6ZTA3zNkf87KS";
	private static final String ocrclientId = "1EK6V52UFsCetoaqSUGH32O1";
	private static final String ocrclientSecret = "HRTEs74gnVhb5ZbSeqYyEct1CUirkcDB";

	static {
		errorCodes.put("1", "服务器内部错误，请再次请求");
		errorCodes.put("2", "服务暂不可用，请再次请求");
		errorCodes.put("3", "调用的API不存在，请检查后重新尝试");
		errorCodes.put("4", "集群超限额");
		errorCodes.put("6", "无权限访问该用户数据");
		errorCodes.put("14", "IAM鉴权失败");
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
		errorCodes.put("216015", "模块关闭");
		errorCodes.put("216100", "非法参数");
		errorCodes.put("216101", "参数数量不够");
		errorCodes.put("216102", "业务不支持");
		errorCodes.put("216103", "参数太长");
		errorCodes.put("216110", "APP ID不存在");
		errorCodes.put("216111", "非法用户ID");
		errorCodes.put("216200", "空的图片");
		errorCodes.put("216201", "图片格式错误");
		errorCodes.put("216202", "图片大小错误");
		errorCodes.put("216300", "DB错误");
		errorCodes.put("216400", "后端系统错误");
		errorCodes.put("216401", "内部错误");
		errorCodes.put("216630", "识别错误");
		errorCodes.put("216631", "识别银行卡错误");
		errorCodes.put("216633", "识别身份证错误");
		errorCodes.put("216634", "检测错误，请再次请求");
		errorCodes.put("272000", "检测错误，请再次请求");
		errorCodes.put("282000", "业务逻辑层内部错误");
		errorCodes.put("282001", "业务逻辑层后端服务错误");
		errorCodes.put("282002", "请求参数编码错误");
		errorCodes.put("282100", "图片压缩转码错误");
		errorCodes.put("282202", "检测超时");
		errorCodes.put("282203", "gif单帧大小超限");
		errorCodes.put("282204", "gif总帧数超限");
		errorCodes.put("282205", "图片格式错误");
		errorCodes.put("282800", "配置Id不存在");
		errorCodes.put("282801", "image和imgUrl均为空");
		errorCodes.put("282802", "image和imgUrl只能有一个有值");
		errorCodes.put("282804", "图片下载失败");
		errorCodes.put("282805", "调用底层服务异常");
		errorCodes.put("282806", "图片宽高异常");
		errorCodes.put("282872", "图片链接过多");
		errorCodes.put("282873", "部分url异常");

		labels.put("1", "暴恐违禁");
		labels.put("2", "文本色情");
		labels.put("3", "政治敏感");
		labels.put("4", "恶意推广");
		labels.put("5", "低俗辱骂");

		imageStatus.put("normal", "识别正常");
		imageStatus.put("reversed_side", "身份证正反面颠倒");
		imageStatus.put("non_idcard", "上传的图片中不包含身份证");
		imageStatus.put("blurred", "身份证模糊");
		imageStatus.put("other_type_card", "其他类型证照");
		imageStatus.put("over_exposure", "身份证关键字段反光或过曝");
		imageStatus.put("unknown", "未知状态");
	}

	/**
	 * 获取API访问token 该token有一定的有效期，需要自行管理，当失效时需重新获取.
	 */
	public static String getAuth(String clientId, String clientSecret) {
		// 获取token地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// 1. grant_type为固定参数
				+ "grant_type=client_credentials"
				// 2. 官网获取的 API Key
				+ "&client_id=" + clientId
				// 3. 官网获取的 Secret Key
				+ "&client_secret=" + clientSecret;
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
			/** 返回结果示例 */
			System.err.println("result:" + result);
			JSONObject jsonObject = new JSONObject(result);
			String accessToken = jsonObject.getString("access_token");
			return accessToken;
		} catch (Exception e) {
			System.err.printf("获取token失败！");
			e.printStackTrace(System.err);
		}
		return null;
	}

	public static ActionResult validateStr(String content) {
		ActionResult actionResult = new ActionResult();
		String accessToken = getAuth(clientId, clientSecret);
		String url = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("access_token", accessToken);
		parameterMap.put("content", content);
		String result = HttpUtils.postx(url, parameterMap);
		System.out.println(result);
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
		if (jsonObject.containsKey("error_code")) {
			System.out.println(jsonObject.get("error_code"));
			System.out.println(errorCodes.get(jsonObject.getString("error_code")));
			actionResult.setSuccess(false);
			actionResult.setMsg(errorCodes.get(jsonObject.getString("error_code")));
			return actionResult;
		}
		if (jsonObject.containsKey("result")) {
			net.sf.json.JSONObject resultObject = jsonObject.getJSONObject("result");
			if (resultObject.containsKey("spam")) {
				String spam = resultObject.getString("spam");
				if ("0".equals(spam)) {
					System.out.println("非违禁");
					actionResult.setSuccess(true);
					actionResult.setMsg("非违禁");
					return actionResult;
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
								actionResult.setSuccess(false);
								actionResult.setMsg(labels.get(label));
								return actionResult;
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
								actionResult.setSuccess(false);
								actionResult.setMsg(labels.get(label));
								return actionResult;
							}
						}
					}
				} else {
					actionResult.setSuccess(false);
					actionResult.setMsg("数据解析异常");
					return actionResult;
				}
			}
		}
		return actionResult;
	}

	/**
	 * 图片审核
	 * @param imageUrl 图片路径
	 * @return
	 */
	public static ActionResult imgCensor(String imageUrl) {
		ActionResult actionResult = new ActionResult();
        String url = "https://aip.baidubce.com/api/v1/solution/direct/img_censor";
        try {
            //请求参数
            Map<String, Object> sceneConf = new HashMap<String, Object>();
            Map<String, Object> ocrConf = new HashMap<String, Object>();
            ocrConf.put("recognize_granularity", "big");
            ocrConf.put("language_type", "CHN_ENG");
            ocrConf.put("detect_direction", true);
            ocrConf.put("detect_language", true);
            sceneConf.put("ocr", ocrConf);

            Map<String, Object> input = new HashMap<String, Object>();
            List<Object> scenes = new ArrayList<Object>();
            scenes.add("ocr");
//            scenes.add("face");
            scenes.add("public");
            scenes.add("politician");
            scenes.add("antiporn");
            scenes.add("terror");
            scenes.add("webimage");
//            scenes.add("disgust");
//            scenes.add("watermark");

            input.put("imgUrl", imageUrl);//与image二者选一
            input.put("scenes", scenes);
            input.put("sceneConf", sceneConf);

            String params = GsonUtils.toJson(input);
            System.out.println(params);
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
    		String accessToken = getAuth(clientId, clientSecret);
            String result = HttpUtil.post(url, accessToken, "application/json", params);
            System.out.println(result);

    		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
    		if (jsonObject.containsKey("error_code")) {
    			System.out.println(jsonObject.get("error_code"));
    			System.out.println(errorCodes.get(jsonObject.getString("error_code")));
    			actionResult.setSuccess(false);
    			actionResult.setMsg(errorCodes.get(jsonObject.getString("error_code")));
    			return actionResult;
    		}
    		if (jsonObject.containsKey("result")) {
    			net.sf.json.JSONObject resultObject = jsonObject.getJSONObject("result");
    			if(resultObject.containsKey("politician")){
        			net.sf.json.JSONObject politicianObject = resultObject.getJSONObject("politician");
        			if("是".equals(politicianObject.getString("include_politician"))){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含政治人物");
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("antiporn")){
        			net.sf.json.JSONObject antipornObject = resultObject.getJSONObject("antiporn");
        			if(!"正常".equals(antipornObject.getString("conclusion"))){
            			actionResult.setSuccess(false);
            			actionResult.setMsg(antipornObject.getString("conclusion"));
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("terror")){
        			net.sf.json.JSONObject terrorObject = resultObject.getJSONObject("terror");
        			String resultStr = terrorObject.getString("result");
        			BigDecimal bd = new BigDecimal(resultStr);
        			if(bd.doubleValue()>0.6){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含暴恐信息");
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("disgust")){
        			net.sf.json.JSONObject disgustObject = resultObject.getJSONObject("disgust");
        			if(disgustObject.getDouble("result")>0.6){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含恶心信息");
            			return actionResult;
        			}
    			}

    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return actionResult;
    }

	public static ActionResult imgCensor(byte[] imgData) {
		ActionResult actionResult = new ActionResult();
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
        try {
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
    		String accessToken = getAuth(clientId, clientSecret);
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);

    		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
    		if (jsonObject.containsKey("error_code")) {
    			System.out.println(jsonObject.get("error_code"));
    			System.out.println(errorCodes.get(jsonObject.getString("error_code")));
    			actionResult.setSuccess(false);
    			actionResult.setMsg(errorCodes.get(jsonObject.getString("error_code")));
    			return actionResult;
    		}
    		if (jsonObject.containsKey("result")) {
    			net.sf.json.JSONObject resultObject = jsonObject.getJSONObject("result");
    			if(resultObject.containsKey("politician")){
        			net.sf.json.JSONObject politicianObject = resultObject.getJSONObject("politician");
        			if(!"是".equals(politicianObject.getString("include_politician"))){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含政治人物");
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("antiporn")){
        			net.sf.json.JSONObject antipornObject = resultObject.getJSONObject("antiporn");
        			if(!"正常".equals(antipornObject.getString("conclusion"))){
            			actionResult.setSuccess(false);
            			actionResult.setMsg(antipornObject.getString("conclusion"));
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("terror")){
        			net.sf.json.JSONObject terrorObject = resultObject.getJSONObject("terror");
        			String resultStr = terrorObject.getString("result");
        			BigDecimal bd = new BigDecimal(resultStr);
        			if(bd.doubleValue()>0.6){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含暴恐信息");
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("disgust")){
        			net.sf.json.JSONObject disgustObject = resultObject.getJSONObject("disgust");
        			if(disgustObject.getDouble("result")>0.6){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含恶心信息");
            			return actionResult;
        			}
    			}

    		}

            return actionResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return actionResult;
    }

	@SuppressWarnings("deprecation")
	public static ActionResult videoCensor(String videoUrl) {
		ActionResult actionResult = new ActionResult();
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/video_censor/user_defined";
        try {
            //请求参数
            Map<String, Object> sceneConf = new HashMap<String, Object>();
            Map<String, Object> ocrConf = new HashMap<String, Object>();
            ocrConf.put("recognize_granularity", "big");
            ocrConf.put("language_type", "CHN_ENG");
            ocrConf.put("detect_direction", true);
            ocrConf.put("detect_language", true);
            sceneConf.put("ocr", ocrConf);

            Map<String, Object> input = new HashMap<String, Object>();
            List<Object> scenes = new ArrayList<Object>();
            scenes.add("public");
            scenes.add("politician");
            scenes.add("antiporn");
            scenes.add("terror");
            scenes.add("disgust");
            scenes.add("watermark");

			input.put("appid",new Long(11800067));
			input.put("scenes", scenes);
			input.put("imgUrls", URLEncoder.encode(videoUrl));
			input.put("extTag","{\"vid\":1}");

            String params = GsonUtils.toJson(input);
            System.out.println(params);
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
    		String accessToken = getAuth(clientId, clientSecret);
    		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
			json.put("appid",new Long(11800067));
			json.put("scenes",scenes);
			json.put("imgUrls", URLEncoder.encode(videoUrl));
			json.put("extTag","1");

            String result = WebUtils.doPostJson(url + "?access_token=" + accessToken,json);   //HttpUtil.post(url, accessToken, "application/json;charset=utf-8", params);
            System.out.println(result);

    		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
    		if (jsonObject.containsKey("error_code")) {
    			System.out.println(jsonObject.get("error_code"));
    			System.out.println(errorCodes.get(jsonObject.getString("error_code")));
    			actionResult.setSuccess(false);
    			actionResult.setMsg(errorCodes.get(jsonObject.getString("error_code")));
    			return actionResult;
    		}
    		if (jsonObject.containsKey("result")) {
    			net.sf.json.JSONObject resultObject = jsonObject.getJSONObject("result");
    			if(resultObject.containsKey("politician")){
        			net.sf.json.JSONObject politicianObject = resultObject.getJSONObject("politician");
        			if(!"是".equals(politicianObject.getString("include_politician"))){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含政治人物");
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("antiporn")){
        			net.sf.json.JSONObject antipornObject = resultObject.getJSONObject("antiporn");
        			if(!"正常".equals(antipornObject.getString("conclusion"))){
            			actionResult.setSuccess(false);
            			actionResult.setMsg(antipornObject.getString("conclusion"));
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("terror")){
        			net.sf.json.JSONObject terrorObject = resultObject.getJSONObject("terror");
        			String resultStr = terrorObject.getString("result");
        			BigDecimal bd = new BigDecimal(resultStr);
        			if(bd.doubleValue()>0.6){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含暴恐信息");
            			return actionResult;
        			}
    			}
    			if(resultObject.containsKey("disgust")){
        			net.sf.json.JSONObject disgustObject = resultObject.getJSONObject("disgust");
        			if(disgustObject.getDouble("result")>0.6){
            			actionResult.setSuccess(false);
            			actionResult.setMsg("包含恶心信息");
            			return actionResult;
        			}
    			}

    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return actionResult;
    }

	public static ActionResult idcard(byte[] imgData, String idCardSide) {
		ActionResult actionResult = new ActionResult();
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
    		String accessToken = getAuth(ocrclientId, ocrclientSecret);
            String result = HttpUtil.post(url, accessToken, params);
            System.out.println(result);

    		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);

    		if (jsonObject.containsKey("error_code")) {
    			System.out.println(jsonObject.get("error_code"));
    			System.out.println(errorCodes.get(jsonObject.getString("error_code")));
    			actionResult.setSuccess(false);
    			actionResult.setMsg(errorCodes.get(jsonObject.getString("error_code")));
    			return actionResult;
    		}
    		if (jsonObject.containsKey("image_status")) {
    			System.out.println(jsonObject.get("image_status"));
    			System.out.println(imageStatus.get(jsonObject.getString("image_status")));
    			actionResult.setSuccess(false);
    			actionResult.setMsg(imageStatus.get(jsonObject.getString("image_status")));
    			return actionResult;
    		}
    		if (jsonObject.containsKey("words_result")) {
    			net.sf.json.JSONObject wordsResultObject = jsonObject.getJSONObject("words_result");
    			WordsResult wordsResult = new WordsResult();
    			if(wordsResultObject.containsKey("住址")){
        			net.sf.json.JSONObject addressObject = wordsResultObject.getJSONObject("住址");
        			wordsResult.setAddress(addressObject.getString("words"));
    			}
    			if(wordsResultObject.containsKey("公民身份号码")){
        			net.sf.json.JSONObject idCardObject = wordsResultObject.getJSONObject("公民身份号码");
        			wordsResult.setIdCard(idCardObject.getString("words"));
    			}
    			if(wordsResultObject.containsKey("出生")){
        			net.sf.json.JSONObject dayObject = wordsResultObject.getJSONObject("出生");
        			wordsResult.setDay(dayObject.getString("words"));
    			}
    			if(wordsResultObject.containsKey("姓名")){
        			net.sf.json.JSONObject nameObject = wordsResultObject.getJSONObject("姓名");
        			wordsResult.setName(nameObject.getString("words"));
    			}
    			if(wordsResultObject.containsKey("性别")){
        			net.sf.json.JSONObject sexObject = wordsResultObject.getJSONObject("性别");
        			wordsResult.setSex(sexObject.getString("words"));
    			}
    			if(wordsResultObject.containsKey("民族")){
        			net.sf.json.JSONObject nationObject = wordsResultObject.getJSONObject("民族");
        			wordsResult.setNation(nationObject.getString("words"));
    			}
    			actionResult.setSuccess(true);
    			actionResult.setData(wordsResult);
    		}
        } catch (Exception e) {
            e.printStackTrace();
			actionResult.setSuccess(false);
        }
		return actionResult;
    }



	public static void main(String[] args) throws Exception{
		//userDefined();
//		ActionResult imageValidate= imgCensor("https://otc-ggyx.oss-cn-shenzhen.aliyuncs.com/2018-10-15/a000000001539584714079.mp4");
//		System.out.println(imageValidate.getCode() +""+imageValidate.getMsg());
//
//		ActionResult videoValidate= BaiDuAiUtil.videoCensor("https://otc-ggyx.oss-cn-shenzhen.aliyuncs.com/2018-10-12/a000000001539341854348.jpg");
//				if(!videoValidate.isSuccess()){
//					System.out.println(videoValidate.getCode() +""+videoValidate.getMsg());
//				}
        System.out.println("xxxx"+new BigDecimal( new BigDecimal(0.1).intValue()));
//		byte[] imgData = FileUtil.readFileByBytes("D:\\PIC\\20180504100528.jpg");
//		idcard(imgData,"front");
	}

}
