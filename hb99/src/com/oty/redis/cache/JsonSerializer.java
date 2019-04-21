package com.oty.redis.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonSerializer implements Serializer {

	private static final String CLASS_OBJECT_BREAKER = "@@";

	public String toString(Object object) {
		return object.getClass().getName()
				+ CLASS_OBJECT_BREAKER
				+ JSON.toJSONString(
						object,
						new SerializerFeature[] { SerializerFeature.WriteClassName });
	}

	public Object toObject(String string) throws ClassNotFoundException {
		if ((string == null) || (string.trim().length() == 0)) {
			return null;
		}
		String[] classObjectPairs = string.split(CLASS_OBJECT_BREAKER, 2);
		if (classObjectPairs.length != 2) {
			throw new ClassNotFoundException("classObjectPairs length is not 2\n" + string);
		}
		Class<?> clazz = Class.forName(classObjectPairs[0]);

		return JSON.parseObject(classObjectPairs[1], clazz);
	}
	
}
