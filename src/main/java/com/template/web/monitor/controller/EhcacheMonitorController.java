package com.template.web.monitor.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.web.monitor.entity.EhcacheInfo;

@Controller
@RequestMapping("${adminPath}/monitor/ehcache")
public class EhcacheMonitorController {
	
	@Resource
	private CacheManager ehcacheManager;

	@RequestMapping
	public String toEhcache(Model model){
		String[] cacheNames = ehcacheManager.getCacheNames();
		/*for(String cacheName : cacheNames){
			Cache cache = ehcacheManager.getCache(cacheName);
			Long cacheHitCount = cache.getStatistics().cacheHitCount();
			Long cacheMissCount = cache.getStatistics().cacheMissCount();
			Long cacheTotalCount = cacheHitCount + cacheMissCount;
			cacheTotalCount = cacheTotalCount > 0 ? cacheTotalCount : 1;
			Double cacheHitPercent = cacheHitCount * 1.0 / cacheTotalCount;
			EhcacheInfo ehcacheInfo = new EhcacheInfo();
			ehcacheInfo.setCacheHits(cacheHitCount);
			ehcacheInfo.setCacheMisses(cacheMissCount);
			ehcacheInfo.setCacheHitPercent(cacheHitPercent);
		}
		*/
		model.addAttribute("ehcacheManager", ehcacheManager);
		return "sys/monitor/ehcache";
	}
	
}
