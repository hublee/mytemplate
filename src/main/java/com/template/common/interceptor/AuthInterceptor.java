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

	//TODO 要修改截取url 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		System.out.println(request.getRequestURL().toString());
		String rootPath = Global.getCtxPath()+"/"+Global.getAdminPath();
		
		String path = url.substring(url.lastIndexOf("/") + 1, url.length());
		Map<String, SysResource> allRes = BeetlUtils
				.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
		SysResource sysResource = allRes.get(path);
		if (sysResource == null
				|| Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
			return true;
		} else {
			SysUser sysUser = (SysUser) request.getSession().getAttribute(
					Constant.SESSION_LOGIN_USER);
			if (sysUser == null) { // 转到登陆页面
				response.sendRedirect("/login");
				return false;
			} else {
				Map<String, SysResource> userRes = SysUserUtils.getUserPermission(sysUser);
				if(userRes.containsKey(path)) {
					return true;
				}else{
					response.sendRedirect("/"+Global.getCtxPath()+"/error/noauth.html");
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String a = "a/b/my/menu/list";
		System.out.println(a.indexOf("/b/my"));
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
