package com.template.web.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.web.sys.service.SysUserService;

@Controller
@RequestMapping("${adminPath}/test")
public class TestController {
	
	@Resource
	private SysUserService sysUserService;

	@RequestMapping
	public String test(){
		return "test";
	}
	
}
