package cmcciot.onenet.nbapi.sdk.api.online;

import cmcciot.onenet.nbapi.sdk.entity.CommonEntity;
import cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;
import okhttp3.Callback;
import org.json.JSONObject;

public class ObserveOpe extends BasicOpe {
	
	public ObserveOpe(String apiKey) {
		super(apiKey);
	}

	@Override
	public JSONObject operation(CommonEntity commonEntity, JSONObject body) {
		return HttpSendCenter.postNotBody(apiKey, commonEntity.toUrl());
	}

	@Override
	public void operation(CommonEntity commonEntity, JSONObject body, Callback callback) {
		HttpSendCenter.postNotBodyAsync(apiKey, commonEntity.toUrl(), callback);
	}
	
}
