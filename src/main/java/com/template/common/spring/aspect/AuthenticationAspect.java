package com.template.common.spring.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.template.common.constant.Constant;
import com.template.common.utils.Global;
import com.template.web.sys.model.SysUser;

@Aspect
@Component
public class AuthenticationAspect {

	@Autowired
	HttpServletRequest request;
	
	@Pointcut(value="bean(*Controller)")
	public void authenticationAspect(){}
	
	@Before(value="authenticationAspect()")
	public void deBefore(JoinPoint joinpoint){
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		System.out.println(sysUser);
		System.out.println(joinpoint);
	}
	
}
