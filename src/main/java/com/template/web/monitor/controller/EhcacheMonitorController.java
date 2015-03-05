package com.template.web.monitor.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("${adminPath}/monitor/ehcache")
public class EhcacheMonitorController {

	@Resource
	private CacheManager ehcacheManager;

	@RequestMapping
	public String toEhcache(Model model) {
		model.addAttribute("ehcacheManager", ehcacheManager);
		return "sys/monitor/ehcache";
	}

	@RequestMapping("{cacheName}/detail")
	public String cacheNameDetail(
			@PathVariable("cacheName") String cacheName,
			@RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
			Model model) {
		@SuppressWarnings("unchecked")
		List<Object> nonExpiryKeys = ehcacheManager.getCache(cacheName).getKeys();
		List<Object> showKeys = Lists.newArrayList();
		for (Object key : nonExpiryKeys) {
			if (key.toString().contains(searchKey)) {
				showKeys.add(key);
			}
		}
		model.addAttribute("keys", showKeys);
		return "sys/monitor/ehcache-detail";
	}
	
	/*@RequestMapping("{cacheName}/{key}/detail")
	public String */

}
