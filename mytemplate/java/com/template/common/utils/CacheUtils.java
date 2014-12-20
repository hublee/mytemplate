package com.template.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.template.common.spring.util.SpringContextHolder;

public class CacheUtils {

	/**
	 * 
	* @Title: put  
	* @Description: 添加到缓存中
	* @param @param cacheName 配置的缓存name
	* @param @param key  存储缓存key
	* @param @param value  值
	* @return void     
	* @throws
	 */
	public static <T> void put(String cacheName,String key,T value) {
		 
		if (StringUtils.isNotBlank(key)) {
			getCache(cacheName).put(key, value);
		}
	}

	/**
	 * 
	* @Title: evict  
	* @Description: 删除缓存中的信息 
	* @param @param cacheName
	* @param @param key      
	* @return void     
	* @throws
	 */
	public static <T> void evict(String cacheName,String key) {
		if (StringUtils.isNotBlank(key)) {
			getCache(cacheName).evict(key);
		}
	}
	
	/**
	 * 
	* @Title: get  
	* @Description: 得到缓存中的信息
	* @param @param cacheName
	* @param @param key
	* @param @return      
	* @return T     
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName,String key) {
		T value = null;
		if (StringUtils.isNotBlank(key)) {
			ValueWrapper val = getCache(cacheName).get(key);
			if (val != null) {
				value = (T) val.get();
			}
		}
		return value;
	}
	
	public static boolean isCacheByKey(String cacheName,String key){
		ValueWrapper val = getCache(cacheName).get(key);
		return val==null?false:true;
	}

	public static Cache getCache(String cacheName) {
		EhCacheCacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
		return cacheManager.getCache(cacheName);
	}

}
