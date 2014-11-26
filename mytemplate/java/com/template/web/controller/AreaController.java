package com.template.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("${adminPath}/area")
public class AreaController {
	
	@RequestMapping
	public String toArea(Model model){
		return "org/area";
	}
	
}
