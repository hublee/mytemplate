package com.template.web.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
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
		List<SysRole> list = Lists.newArrayList();
		SysRole role1 = new SysRole();
		role1.setDataScope("1");
		SysRole role2 = new SysRole();
		role2.setDataScope("2");
		SysRole role3 = new SysRole();
		role3.setDataScope("2");
		list.add(role1);
		list.add(role2);
		list.add(role3);
		List<String> newList = Lists.transform(list, new Function<SysRole, String>() {
			@Override
			public String apply(SysRole sysRole) {
				return sysRole.getDataScope();
			}
		});
		System.out.println(newList.toString());
	}
	
}
