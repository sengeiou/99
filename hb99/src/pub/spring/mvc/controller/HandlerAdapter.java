package pub.spring.mvc.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import pub.spring.mvc.bind.annotation.PageData;
import pub.web.PageDataUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" }) 
public class HandlerAdapter extends AnnotationMethodHandlerAdapter {

	@Override
	protected ModelAndView invokeHandlerMethod(HttpServletRequest request,
											   HttpServletResponse response,
											   Object handler) throws Exception { 
		ModelAndView mav = super.invokeHandlerMethod(request, response, handler);
		if (mav == null) {
			return null;
		}
		Map<String, Object> model = mav.getModel(); 
		PageData pageDataAnnotation = AnnotationUtils.findAnnotation(handler.getClass(), PageData.class);
		if (pageDataAnnotation != null) {
			storePageData(request, model, pageDataAnnotation);
		} 
		if (handler.getClass().getAnnotation(NoCache.class) != null) {
			response.setHeader("Cache-Control", "no-store");
		}

		return mav;
	}

	private void storePageData(HttpServletRequest request, Map<String, Object> model, PageData annotation) {
		String[] names = annotation.value();
		Class[] types = annotation.types();

		Map pageDataMap = new HashMap();
		for (String name : names) {
			Object value = model.get(name);
			if (value == null) {
				value = WebArgumentResolver.UNRESOLVED;
			}
			pageDataMap.put(name, value);
		}
		for (Class type : types) {
			Object value = WebArgumentResolver.UNRESOLVED;
			for (Object tValue : model.values()) {
				if (type.isInstance(tValue)) {
					value = tValue;
					break;
				}
			}
			pageDataMap.put(type, value);
		}
		if (pageDataMap.isEmpty()) {
			// nothing to do
		}
		else if (pageDataMap.size() == 1) {
			PageDataUtils.savePageData(request, pageDataMap.values().iterator().next());
		}
		else {
			PageDataUtils.savePageData(request, pageDataMap);
		}
	}
}
