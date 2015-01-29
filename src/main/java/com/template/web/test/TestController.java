package com.template.web.test;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.template.common.constant.Constant;
import com.template.web.sys.model.SysRole;
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
	
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("1","2");
		System.out.println();
	}
	
}
