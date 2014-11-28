package com.template.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.core.utils.JsonUtils;
import com.template.web.model.SysArea;
import com.template.web.service.SysAreaService;


@Controller
@RequestMapping("${adminPath}/area")
public class AreaController {
	
	@Resource
	private SysAreaService sysAreaService;
	
	@RequestMapping
	public String toArea(Model model){
		return "org/area";
	}
	
	@RequestMapping("tree")
	public @ResponseBody String getAreaTreeList(){
		List<SysArea> list = sysAreaService.findSysAreaListByParams(null);
		return JsonUtils.getInstance().toJson(list);
	}
	
	
}
