package com.template.web.test;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.common.constant.Constant;
import com.template.common.utils.CacheUtils;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysUserService;
import com.template.web.sys.utils.SysUserUtils;

@Controller
@RequestMapping("${adminPath}/test")
public class TestController {
	
	@Resource
	private SysUserService sysUserService;

	@RequestMapping
	public String test(){
		//CacheUtils.getnonExpiredKeys(Constant.CACHE_SYS_USER);
		//SysUserUtils.test("so", "su");
		return "test";
	}
	
	
	public static void main(String[] args) {
		test1();
	}
	
	public static void test1(String... a){
		a[0] = "dd";
		System.out.println(a.length);
	}
	
}
