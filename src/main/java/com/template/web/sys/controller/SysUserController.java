package com.template.web.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysRoleService;
import com.template.web.sys.service.SysUserService;

@Controller
@RequestMapping("${adminPath}/sysuser")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 跳转到用户管理
	* @return
	 */
	@RequestMapping
	public String toSysUser(Model model){
		aa();
		System.out.println("asdsd1212");
		return "sys/user/user";
	}
	
	@Async
	public void aa(){
		try {
			Thread.sleep(10000);
			System.out.println("dddd");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存用户
	* @return
	 */
	@RequestMapping("save")
	public @ResponseBody Integer save(@ModelAttribute SysUser sysUser){
		return sysUserService.saveSysUser(sysUser);
	}
	
	/**
	 * 验证用户名是否存在
	* @param param
	* @return
	 */
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
