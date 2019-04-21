package cmcciot.onenet.nbapi.sdk.api.online;

import cmcciot.onenet.nbapi.sdk.entity.CommonEntity;
import cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;
import okhttp3.Callback;
import org.json.JSONObject;

public class ResourcesOpe extends BasicOpe {
	
	public ResourcesOpe(String apiKey) {
		super(apiKey);
	}

	@Override
	public JSONObject operation(CommonEntity commonEntity, JSONObject body) {
		return HttpSendCenter.get(apiKey, commonEntity.toUrl());
	}

	@Override
	public void operation(CommonEntity commonEntity, JSONObject body, Callback callback) {
		HttpSendCenter.getAsync(apiKey, commonEntity.toString(), callback);
	}

}
