package pub.web;

import pub.functions.MathFuncs;
import pub.functions.ReqFuncs;
import pub.types.ObjectCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PageDataUtils {

	private static Integer generatePageKey() {
		final HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Integer pageKey = null;
		synchronized (session.getAttribute("SESSION_LOCK")) {
			pageKey = (Integer) session.getAttribute("NEXT_PAGE_KEY");
			if (pageKey == null) {
				pageKey = MathFuncs.randInt();
			}
			session.setAttribute("NEXT_PAGE_KEY", pageKey + 1);
		}
		return pageKey;
	}

	private static void setPageData(Integer key, Object pageData) {
		final HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		synchronized (session.getAttribute("SESSION_LOCK")) {
			Map<Integer, PageDataInfo> dataInfoMap = (Map<Integer, PageDataInfo>)
				session.getAttribute("PAGE_DATA_INFO_MAP");
			PageDataInfo pageDataInfo = new PageDataInfo();
			pageDataInfo.setData(pageData);
			pageDataInfo.setLastVisitTime(System.currentTimeMillis());
			dataInfoMap.put(key, pageDataInfo);
			System.out.println("info: pageData set with key " + key);
			// passive cleanup
			cleanObsoletePageDatas();
		}
	}

	private static Object getPageData(Integer key) {
		final HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Map<Integer, PageDataInfo> dataInfoMap = (Map<Integer, PageDataInfo>)
			session.getAttribute("PAGE_DATA_INFO_MAP");
		Object pageData = null;
		synchronized (session.getAttribute("SESSION_LOCK")) {
			PageDataInfo pageDataInfo = dataInfoMap.get(key);
			if (pageDataInfo == null) {
				System.out.println("Warning: getPageData with invalid key? " + key);
				return null;
			}
			pageDataInfo.setLastVisitTime(System.currentTimeMillis());
			pageData = pageDataInfo.getData();
		}
		return pageData;
	}

	// passive cleanup
	private static void cleanObsoletePageDatas() {
		final HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Long lastCleanTime = (Long) session.getAttribute("LAST_PAGE_DATA_CLEAN_TIME");
		long now = System.currentTimeMillis();

		final int MIN_CLEAN_INTERVAL = 30;  
		final int MAX_IDLE_TIME = 200; 

		if ((now - lastCleanTime) / 1000 < MIN_CLEAN_INTERVAL) {
			return;
		}
		else {
			session.setAttribute("LAST_PAGE_DATA_CLEAN_TIME", now);
		}

		Map<Integer, PageDataInfo> dataInfoMap = (Map<Integer, PageDataInfo>)
			session.getAttribute("PAGE_DATA_INFO_MAP");
		List<Integer> pageKeys = new ArrayList<Integer>(dataInfoMap.keySet());
		for (Integer key : pageKeys) {
			PageDataInfo info = dataInfoMap.get(key);
			if ((now - info.getLastVisitTime()) / 1000 > MAX_IDLE_TIME) {
				dataInfoMap.remove(key);
			}
		}
	}

	public static Integer savePageData(HttpServletRequest request, Object data) {
		Integer pageKey = generatePageKey();
		setPageData(pageKey, data);

		request.setAttribute("pageKey", pageKey);

		return pageKey;
	}

	public static Object loadPageData(HttpServletRequest request) {
		Integer pageKey = ReqFuncs.getIntegerParam(request, "pageKey");
		request.setAttribute("pageKey", pageKey);
		return getPageData(pageKey);
	}

	public static void keepPageDataAlive(HttpServletRequest request) {
		Integer pageKey = ReqFuncs.getIntegerParam(request, "pageKey");
		final HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Map<Integer, PageDataInfo> dataInfoMap = (Map<Integer, PageDataInfo>)
			session.getAttribute("PAGE_DATA_INFO_MAP");
		synchronized (session.getAttribute("SESSION_LOCK")) {
			PageDataInfo pageDataInfo = dataInfoMap.get(pageKey);
			if (pageDataInfo == null) {
				System.out.println("Warning: outdated pageKey? " + pageKey);
				return;
			}
			pageDataInfo.setLastVisitTime(System.currentTimeMillis()); 
		}
	}

	public static void abandonPageData(HttpServletRequest request) {
		Integer pageKey = ReqFuncs.getIntegerParam(request, "pageKey");
		final HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		Map<Integer, PageDataInfo> dataInfoMap = (Map<Integer, PageDataInfo>)
			session.getAttribute("PAGE_DATA_INFO_MAP");
		synchronized (session.getAttribute("SESSION_LOCK")) {
			dataInfoMap.remove(pageKey);
		}
	}

	//
	public static<T> T loadOrCreate(HttpServletRequest request, ObjectCreator<T> creator) {
		T data;
		if (request.getParameter("pageKey") == null) {
			data = creator.create();
			savePageData(request, data);
		}
		else {
			data = (T) loadPageData(request);
		}
		return data;
	}
}
