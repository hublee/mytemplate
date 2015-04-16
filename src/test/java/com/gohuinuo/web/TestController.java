package com.gohuinuo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("test")
	public String test(){
		return "test";
	}
	
	@RequestMapping(value="duallist")
	public @ResponseBody void test1(String[] duallist){
		System.out.println(duallist);
	}
	
}
