package com.oty.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oty.sys.model.WechatTemplate;

public class TemplayeMsgUtil {
    private static Log logger = LogFactory.getLog(TemplayeMsgUtil.class);

    // 获取access_token的接口地址（GET）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token";

    public static JSONObject getAccessToken(String appid, String appsecret) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", appid);
        paramMap.put("secret", appsecret);
        String resultStr = WebUtils.get(access_token_url, paramMap);
        JSONObject jsonObject = JSONObject.fromObject(resultStr);
        logger.debug("--------------->getAccessToken [jsonObject]=" + jsonObject.toString());
        return jsonObject;
    }

    private static final String SEND_TEMPLAYE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";

    /**
     * 发送模板消息
     * 
     * @param accessToken
     * @param wechatTemplate
     */
    public static void sendTemplateMessage(String accessToken, WechatTemplate wechatTemplate) {
        JSONObject jsonObjectParam = JSONObject.fromObject(wechatTemplate);
        String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
        String resultStr = WebUtils.doPostJson(requestUrl, jsonObjectParam);
        JSONObject jsonObject = JSONObject.fromObject(resultStr);
        // logger.info("jsonObject="+jsonObject);
        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            if (0 == errorCode) {
                logger.info("模板消息发送成功<" + wechatTemplate + ">");
            } else {
                String errorMsg = jsonObject.getString("errmsg");
                logger.info("模板消息发送失败,错误是 " + errorCode + ",错误信息是" + errorMsg + "<" + wechatTemplate + ">");
            }
        }
    }
}
