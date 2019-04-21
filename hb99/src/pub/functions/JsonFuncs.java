package pub.functions;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;
import pub.types.IdText;

@SuppressWarnings({"unchecked" ,"rawtypes"})
public class JsonFuncs {

	private final static JsonConfig jsonConfig = new JsonConfig();
	private final static JsonConfig jsonConfig2 = new JsonConfig();

	private final static DefaultValueProcessor nullValueProcessor = new DefaultValueProcessor(){
		 
		public Object getDefaultValue(Class type) {  
            return null;  
        } 
	};
	
	static{
		//@1：注册时间类型为自定义类型JsonDateValueProcessor
		//匿名类
		JsonValueProcessor dateValueProcessor = new JsonValueProcessor(){
			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				String[] result = {};
				if (value != null && value instanceof Date[]) {
					Date[] dates = (Date[]) value;
					result = new String[dates.length];
					for (int i = 0; i < dates.length; i++) {
						result[i] = DateFuncs.toString(dates[i]);
					}
				}
				return  result;
			}

			public Object processObjectValue(String key, Object value,
					JsonConfig jsonConfig) {
				if (value != null && value instanceof Date){
					return DateFuncs.toString((Date) value);
				}
				else if (value != null && value instanceof Clob){
					return StrFuncs.valueOf((Clob)value);
				}
				else		
					return null;
			}
		};
		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, dateValueProcessor);
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, dateValueProcessor);
		jsonConfig.registerDefaultValueProcessor(Integer.class, nullValueProcessor); 
		jsonConfig.registerDefaultValueProcessor(Double.class, nullValueProcessor); 
		
		jsonConfig2.registerJsonValueProcessor(java.util.Date.class, dateValueProcessor);
		jsonConfig2.registerJsonValueProcessor(java.sql.Timestamp.class, dateValueProcessor);
		jsonConfig2.registerJsonValueProcessor(java.sql.Clob.class, dateValueProcessor);
		jsonConfig.registerDefaultValueProcessor(Integer.class, nullValueProcessor); 
		jsonConfig.registerDefaultValueProcessor(Double.class, nullValueProcessor); 
		jsonConfig.registerDefaultValueProcessor(String.class, nullValueProcessor); 
	}
	/** 
	 * @ 方法说明： 将json字符串转换成map
	 * @param jsonDataStr
	 * @1：讲字符串转换成JsonObject
	 * @2：取出JsonObject里的name和value封装成map 
	 */ 
	public static Map<String, String> toMap(String jsonDataStr){
		return toMap(JSONObject.fromObject(jsonDataStr));
	}
	
	public static Map<String, String> toMap(JSONObject jsonObject){
		Map<String, String> map = new HashMap<String, String>();
		if(jsonObject != null){
			Set<String> set = jsonObject.keySet();
			for (String key : set) {
				map.put(key, jsonObject.getString(key).trim());
			}
		}
		return map;
	}
	
	/*
	 * {key:value,key,value}形式转为List<IdText>
	 * by dgs
	 * */
	public static List<IdText> toListIdText(String jsonDataStr){
		List<IdText> idTexts = new ArrayList<IdText>();
		if(StrFuncs.isEmpty(jsonDataStr)){
			return idTexts;
		}
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonDataStr);
			if(jsonObject != null){
				Set<String> set = jsonObject.keySet();
				for (String key : set) {
					idTexts.add(new IdText(key, jsonObject.getString(key).trim()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idTexts;
	}
	
	
	public static List<Map<String, String>> toList(String jsonDataStr){
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		if(!StrFuncs.isEmpty(jsonDataStr)){
			JSONArray fromObject = JSONArray.fromObject(jsonDataStr);
			for (Object object : fromObject) {
				Map<String, String> map=toMap(object.toString());
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @Author：pj
	 * @ 方法说明：将对象转换成JSONObject,此方法已经对时间类型进行了处理
	 * @param obj
	 * @return
	 * @ 算法如下：
	 * 
	 * @1：注册时间类型为自定义类型JsonDateValueProcessor
	 * @2: 通过JSONObject.fromObject转换object，并且注入jsonConfig
	 * @ 版本信息:2013-5-24
	 */
	public static JSONObject toJsonObject(Object obj){
		return toJsonObject(obj, false);
	}
	public static JSONObject toJsonObject(Object obj, boolean ignoreNull){
		if(obj==null)
			return null;
		
		return JSONObject.fromObject(obj,
			ignoreNull ? jsonConfig2 : jsonConfig);
	}
	/**
	 * 
	 * @Author：pj
	 * @ 方法说明：将对象转换成JSONObject
	 * @param obj
	 * @return
	 * @ 算法如下：
	 * 
	 * @1：注册时间类型为自定义类型JsonDateValueProcessor
	 * @2: 通过JSONObject.fromObject转换object，并且注入jsonConfig
	 * @ 版本信息:2013-5-24
	 */
	public static JSONArray toJsonArray(Object obj){
		return toJsonArray(obj, false);
	}
	
	public static JSONArray toJsonArray(Object obj, boolean ignoreNull){
		if(obj==null)
			return null;
		return (JSONArray)JSONSerializer.toJSON(obj, ignoreNull ? jsonConfig2 : jsonConfig);
	}
	
	/**
	 * @Author：pj
	 * @ 方法说明：往condition中添加属性
	 * @param key 指定的键
	 * @param value 指定的值
	 * @return
	 */
	public static String addJsonObjectAttribute(String condition, String key, String value){
		JSONObject jsonObject = StrFuncs.isEmpty(condition) ? new JSONObject() : toJsonObject(condition);
		return jsonObject.element(key, value).toString();
	}
	
	public static String removeJsonObjectAttribute(String condition, String key){
		JSONObject jsonObject = StrFuncs.isEmpty(condition) ? new JSONObject() : toJsonObject(condition);
		
		if(jsonObject.containsKey(key)){
			jsonObject.remove(key);
		}
		return jsonObject.toString();
	}
	
	/**
	 * @Author：pj
	 * @ 方法说明：为jsonArr的每个JsonObject添加指定的键值对（key，value）
	 * @param key 指定的键
	 * @param value 指定的值
	 * @return
	 * @ 算法如下：
	 * 
	 * @1：注册时间类型为自定义类型JsonDateValueProcessor
	 * @2：注册Interger类型返回自定义默认值
	 * @3：注册Long类型返回自定义默认值
	 * @4：通过循环遍历往每个JSONObject添加指定的（key,value）
	 * @ 版本信息:2013-5-27
	 */
	public static JSONArray element(JSONArray jsonArr,String key,Object value){
		
		//@4: 通过循环遍历往每个JSONObject添加指定的（key,value）
		for (int i = 0; i < jsonArr.size(); i++) {
			jsonArr.getJSONObject(i).element(key, value,jsonConfig);
		}
		return jsonArr;
	}
	
	/**
	 * 
	 * @Author：pj
	 * @ 方法说明：将两个jsonArray合并成一个
	 * @param newArr
	 * @param oldArr
	 * @ 算法如下：
	 * @1：
	 * @ 版本信息:2013-5-27
	 */
	public static void merger(JSONArray newArr,JSONArray oldArr){
		for (int i = 0; i < oldArr.size(); i++) {
			newArr.add(oldArr.getJSONObject(i));
		}
	}
	
	public static JSON toJson(Object obj){
		return toJson(obj, false);
	}
	
	public static JSON toJson(Object obj, boolean ignoreNull){
		JSON result = JSONSerializer.toJSON(obj, jsonConfig);
		//2。
		if(ignoreNull){
			trimJson(result);
		}
		//3。
		return result;
	}
	
	//myq add 2015-3-25重构，不容易啊
	public static void trimJson(JSON json){
		try{
			if(json instanceof JSONObject){
				compress((JSONObject)json);
			}
			else if(json instanceof JSONArray){
				compress((JSONArray)json);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void compress(JSONObject jsonObj){
		//0.预处理，否则出现Empty Object后面会出异常
		if(jsonObj.isEmpty() || jsonObj.isNullObject()){
			return;
		}
		
		//1.找出所有的空属性
		Iterator it = jsonObj.keys();
		List<String> nullProperties = new ArrayList<String>();
		while(it.hasNext()){
			String key = it.next().toString();
			Object json = jsonObj.get(key);
			if(json == null){
				nullProperties.add(key);
			}
			else if(json instanceof JSONArray){
				JSONArray jsonArr = (JSONArray)json;
				if(jsonArr.size() == 0 || jsonArr.isEmpty()){
					nullProperties.add(key);
				}
				else{
					compress(jsonArr);
				}
			}
			else if(json instanceof JSONObject){
				JSONObject subJsonObj = (JSONObject)json;
				if(subJsonObj.isNullObject()){
					nullProperties.add(key);
				}
				else{
					compress(subJsonObj);
				}
			}
		}
		
		//2.删除步骤一找出的空属性
		for(String key : nullProperties){
			jsonObj.remove(key);
		}
	}
	
	private static void compress(JSONArray jsonArr){
		for(int i = 0; i < jsonArr.size(); i++){
			Object json = jsonArr.get(i);
			if(json instanceof JSONArray){
				trimJson((JSONArray)json);
			}
			else if(json instanceof JSONObject){
				trimJson((JSONObject)json);
			}
		}
	}
	
	public static String[] toStrings(JSONArray jsonArray){
		if(jsonArray == null || jsonArray.size() == 0)
			return null;
		
		String[] result = new String[jsonArray.size()];
		for(int i = 0; i < jsonArray.size(); i++)
			result[i] = jsonArray.getString(i);
		
		return result;
	}
	public static Integer getIntValue(JSONObject jsonObj, String key, Integer defaultValue){
		try{
			return jsonObj.containsKey(key) ? jsonObj.getInt(key) : defaultValue;
		}
		catch(Exception e){
			//e.printStackTrace();
			return defaultValue;
		}	
	}
	public static Integer getIntValue(JSONObject jsonObj, String key){
		return getIntValue(jsonObj, key, null);
	}
	
	public static Double getDoubleValue(JSONObject jsonObj, String key, Double defaultValue){
		try{
			return jsonObj.containsKey(key) ? jsonObj.getDouble(key) : defaultValue;
		}
		catch(Exception e){
			//e.printStackTrace();
			return defaultValue;
		}	
	}
	public static Double getDoubleValue(JSONObject jsonObj, String key){
		return getDoubleValue(jsonObj, key, null);
	}
	public static Boolean getBooleanValue(JSONObject jsonObj, String key, Boolean defaultValue){
		try{
			return jsonObj.containsKey(key) ? jsonObj.getBoolean(key) : defaultValue;
		}
		catch(Exception e){
			//e.printStackTrace();
			return defaultValue;
		}	
	}
	public static Boolean getBooleanValue(JSONObject jsonObj, String key){
		return getBooleanValue(jsonObj, key, false);
	}
	public static String getStringValue(JSONObject jsonObj, String key, String defaultValue){
		try{
			return jsonObj.containsKey(key) ? jsonObj.get(key).toString() : defaultValue;
		}
		catch(Exception e){
			//e.printStackTrace();
			return defaultValue;
		}	
	}
	public static String getStringValue(JSONObject jsonObj, String key){
		return getStringValue(jsonObj, key, null);
	}
	
	public static boolean containsKey(JSONArray jsonArray,String str){
		
		if(jsonArray==null||StrFuncs.isEmpty(str))
			return false;
		
		for (int i = 0; i < jsonArray.size(); i++) {
			String value = jsonArray.getString(i);
			if(value.equals(str))
				return true;
		}
		
		return false;
	}
}
