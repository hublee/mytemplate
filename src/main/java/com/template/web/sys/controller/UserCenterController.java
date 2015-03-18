package com.template.web.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userCenter")
public class UserCenterController {

	@RequestMapping
	public String viewInfo(){
		return "sys/userCenter/userCenter";
	}
	
}
