package com.template.common.beetl.function;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.utils.SysUserUtils;

@Component
public class AuthUserFunctions {

	/**
	 * 判断用户是否具有指定权限
	 */
	public boolean hasPermission(String url) {
		Map<String, SysResource> allRes = BeetlUtils
				.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
		SysResource sysResource = allRes.get(url);
		if (sysResource == null
				|| Constant.RESOURCE_COMMON.equals(sysResource.getCommon())) {
			return true;
		} 
		Map<String, SysResource> userRes = SysUserUtils
				.getUserPermission(SysUserUtils.getSessionUser());
		if (userRes.containsKey(url)) return true;
		return false;
	}

}
