package com.template.web.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.template.common.constant.Constant;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysUserService;

public class UserRealm extends AuthorizingRealm {

	@Resource
	private SysUserService sysUserService;

	public UserRealm() {
		// 该句作用是重写shiro的密码验证，让shiro用我自己的验证
		setCredentialsMatcher(new UserMatcher());
	}

	/**
	 * SHIRO授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("hhh");
		return null;
	}

	/**
	 * SHIRO认证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticattionToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authenticattionToken;
		String username = token.getUsername();
		SysUser sysUser = sysUserService.login(username);
		if (null != sysUser) {
			if (!sysUser.getStatus()) { // 账户被锁定
				throw new LockedAccountException(Constant.USER_LOCKED_MSG);
			}
			setSession(Constant.SESSION_LOGIN_USER, sysUser);
			return new SimpleAuthenticationInfo(username,
					sysUser.getPassword(), getName());
		} else {
			throw new UnknownAccountException();
		}
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
