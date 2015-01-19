package com.template.web.sys.utils;

import java.util.List;
import java.util.Map;

import com.template.common.constant.Constant;
import com.template.common.spring.utils.SpringContextHolder;
import com.template.common.utils.CacheUtils;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysResourceService;
import com.template.web.sys.service.SysRoleService;
import com.template.web.sys.service.SysUserService;

public class SysUserUtils {

	static SysResourceService sysResourceService = SpringContextHolder
			.getBean("sysResourceService");
	static SysUserService sysUserService = SpringContextHolder
			.getBean("sysUserService");
	static SysRoleService sysRoleService = SpringContextHolder
			.getBean("sysRoleService");

	/**
	 * 清除缓存中用户的资源列表和菜单树结构
	 */
	public static void clearAllCachedAuthorizationInfo(List<Long> userIds) {
		for (Long userId : userIds) {
			boolean isRole = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_MENU
							+ userId);
			if (isRole) {
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_MENU + userId);
			}
			boolean isRes = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_RESOURCE
							+ userId);
			if (isRes) {
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_RESOURCE + userId);
			}
		}
	}

	/**
	 * 如果缓存中没有，设置用户的权限
	 * 
	 * @param sysUser
	 */
	public static Map<String, SysResource> getUserPermission(SysUser sysUser) {
		return sysResourceService.findUserResourceByUserId(sysUser);
	}

	/**
	 * 把session用户保存到局部线程中
	 * 
	 * @param sysUser
	 */
	public static void setThreadLocalSessionUser(SysUser sysUser) {
		ThreadLocalUtils.set(Constant.SESSION_LOGIN_USER, sysUser);
	}

	/**
	 * 得到session中的用户
	 */
	public static SysUser getSessionUser() {
		SysUser sysUser = ThreadLocalUtils.get(Constant.SESSION_LOGIN_USER,
				SysUser.class);
		return sysUser;
	}

}
