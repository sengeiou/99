package com.oty.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据 
 */
public class R extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 200);
	}

	public static R error() {
		return error(500, "未知异常, 请联系管理员！");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg, Object map) {
		R r = new R();
		r.put("msg", msg);
		r.put("data", map);
		return r;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static R ok(String msg, Map data, Integer totalPages, Integer total, Integer pageNumber, Integer pageSize) {
		R r = new R(); 
		data.put("totalPages", totalPages); // 总页数
		data.put("total", total); // 总条数
		data.put("pageNumber", pageNumber); // 当前页数
		data.put("pageSize", pageSize); // 页条数 
		r.put("msg", msg);
		r.put("data", data);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		R r = new R();
		return r;
	}

	public R put(String key, Object value) { 
		super.put(key, value);
		return this;
	}
	
}
