package pub.functions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import pub.beans.Binder;
import pub.web.RestoreRedirectedRequestDataFilter; 

@SuppressWarnings({"unchecked" ,"rawtypes"})
public class ReqFuncs {

	public static void setRefererAsNextUrl() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String referer = request.getHeader("referer");
		request.setAttribute("nextUrl", referer);
		RestoreRedirectedRequestDataFilter.setRequestData("nextUrl", referer);
	}

	public static void setNextUrl(String nextUrl) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("nextUrl", nextUrl);
		RestoreRedirectedRequestDataFilter.setRequestData("nextUrl", nextUrl);
	}
 
	public static String getStringParam(HttpServletRequest request, String paramName) {
		return getStringParam(request, paramName, null);
	}

	public static String[] getStringParams(HttpServletRequest request, String paramName) {
		String[] sValues = request.getParameterValues(paramName);

		return sValues;
	}

	public static String getStringParam(HttpServletRequest request, String paramName, String defaultValue) {
		String result = request.getParameter(paramName);
		if (result == null || result.length() == 0) {
			result = defaultValue;
		}
		return result;
	}
 
	public static Integer getIntegerParam(HttpServletRequest request, String paramName) {
		return getIntegerParam(request, paramName, null);
	}

	public static Integer getIntegerParam(HttpServletRequest request, String paramName, Integer defaultValue) {
		String sParam = request.getParameter(paramName);
		Integer result;
		try {
			result = Integer.parseInt(sParam);
		}
		catch (NumberFormatException nfe) { // null or bad format
			result = defaultValue;
		}
		return result;
	}
	public static boolean hasParam(HttpServletRequest request, String paramName) {
		return request.getParameter(paramName) != null;
	}
	public static Double getDoubleParam(HttpServletRequest request, String paramName) {
		return getDoubleParam(request, paramName, null);
	}
	public static Double getDoubleParam(HttpServletRequest request, String paramName, Double defaultValue) {
		String sParam = request.getParameter(paramName);
		Double result;
		try {
			result = Double.parseDouble(sParam);
		}
		catch (NumberFormatException nfe) { // null or bad format
			result = defaultValue;
		}
		return result;
	}

	public static Integer[] getIntegerParams(HttpServletRequest request, String paramName) {
		String[] sValues = request.getParameterValues(paramName);
		if (sValues == null)
			return new Integer[]{};
		Integer[] values = new Integer[sValues.length];
		for (int n = 0; n < sValues.length; n++) {
			values[n] = Integer.parseInt(sValues[n]);
		}
		return values;
	}
 
	public static String getUrl(HttpServletRequest request) {
		return getUrl(request, true);
	}
	public static String getUrl(HttpServletRequest request, boolean considerInclude) {
		StringBuffer url = new StringBuffer();
		String qs;

		String includedUri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (includedUri != null && considerInclude) {
			url.append(includedUri);
			qs = (String) request.getAttribute("javax.servlet.include.query_string");
		}
		else {
			url.append(request.getRequestURL());
			qs = request.getQueryString();
		}
		if (qs != null && qs.length() > 0) {
			url.append('?');
			url.append(qs);
		}
		if (url.charAt(0) == '/') {
			return url.toString();
		}
		return url.substring(url.indexOf("/", 8));
	}

	public static boolean isIncluded(HttpServletRequest request) {
		return request.getAttribute("javax.servlet.include.request_uri") != null;
	} 

	public static void populate(Object bean, HttpServletRequest request) {
		Map map = request.getParameterMap();
		if (map.size() == 0) {
			return;
		} 
		Iterator iter = map.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    @SuppressWarnings("unused")
			Object key = entry.getKey(); 
		    Object val = entry.getValue(); 
		    if(val instanceof String[]){
		    	String[] values = (String[])val;
		    	if(values.length > 1){
		    		String joinValue = "";
		    		for(int i = 0; i < values.length; i++){
		    			String value = values[i];
		    			if(StrFuncs.isEmpty(value))
		    				continue;
		    			joinValue = StrFuncs.isEmpty(joinValue) ?
		    				value : joinValue + "," + value;
		    		}
		    		entry.setValue(new String[]{joinValue});
		    	}
		    }
		}  
		try { 
			BeanFuncs.populate(bean, map);  
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static Map checkFieldMarkers(Map<String, Object> map) {
		List<String> markerFields = new ArrayList<String>();
		for (String key : map.keySet()) {
			if (key.startsWith("_")) {
				String realKey = key.substring(1);
				if (!map.containsKey(realKey)) {
					markerFields.add(key);
				}
			}
		}
		if (markerFields.size() == 0) {
			return map;
		}
		HashMap<String, Object> newMap = new HashMap(map);
		for (String key : markerFields) {
			String realKey = key.substring(1);
			Object value = map.get(key); 
			newMap.put(realKey, value);
		}
		return newMap;
	} 
	
	public static void setRequestData(HttpServletRequest request, Object data) {
		setRequestData(request, data.getClass().getName(), data);
	}

	public static void setRequestData(HttpServletRequest request, String key, Object data) {
		request.setAttribute(key, data);
		setRequestDataForRedirect(request, key, data);
	}

	private static void setRequestDataForRedirect(HttpServletRequest request, String key, Object data) {
		HttpSession session = request.getSession();
		Map map = (Map) session.getAttribute("REQUEST_DATA_MAP_FOR_REDIRECT");
		map.put(key, data);
	}

	public static <T> T getRequestData(Class<T> clazz) {
		return (T) ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getAttribute(clazz.getName());
	}

	public static <T> T getRequestData(HttpServletRequest request, Class<T> clazz) {
		return (T) request.getAttribute(clazz.getName());
	}

	//
	public static void setSessionData(HttpServletRequest request, Object data) {
		request.getSession().setAttribute(data.getClass().getName(), data); 
	}

	public static void setSessionData(HttpServletRequest request, String key, Object data) {
		request.getSession().setAttribute(key, data);
	}

	public static <T> T getSessionData(HttpServletRequest request, Class<T> clazz) {
		return (T) request.getSession().getAttribute(clazz.getName());
	}

	public static <T> T getSessionData(HttpServletRequest request, String key, Class<T> clazz) {
		return (T) request.getSession().getAttribute(key);
	}

	public static <T> T getSessionData(HttpServletRequest request, Class<T> clazz, String key) {
		return (T) request.getSession().getAttribute(key);
	}

	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("referer");
	}

	//
	public static Map<String, String> getCookieMap(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				result.put(cookie.getName(), cookie.getValue());
			}
		}
		return result;
	} 

	//检查url
	public static String checkUrl(String pageUrl) throws UnsupportedEncodingException {
		pageUrl = java.net.URLDecoder.decode(pageUrl, "utf-8");
		pageUrl.replaceAll("&amp;", "&");
		pageUrl.replaceAll("&", "&amp;");

		return pageUrl;
	}

	public static String formatUrl(HttpServletRequest request, String strUrl) throws
																			  UnsupportedEncodingException {
		String webapp = request.getRequestURI().substring(0, request.getRequestURI().indexOf(request.getServletPath()));
		strUrl = strUrl.replaceFirst(webapp, "");

		return strUrl;
	}

	public static Object getAttribute(WebRequest request, String name) {
		return request.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
	}

	public static Object getAttribute(String name) {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getAttribute(name);
	}

	public static Integer getIntegerParam(String name) {
		return getIntegerParam(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(), name);
	}

	public static void setAttribute(String name, String value) {
		((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().setAttribute(name, value);
	}
	
	public static String getIpAddr(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	}

	public static Date getDateParam(HttpServletRequest request, String paramName) {
		return (Date) Binder.beanUtilBean.getConvertUtils().convert(
			request.getParameter(paramName), Date.class);
	}  
	
}
