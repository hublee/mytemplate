package com.template.web.sys.utils;

import java.util.List;

import com.template.common.constant.Constant;
import com.template.common.spring.utils.SpringContextHolder;
import com.template.common.utils.CacheUtils;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysResourceService;
import com.template.web.sys.service.SysUserService;

public class SysUserUtils {

	static SysResourceService sysResourceService = SpringContextHolder.getBean("sysResourceService");
	static SysUserService sysUserService = SpringContextHolder.getBean("sysUserService");

	/**
	 * 清除缓存中用户的资源列表和菜单树结构
	 */
	public static void clearAllCachedAuthorizationInfo() {
		CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
				Constant.CACHE_USER_RESOURCE);
		CacheUtils.evict(Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_MENU);
	}

	/**
	 * 如果缓存中没有，设置用户的权限
	* @param sysUser
	 */
	public static SysUser setUserPermission(SysUser sysUser) {
		boolean isCache = CacheUtils.isCacheByKey(Constant.CACHE_SYS_RESOURCE,
				Constant.CACHE_USER_RESOURCE + sysUser.getId());
		if(!isCache){
			int maxPos = sysResourceService.maxPos(); // 最大权限位
			sysUser.setPosSum(new long[maxPos + 1]); // 总共的权限组
			// 用户持有的资源
			List<SysResource> userResources = sysResourceService.findUserResourceByUserId(sysUser);
			sysUser.calculatePermissionSum(userResources); // 计算权限和
		}
		return sysUser;
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
