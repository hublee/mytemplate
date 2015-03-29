package com.template.web.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.template.common.constant.Constant;
import com.template.common.exception.ShiroAuthenticationException;

public class FormAuthFilter extends FormAuthenticationFilter{

	protected void saveRequestAndRedirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		redirectToLogin(request, response);
	}
	
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		boolean result = super.onLoginFailure(token, e, request, response);
		if (!(e instanceof ShiroAuthenticationException)) {
			/*request.setAttribute(Finals.ADMIN_USER, new User(
					getUsername(request)));
			request.setAttribute(Finals.ERROR_MSG, e.getMessage());*/
		} else {
			request.setAttribute(Constant.ERROR_MSG, Constant.ERROR_MSG_CONTENT);
		}
		return result;
	}
	
	@Override
	protected UsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String userName = getUsername(request);// 获取用户名称
		String userPassword = getPassword(request);// 获取用户密码
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new UsernamePasswordToken(userName, userPassword,rememberMe, host);// 创建令牌
	}
	
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		UsernamePasswordToken token = createToken(request, response);// 获取用户令牌
		Subject subject = getSubject(request, response);
		try {
			boolean pass = true;
			try {
				subject.login(token);
			}catch(UnknownAccountException uae){ 
				uae.printStackTrace();
				pass = false;
	        }catch(IncorrectCredentialsException ice){  
	        	ice.printStackTrace();
	        	pass = false;
	        }catch(LockedAccountException lae){ 
	        	lae.printStackTrace();
	        	pass = false;
	        	return onLoginFailure(token, lae, request, response);
	        }catch(ExcessiveAttemptsException eae){  
	        	eae.printStackTrace();
	        	pass = false;
	        }catch (AuthenticationException e) {
	        	e.printStackTrace();
	        	pass = false;
			}
			if(pass){
				return onLoginSuccess(token, subject, request, response);
			}else{
				return onLoginFailure(token, new ShiroAuthenticationException(Constant.ERROR_MSG_CONTENT), request, response);
			}
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}
	
}
