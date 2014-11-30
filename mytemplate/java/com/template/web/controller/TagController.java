package com.template.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 标签controller
* @ClassName: TagController  
* @author  
* @date 2014年11月28日 下午1:52:11 
*
 */
@Controller
@RequestMapping("${adminPath}/tag")
public class TagController {
	
	/**
	 * 树标签
	* @param model
	* @return
	 */
	@RequestMapping("/treeselect")
	public String treeSelect(HttpServletRequest request,Model model){
		model.addAttribute("url", request.getParameter("url")); 	// 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("treeId",  request.getParameter("treeId")); //id属性名称
		model.addAttribute("treePid", request.getParameter("treePid")); //pid属性名称
		return "modules/tagTreeselect";
	}

}
