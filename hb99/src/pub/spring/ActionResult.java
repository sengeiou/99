package pub.spring;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import pub.web.RestoreRedirectedRequestDataFilter; 

public class ActionResult {

	public static View formView() { 
		String url = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getServletPath().toString();
		url = url.substring(0, url.lastIndexOf("/")); 
		return new InternalResourceView(url+"/show.html");
	}
	public static View formView(String addr) { 
		String url = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getServletPath().toString();
		url = url.substring(0, url.lastIndexOf("/")); 
		return new InternalResourceView(url+addr);
	}
	public static View ok() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String returnUrl = request.getParameter("returnUrl");
		if (returnUrl == null) {
			returnUrl = request.getHeader("referer");
		}
		return new RedirectView(returnUrl);
	}

	public static View ok(String messageOrUrl) {
		boolean isUrl = messageOrUrl.contains(".html");
		String message = isUrl ? "操作成功" : messageOrUrl;
		String url = isUrl ? messageOrUrl : null;
		return ok(message, url);
	}

	public static View ok(String message, String nextUrl) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String returnUrl = request.getParameter("returnUrl");
		boolean directReturn = false;
		if (returnUrl != null) {
			if (nextUrl == null) {
				directReturn = true;
			}
			else {
				String urlWithHome = request.getContextPath() + correctNoPathUrl(nextUrl);
				directReturn = returnUrl.startsWith(urlWithHome);
			}
		}
		if (directReturn) {
			return new RedirectView(returnUrl);
		}
		return result(message, false, nextUrl);
	}

	public static View fail(String message) {
		return result(message, true, null);
	}

	public static View fail(String message, String nextUrl) {
		return result(message, true, nextUrl);
	}

	public static View error(String message) {
		return error(message, null);
	}

	public static View error(Exception e) {
		return error("发生异常", e);
	}

	public static View error(String message, Exception e) {
		if(e!=null){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			sw.flush();
			message = message + "\n详细信息:\n" + sw.getBuffer();
		}
		return result(message, true, null);
	}

	public static View result(String message, boolean isError, String nextUrl) {
		nextUrl = correctNoPathUrl(nextUrl);
		RestoreRedirectedRequestDataFilter.setRequestData("message", message);
		RestoreRedirectedRequestDataFilter.setRequestData("isError", isError);
		RestoreRedirectedRequestDataFilter.setRequestData("nextUrl", nextUrl);
		return new RedirectView("/common/action_result.html", true);
	}

	private static String correctNoPathUrl(String nextUrl) {
		if (nextUrl == null) {
			return null;
		}
		if (nextUrl.indexOf('/') == -1) {
			String path = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getServletPath();
			int pos = path.lastIndexOf('/');
			nextUrl = path.substring(0, pos + 1) + nextUrl;
		}
		return nextUrl;
	}
}
