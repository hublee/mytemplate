package com.template.common.spring.aspect;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.template.common.constant.Constant;
import com.template.web.sys.model.SysUser;

@Deprecated
public class AuthenticationAspect {

	@Autowired
	HttpServletRequest request;
	
	@Pointcut(value="bean(*Controller)")
	public void authenticationAspect(){}
	
	@Before(value="authenticationAspect()")
	public void deBefore(JoinPoint joinpoint){
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		if(sysUser != null){
			session.setAttribute(Constant.SESSION_LOGIN_USER, sysUser);
		}
		try {
			getControllerMethodDescription(joinpoint);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sysUser);
		System.out.println(joinpoint);
		//return "/login";
	}
	
	/**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
     @SuppressWarnings("rawtypes")
	public  String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
    	 request.getRequestURI();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
         for (Method method : methods) {
             if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                 if (clazzs.length == arguments.length) {
                     break;
                }
            }
        }
         return description;
    }	
     
     
     
     
}
