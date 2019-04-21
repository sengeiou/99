package com.oty.redis.cache;

public interface Serializer {
	
	public abstract String toString(Object paramObject);

	public abstract Object toObject(String paramString) throws ClassNotFoundException;
}
