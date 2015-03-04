package com.template.web.monitor.controller;


import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("${adminPath}/monitor/ehcache")
public class EhcacheMonitorController {
	
	@Resource
	private CacheManager ehcacheManager;

	@RequestMapping
	public String toEhcache(Model model){
		model.addAttribute("ehcacheManager", ehcacheManager);
		return "sys/monitor/ehcache";
	}
	
}
