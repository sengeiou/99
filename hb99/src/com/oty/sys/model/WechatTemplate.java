package com.oty.sys.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class WechatTemplate {
    /**
     * 接收者（用户）的 openid
     */
	@Getter @Setter
    private String touser;
    /**
     * 所需下发的模板消息的id
     */
	@Getter @Setter
    private String template_id;
    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     */
	@Getter @Setter
    private String page;

    /**
     * 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     */
	@Getter @Setter
    private String form_id;

	@Getter @Setter
    private Map<String, TemplateData> data;
 
    @Override
    public String toString() {
        StringBuffer info = new StringBuffer("ToUser=" + touser).append(";");
        if (data != null) {
            for (String key : data.keySet()) {
                info.append(key).append("=").append(data.get(key).getValue()).append(";");
            }
        }
        return info.toString();
    }

}