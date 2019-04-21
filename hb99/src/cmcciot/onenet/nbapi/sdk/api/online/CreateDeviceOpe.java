package cmcciot.onenet.nbapi.sdk.api.online;

import cmcciot.onenet.nbapi.sdk.entity.CommonEntity; 
import cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;
import okhttp3.Callback;
import org.json.JSONObject;

public class CreateDeviceOpe extends BasicOpe {
	
	public CreateDeviceOpe(String apiKey) {
		super(apiKey);
	}

	@Override
	public JSONObject operation(CommonEntity commonEntity, JSONObject body) {
		return HttpSendCenter.post(this.apiKey, commonEntity.toUrl(), body);
	}

	@Override
	public void operation(CommonEntity commonEntity, JSONObject body, Callback callback) {
		HttpSendCenter.postAsync(this.apiKey, commonEntity.toUrl(), body, callback);
	}

}
