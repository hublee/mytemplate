package com.template.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${adminPath}/test")
public class TestController {

	@RequestMapping
	public String test(){
		return "test";
	}
	
}
