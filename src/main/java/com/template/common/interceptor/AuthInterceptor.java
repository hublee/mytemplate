package com.template.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.exception.Custom404Exception;
import com.template.common.utils.Global;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;

public class AuthInterceptor implements HandlerInterceptor {
	
	private String ignorePath = ".+/(login|code.image|notlogin)";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//是否是容器默认servlet，以备404处理
		if(handler instanceof DefaultServletHttpRequestHandler) return true;
		
		String url = request.getRequestURI(); //请求路径
		String rootPath = BeetlUtils.getBeetlSharedVars("rootPath");
		
		String path = "";
		int len = url.indexOf(rootPath)+rootPath.length()+1;
		if(len <= url.length()){
			path = url.substring(len, url.length());
		}
		
		//把session保存在局部线程中
		SysUserUtils.setThreadLocalSession(request.getSession());
		
		if(!url.matches(ignorePath)){
			//获得session中的登陆用户
			SysUser sessionUser = SysUserUtils.getSessionLoginUser();
			
			//获得缓存中的登陆用户
			SysUser cacheUser = SysUserUtils.getCacheLoginUser();
			
			if (sessionUser == null || cacheUser == null) { // 转到登陆页面
				response.sendRedirect(rootPath + "/notlogin");
				return false;
			} else {
				Map<String, SysResource> allRes = BeetlUtils
						.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
				SysResource sysResource = allRes.get(path);
				if (sysResource == null
						|| Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
					return true;
				} 
				//检测用户认证是否改变，如果认证改变则重置，否则不进行任何操作
				SysUserUtils.setUserAuth();
				//从缓存中的用户权限
				Map<String, SysResource> userRes = SysUserUtils.getUserResources();
				if (userRes.containsKey(path)) {
					return true;
				} else {
					response.sendRedirect(rootPath + "/notauth");
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
		
		/*String requestRri = request.getRequestURI();
		String uriPrefix = request.getContextPath() + Global.getAdminPath();
		System.out.println(requestRri+uriPrefix);*/
	}

}
