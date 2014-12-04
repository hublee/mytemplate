package com.template.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/**
	 * 区域树
	* @return
	 */
	@RequestMapping("tree")
	public @ResponseBody List<SysArea> getAreaTreeList(){
		List<SysArea> list = sysAreaService.findSysArea();
		return list;
	}
	
	/*@RequestMapping("list")
	public List<SysArea> list(){
		return sysAreaService.findSysAreaListByParams(null);
	}*/
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(SysArea area,@RequestParam Map<String,Object> params){
		System.out.println("ceshi");
		return null;//sysAreaService.insertOrUpdateSysArea(params);
	}
	
	
}
