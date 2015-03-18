package com.template.web.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysUserCenterService;

@Controller
@RequestMapping("userCenter")
public class UserCenterController {

	@Resource
	private SysUserCenterService sysUserCenterService;

	@RequestMapping
	public String viewInfo(Model model) {
		SysUser sysUser = sysUserCenterService.getSysUserInfo();
		model.addAttribute("sysUser", sysUser);
		return "sys/userCenter/userCenter";
	}

}
