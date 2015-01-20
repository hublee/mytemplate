package com.template.common.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.template.common.constant.Constant;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;

public class SessionListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if(Constant.SESSION_LOGIN_USER.equals(se.getName())){
			SysUserUtils.setThreadLocalSessionUser((SysUser) se.getValue());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		if(Constant.SESSION_LOGIN_USER.equals(se.getName())){
			SysUserUtils.setThreadLocalSessionUser((SysUser) se.getValue());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		
	}

}
