package pub.spring.mvc.view; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("unchecked")
public class JsonView extends MappingJackson2JsonView {
 
	public static final String directResult = "direct_result";

	private boolean singleValueAsDirectResult = false;

	public boolean isSingleValueAsDirectResult() {
		return singleValueAsDirectResult;
	}

	public void setSingleValueAsDirectResult(boolean singleValueAsDirectResult) {
		this.singleValueAsDirectResult = singleValueAsDirectResult;
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) { 
		if (!response.containsHeader("Cache-Control")) {
			response.setHeader("Cache-Control", "no-store");
		}
		super.prepareResponse(request, response);
	}

	@Override
	protected Object filterModel(Map<String, Object> model) {
		if (model.containsKey(directResult)) {
			return model.get(directResult);
		} 
		model = (Map<String, Object>) super.filterModel(model);

		if (model.containsKey("_fullModel")) {
			return model;
		}
		Object result = null;
		Iterator<Object> iter = model.values().iterator();
		while (iter.hasNext()) {
			result = iter.next();
		}
		return result;
	}
}
