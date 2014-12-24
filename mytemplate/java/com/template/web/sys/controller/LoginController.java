package com.template.web.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.common.utils.Global;
import com.template.common.utils.PasswordEncoder;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysResourceService;
import com.template.web.sys.service.SysUserService;


@Controller
@RequestMapping("${adminPath}")
public class LoginController {

	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 管理主页
	* @param model
	* @param request
	* @return
	 */
	@RequestMapping
	public String toIndex(Model model,HttpServletRequest request) {
		request.getSession().removeAttribute("code"); //清除code
		SysUser user = (SysUser)request.getSession().getAttribute(Global.getSessionUserKey());
		if(null == user) return "redirect:"+Global.getAdminPath()+"/login";
		model.addAttribute("menuList", sysResourceService.getMenuTree());
		return "index";
	}
	
	
	/**
	 * 跳转到登录页面
	* @return
	 */
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String toLogin() {
		
		return "login";
	}

	/**
	 * 登录验证
	* @param username
	* @param password
	* @param code
	* @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(String username,
			String password, String code, HttpServletRequest request) {

		Map<String, Object> msg = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		code = StringUtils.trim(code);
		username = StringUtils.trim(username);
		password = StringUtils.trim(password);
		String secPwd = PasswordEncoder.encrypt(password, username);
		String sessionCode = session.getAttribute("code").toString();
		if (!StringUtils.equalsIgnoreCase(code, sessionCode)){
			msg.put("error", "验证码错误");
			return msg;
		}
		SysUser user = sysUserService.checkUser(username, secPwd);
		if(null !=user){
			session.setAttribute(Global.getSessionUserKey(),user);
		}else{
			msg.put("error", "用户名或密码错误");
		}
		return msg;
	}
	
	/**
	 * 用户退出
	* @return 跳转到登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Global.getSessionUserKey());
		request.getSession().invalidate();
		return "redirect:/"+Global.getAdminPath()+"/login";
	}

}
