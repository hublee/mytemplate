package com.template.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("area")
public class AreaController {

	@RequestMapping
	public String toArea(){
		
		return "org/area";
	}
	
}
