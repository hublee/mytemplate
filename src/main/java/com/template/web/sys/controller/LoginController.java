package com.template.web.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.common.constant.Constant;
import com.template.common.utils.IPUtils;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysResourceService;
import com.template.web.sys.service.SysUserService;
import com.template.web.sys.utils.SysUserUtils;

@Controller
public class LoginController {

	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private SysUserService sysUserService;
	
	
	/**
	 * 管理主页
	 */
	@RequestMapping(value="/")
	public String toIndex(Model model, HttpServletRequest request) {
		if(SecurityUtils.getSubject().isRemembered() || SecurityUtils.getSubject().isAuthenticated()){
			//model.addAttribute("menuList", SysUserUtils.getUserMenus());
			return "index";
		}
		return "logins";
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "login")
	public String toLogin() {
		if(SecurityUtils.getSubject().isAuthenticated()){
			return "redirect:/";
		}
		return "logins";
	}
	
	/**
	 * 用户退出
	 * 
	 * @return 跳转到登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		SysUserUtils.clearCacheUser(SysUserUtils.getSessionLoginUser().getId());
		request.getSession().invalidate();
		
		Subject subject = SecurityUtils.getSubject();
		try {
			if(subject.isAuthenticated()){
				subject.logout();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping("notauth")
	public String notAuth(){
		return "notauth";
	}
	
	@RequestMapping("notlogin")
	public String notLogin(){
		return "notlogin";
	}

}
