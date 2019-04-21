package pub.utils;

import pub.functions.BeanFuncs;
import pub.types.JsonFormData;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DataUtils {

	private static String combileKey(String path, String key) {
		if (path == null || path.length() == 0) {
			return key;
		}
		return path + '.' + key;
	}

	private static void addDataEntry(Map<String, Object> data, String path, String key, Object value) {
		String fullKey = combileKey(path, key);
		if (value instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) value;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				addDataEntry(data, fullKey, entry.getKey(), entry.getValue());
			}
		}
		else if (value instanceof List) {
			throw new RuntimeException("not implemented yet..");
		}
		else {
			data.put(fullKey, value);
		}
	}
 
	public static JsonFormData toJsonFormData(Object bean) {
		Map<String, Object> map;
		try {
			map = (Map) BeanFuncs.dynamize(bean);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		JsonFormData jsonFormData = new JsonFormData();
		Map<String, Object> data = jsonFormData.getData();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			addDataEntry(data, null, entry.getKey(), entry.getValue());
		}
		return jsonFormData;
	}

}
