package com.template.web.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.common.base.Entity;
import com.template.web.sys.model.SysArea;
import com.template.web.sys.service.SysAreaService;



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
		List<SysArea> list = sysAreaService.select(new SysArea());
		return list;
	}
	
	/*@RequestMapping("list")
	public List<SysArea> list(){
		return sysAreaService.findSysAreaListByParams(null);
	}*/
	
	@RequestMapping(value="save")
	public @ResponseBody Integer save(@ModelAttribute SysArea sysArea){
		//sysAreaService.insertSelective(params);
		//SysArea a = new SysArea();
		//a.put("parentId", '0');
		System.out.println(sysArea);
		List<SysArea> areas = sysAreaService.select(sysArea);
		System.out.println("select:"+areas);
		//System.out.println(sysAreaService.selectCount(sysArea));
		//sysAreaService.insertSelective(sysArea);
		//sysAreaService.deleteByPrimaryKey(333333L);
		
		return 1;//sysAreaService
	}
	
	
}
