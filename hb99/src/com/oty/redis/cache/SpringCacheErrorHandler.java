package com.oty.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class SpringCacheErrorHandler implements CacheErrorHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringCacheErrorHandler.class);

	public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
		logger.warn("handleCacheGetError,key:{}", cache.getName());
		logger.error(e.getMessage(), e);
	}

	public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
		logger.warn("handleCachePutError,key:{}", cache.getName());
		logger.error(e.getMessage(), e);
	}

	public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
		logger.warn("handleCacheEvictError,key:{}", cache.getName());
		logger.error(e.getMessage(), e);
	}

	public void handleCacheClearError(RuntimeException e, Cache cache) {
		logger.warn("handleCacheClearError,key:{}", cache.getName());
		logger.error(e.getMessage(), e);
	}
	
}
