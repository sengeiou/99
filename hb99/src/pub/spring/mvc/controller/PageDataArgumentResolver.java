package pub.spring.mvc.controller;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import pub.web.PageDataUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
 
public class PageDataArgumentResolver implements WebArgumentResolver {
 
	@SuppressWarnings({ "rawtypes" })
	public Object resolveArgument(MethodParameter methodParameter,
								  NativeWebRequest webRequest) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request.getParameter("pageKey") == null) {
			return UNRESOLVED;
		}
		Class<?> clazz = methodParameter.getParameterType();
		String className = clazz.getName();
		if (className.startsWith("java") || className.startsWith("org.")) {
			return UNRESOLVED;
		}
		Object pageData = PageDataUtils.loadPageData(request);
		if (clazz.isInstance(pageData)) {
			return pageData;
		}
		else if (pageData instanceof Map) {
			Map pageDataMap = (Map) pageData;
			Object value = pageDataMap.get(clazz);
			if (value != null) {
				return value;
			}
			value = pageDataMap.get(methodParameter.getParameterName());
			if (value != null) {
				return value;
			}
		}
		return UNRESOLVED;
	}
}
