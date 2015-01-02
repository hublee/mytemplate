package com.template.web.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysUserService;

@Controller
@RequestMapping("${adminPath}/sysuser")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 跳转到用户管理
	* @return
	 */
	@RequestMapping
	public String toSysUser(){
		return "sys/user/user";
	}
	
	@RequestMapping("checkname")
	public @ResponseBody Map<String, Object> checkName(String param){
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser sysUser = new SysUser();
		sysUser.setUsername(param);
		int count = sysUserService.selectCount(sysUser);
		if(count>0){
			msg.put("info", "此登录名太抢手了,请换一个!");
			msg.put("status", "n");
		}else{
			msg.put("status", "y");
		}
		return msg;
	}
	
}
