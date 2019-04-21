package pub.functions;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

public class RspFuncs {

	private static JsonConfig jsonConfig;
	static {
		jsonConfig = new JsonConfig();
		JsDateJsonBeanProcessor beanProcessor = new JsDateJsonBeanProcessor();
		jsonConfig.registerJsonBeanProcessor(java.util.Date.class,
				beanProcessor);
	}

	public static void writeJson(HttpServletResponse response, Object obj) {
		String json = JSONSerializer.toJSON(obj, jsonConfig).toString();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setContentType("text/plain;charset=UTF-8");// 这个解决IE提示下载的烦人东西
		setNoCache(response);

		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void addCookie(HttpServletResponse response, String name,String value, String path) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setMaxAge(3600 * 24 * 30);
		response.addCookie(cookie);
	}

	public static void deleteCookie(HttpServletResponse response, String name, String path) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static void setNoCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-store");

	}

	public static void setNoCache() {
		setNoCache(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse());
	}
}
