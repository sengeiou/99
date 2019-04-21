package com.oty.redis.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import redis.clients.jedis.JedisPool;

@SuppressWarnings({"rawtypes", "unused"})
public class JedisCacheManager extends AbstractTransactionSupportingCacheManager implements DisposableBean {
 
	private static final Logger logger = LoggerFactory.getLogger(JedisCacheManager.class);

	public static final String sessionCacheName = "sessionCache";
	private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
 
	private Map namedClients;
	private CacheStoreJedisHashRouter cacheStoreJedisHashRouter;
	private Serializer serializer;
	private int timeout;
	private int expires;
	private List<JedisPool> jedisPoolList;
	private GenericObjectPoolConfig config;
	private boolean testOnBorrow;
	
	@Override
	public void afterPropertiesSet() {
		config.setTestOnBorrow(testOnBorrow);
		String server = namedClients.get("default").toString();
		String[] split = server.split(":");
		JedisPool jedisPool = new JedisPool(config, split[0], Integer.valueOf(split[1]), timeout);
		jedisPoolList = new ArrayList<JedisPool>();
		jedisPoolList.add(jedisPool);
	}
 
	public void destroy() throws Exception {
		
	}
	
	@Override
	protected Collection<? extends Cache> loadCaches() {
		Collection<Cache> values = cacheMap.values();
		return values;
	}

	@Override
	public Cache getCache(String name) {
		Cache cache = cacheMap.get(name);   
        if (cache == null) {   
            cache = new JedisCache(name, jedisPoolList, cacheStoreJedisHashRouter, serializer, expires);
            cacheMap.put(name, cache);   
        } 
        return cache;
	}

	public Map getNamedClients() {
		return namedClients;
	}

	public void setNamedClients(Map namedClients) {
		this.namedClients = namedClients;
	}

	public CacheStoreJedisHashRouter getCacheStoreJedisHashRouter() {
		return cacheStoreJedisHashRouter;
	}

	public void setCacheStoreJedisHashRouter(CacheStoreJedisHashRouter cacheStoreJedisHashRouter) {
		this.cacheStoreJedisHashRouter = cacheStoreJedisHashRouter;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public GenericObjectPoolConfig getConfig() {
		return config;
	}

	public void setConfig(GenericObjectPoolConfig config) {
		this.config = config;
	}

}
