package com.template.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.web.model.SysDict;
import com.template.web.service.SysDictService;


@Controller
@RequestMapping("${adminPath}/area")
public class AreaController {
	
	@Resource
	private SysDictService sysDictService;

	@RequestMapping
	public String toArea(Model model,Map<String, Object> dictParams){
		List<SysDict> dict =  sysDictService.findSysDictListByParams(dictParams);
		model.addAttribute("dict", dict);
		return "org/area";
	}
	
}
