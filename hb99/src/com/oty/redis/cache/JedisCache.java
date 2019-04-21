package com.oty.redis.cache; 

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"deprecation", "unchecked"})
public class JedisCache implements Cache {
	private static final Logger logger = LoggerFactory.getLogger(JedisCache.class);
	private String name;
	private List<JedisPool> jedisPoolList;
	private CacheStoreRouter<JedisPool> cacheStoreRouter;
	private Serializer serializer;
	private int expires;

	public JedisCache(String name, List<JedisPool> jedisList, CacheStoreRouter<JedisPool> cacheStoreRouter,
			Serializer serializer, int expires) {
		this.name = name;
		this.jedisPoolList = jedisList;
		this.cacheStoreRouter = cacheStoreRouter;
		this.serializer = serializer;
		this.expires = expires;
	}
 
	public Object getNativeCache() {
		return this.jedisPoolList;
	}
 
	public Cache.ValueWrapper get(Object key) {
		if (key != null) {
			JedisPool jedisPool = (JedisPool) this.cacheStoreRouter.pickUp(this.jedisPoolList, this.name, key);
			if (jedisPool != null) {
				Jedis jedis = null;
				boolean broken = false;
				try {
					jedis = jedisPool.getResource();
					String uniqueKey = uniqueKey(key);
					String valueSerial = jedis.get(uniqueKey);
					if ((this.name.equals("sessionCache")) && (this.expires > 0)) {
						long flag = -2L;
						flag = jedis.expire(uniqueKey, this.expires).longValue();
						if (flag == -2L) {
							logger.warn("Cache {} key {} miss.", this.name, key);
							return null;
						}
					}
					Object value = null;
					try {
						value = this.serializer.toObject(valueSerial);
					} catch (ClassNotFoundException e) {
						logger.error("", e);
					}
					logger.debug("uniqueKey={}, valueSerial={}", new Object[] { uniqueKey, valueSerial });
					if (value != null) {
						logger.info("Cache {} key {} hit.", this.name, key);
						return new SimpleValueWrapper(value);
					}
					logger.warn("Cache {} key {} miss.", this.name, key);
				} catch (JedisConnectionException e) {
					logger.error("key={}", key, e);
					broken = true;
				} finally {
					if (jedis != null) {
						if (broken) {
							jedisPool.returnBrokenResource(jedis);
						} else {
							jedisPool.returnResource(jedis);
						}
					}
				}
			} else {
				logger.error("Cache store route error.");
			}
		} else {
			logger.warn("Key is null.");
		}
		return null;
	}
 
	public <T> T get(Object o, Class<T> aClass) {
		Cache.ValueWrapper vw = get(o); 
		return (T) vw.get();
	}
 
	public void put(Object key, Object value) {
		if ((key != null) && (value != null)) {
			JedisPool jedisPool = (JedisPool) this.cacheStoreRouter.pickUp(this.jedisPoolList, this.name, key);
			if (jedisPool != null) {
				Jedis jedis = null;
				boolean broken = false;
				try {
					jedis = jedisPool.getResource();
					String uniqueKey = uniqueKey(key);
					String valueSerial = this.serializer.toString(value);
					String result = jedis.setex(uniqueKey, this.expires, valueSerial);
					logger.debug("uniqueKey={}, expires={}, valueSerial={}, result={}",
							new Object[] { uniqueKey, String.valueOf(this.expires), valueSerial, result });
				} catch (JedisConnectionException e) {
					logger.error("key={}", key, e);
					broken = true;
				} finally {
					if (jedis != null) {
						if (broken) {
							jedisPool.returnBrokenResource(jedis);
						} else {
							jedisPool.returnResource(jedis);
						}
					}
				}
			} else {
				logger.error("Cache store route error.");
			}
		} else {
			logger.warn("Key or value is null. Key={}, value={}", key, value);
		}
	}
 
	public Cache.ValueWrapper putIfAbsent(Object o, Object o1) {
		return null;
	}
 
	public void evict(Object key) {
		if (key != null) {
			JedisPool jedisPool = (JedisPool) this.cacheStoreRouter.pickUp(this.jedisPoolList, this.name, key);
			if (jedisPool != null) {
				Jedis jedis = null;
				boolean broken = false;
				String[] keys = ((String) key).split(",");
				for (String cacheKey : keys) {
					try {
						jedis = jedisPool.getResource();
						String uniqueKey = uniqueKey(cacheKey);
						long removeCount = jedis.del(uniqueKey).longValue();
						logger.debug("uniqueKey={}, removeCount={}",
								new Object[] { uniqueKey, String.valueOf(removeCount) });
					} catch (JedisConnectionException e) {
						logger.error("key={}", key, e);
						broken = true;
					} finally {
						if (jedis != null) {
							if (broken) {
								jedisPool.returnBrokenResource(jedis);
							} else {
								jedisPool.returnResource(jedis);
							}
						}
					}
				}
			} else {
				logger.error("Cache store route error.");
			}
		} else {
			logger.warn("Key is null.");
		}
	}
 
	public void clear() {
		long deleteCount = 0L;
		for (JedisPool jedisPool : this.jedisPoolList) {
			if (jedisPool != null) {
				Jedis jedis = null;
				boolean broken = false;
				try {
					jedis = jedisPool.getResource();
					Set<String> keySet = jedis.keys(uniqueKey("*"));
					String[] keyArray = (String[]) keySet.toArray(new String[keySet.size()]);
					if (keyArray.length > 0) {
						deleteCount += jedis.del(keyArray).longValue();
					}
				} catch (JedisConnectionException e) {
					logger.error("", e);
					broken = true;
				} finally {
					if (jedis != null) {
						if (broken) {
							jedisPool.returnBrokenResource(jedis);
						} else {
							jedisPool.returnResource(jedis);
						}
					}
				}
			}
		}
		logger.debug("count={}", Long.valueOf(deleteCount));
	}

	private String uniqueKey(Object key) {
		return this.name + "#" + String.valueOf(key);
	}
 
	public String getName() {
		return this.name;
	}
 
	public <T> T get(Object key, Callable<T> valueLoader) {
		return null;
	}
}
