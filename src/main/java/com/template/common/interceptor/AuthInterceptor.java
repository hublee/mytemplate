package com.template.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.utils.Global;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;

public class AuthInterceptor implements HandlerInterceptor {
	
	private String ignorePath = ".+/(login|code.image|notlogin)";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		String rootPath = Global.getCtxPath() + "/" + Global.getAdminPath();
		String path = "";
		int len = url.indexOf(rootPath)+rootPath.length()+1;
		if(len <= url.length()){
			path = url.substring(len, url.length());
		}
		
		SysUser sysUser = (SysUser) request.getSession().getAttribute(
				Constant.SESSION_LOGIN_USER);
		if(!url.matches(ignorePath)){
			if (sysUser == null) { // 转到登陆页面
				response.sendRedirect("/" + rootPath + "/notlogin");
				return false;
			} else {
				//激发监听，把当前用户放入局部线程中
				request.getSession().setAttribute(Constant.SESSION_LOGIN_USER, sysUser);
				Map<String, SysResource> allRes = BeetlUtils
						.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
				SysResource sysResource = allRes.get(path);
				if (sysResource == null
						|| Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
					return true;
				} 
				//检测用户认证是否改变，如果认证改变则重置，否则不进行任何操作
				SysUserUtils.setUserAuth(sysUser);
				//从缓存中的用户权限
				Map<String, SysResource> userRes = SysUserUtils.getUserResources(sysUser);
				if (userRes.containsKey(path)) {
					return true;
				} else {
					response.sendRedirect("/" + rootPath + "/notauth");
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
