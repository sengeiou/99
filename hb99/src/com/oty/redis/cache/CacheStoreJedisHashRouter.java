package com.oty.redis.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;

public class CacheStoreJedisHashRouter implements CacheStoreRouter<JedisPool> {

	private static final Logger logger = LoggerFactory.getLogger(CacheStoreJedisHashRouter.class);
 
	public JedisPool pickUp(List<JedisPool> cacheStores, String cacheName, Object key) {
		int hashCode = (cacheName + String.valueOf(key)).hashCode();
		logger.debug("cacheName={}, key={}, hashCode={}", new Object[] { cacheName, key, Integer.valueOf(hashCode) });
		return (JedisPool) cacheStores.get(Math.abs(hashCode) % cacheStores.size());
	}
	
}
