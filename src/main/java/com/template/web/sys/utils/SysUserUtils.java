package com.template.web.sys.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.spring.utils.SpringContextHolder;
import com.template.common.utils.CacheUtils;
import com.template.common.utils.ThreadLocalUtils;
import com.template.common.utils.TreeUtils;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.service.SysOfficeService;
import com.template.web.sys.service.SysResourceService;
import com.template.web.sys.service.SysRoleService;
import com.template.web.sys.service.SysUserService;

public class SysUserUtils {

	static SysResourceService sysResourceService = SpringContextHolder.getBean("sysResourceService");
	static SysUserService sysUserService = SpringContextHolder.getBean("sysUserService");
	static SysRoleService sysRoleService = SpringContextHolder.getBean("sysRoleService");
	static SysOfficeService sysOfficeService = SpringContextHolder.getBean("sysOfficeService");

	/**
	 * 设置用户的认证
	 */
	public static void setUserAuth() {
		//菜单树
		getUserMenus();
		//角色列表
		getUserRoles();
		//用户机构列表
		getUserOffice();
	}
	
	/**
	 * 登录用户持有的资源
	* @return
	 */
	public static Map<String, SysResource> getUserResources(){
		SysUser sysUser = getCacheLoginUser();
		Map<String, SysResource> userResources = CacheUtils.get(
				Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_RESOURCE
						+ sysUser.getId());
		if (userResources == null) {
			if (sysUser.isAdmin()) {
				userResources = BeetlUtils.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
			} else {
				List<SysResource> userRes = sysResourceService.findUserResourceByUserId(sysUser);
				userResources = new LinkedHashMap<String, SysResource>();
				for(SysResource res : userRes){
					if(StringUtils.isBlank(res.getUrl())){
						userResources.put(res.getId().toString(), res);
					}else{
						userResources.put(res.getUrl(), res);
					}
				}
			}
			CacheUtils.put(Constant.CACHE_SYS_RESOURCE,
					Constant.CACHE_USER_RESOURCE + sysUser.getId(),
					userResources);
		}
		return userResources;
	}
	
	/**
	 * 登录用户菜单树
	 */
	public static List<SysResource> getUserMenus(){
		SysUser sysUser = getCacheLoginUser();
		List<SysResource> userMenus = CacheUtils.get(
				Constant.CACHE_SYS_RESOURCE,
				Constant.CACHE_USER_MENU + sysUser.getId());
		if (userMenus == null) {
			Map<String, SysResource> userResources = getUserResources();
			userMenus = Lists.newArrayList();
			for(SysResource res : userResources.values()){
				if(Constant.RESOURCE_TYPE_MENU.equals(res.getType())){
					userMenus.add(res);
				}
			}
			userMenus = TreeUtils.toTreeNodeList(userMenus,SysResource.class);
			CacheUtils.put(Constant.CACHE_SYS_RESOURCE,
					Constant.CACHE_USER_MENU + sysUser.getId(), userMenus);
		}
		return userMenus;
	}
	
	/**
	 * 登录用户的角色
	 */
	public static List<SysRole> getUserRoles(){
		SysUser sysUser = getCacheLoginUser();
		List<SysRole> userRoles = CacheUtils.get(
				Constant.CACHE_SYS_ROLE,
				Constant.CACHE_USER_ROLE + sysUser.getId());
		if(userRoles == null){
			if(sysUser.isAdmin()){
				userRoles = sysRoleService.select(new SysRole());
			}else{
				userRoles = sysRoleService.findUserRoleListByUserId(sysUser.getId());
			}
			CacheUtils.put(Constant.CACHE_SYS_ROLE,
						Constant.CACHE_USER_ROLE + sysUser.getId(), userRoles);
		}
		return userRoles;
	}
	

	/**
	 * 登录用户的角色map形式
	 */
	public static Map<Long, SysRole> getUserRolesMap(){
		List<SysRole> list = SysUserUtils.getUserRoles();
		Map<Long, SysRole> userRolesMap = Maps.uniqueIndex(list, new Function<SysRole, Long>() {
			@Override
			public Long apply(SysRole sysRole) {
				return sysRole.getId();
			}
		});
		return userRolesMap;
	}
	
	
	/**
	 * 登录用户的机构
	* @return
	 */ 
	public static List<SysOffice> getUserOffice(){
		SysUser sysUser = getCacheLoginUser();
		List<SysOffice> userOffices = CacheUtils.get(
				Constant.CACHE_SYS_OFFICE,
				Constant.CACHE_USER_OFFICE + sysUser.getId());
		if(userOffices == null){
			SysOffice office = new SysOffice();
			if(sysUser.isAdmin()){
				userOffices = sysOfficeService.select(office);
			}else{
				office.setUserDataScope(SysUserUtils.dataScopeFilterString(null, null));
				userOffices = sysOfficeService.findEntityListByDataScope(office);
			}
			CacheUtils.put(Constant.CACHE_SYS_OFFICE,
					Constant.CACHE_USER_OFFICE + sysUser.getId(), userOffices);
		}
		return userOffices;
	}
	
	/**
	 * 数据范围过滤
	 * @param user 当前用户对象
	 * @param officeAlias 机构表别名
	 * @param userAlias 用户表别名，传递空，忽略此参数
	 * @param field field[0] 用户表id字段名称 为了减少中间表连接
	 * @return (so.office id=... or .. or)
	 */
	public static String dataScopeFilterString(String officeAlias, String userAlias,String... field){
		SysUser sysUser = getCacheLoginUser();
		if(StringUtils.isBlank(officeAlias)) officeAlias = "sys_office";
		List<String> dataScope = Lists.newArrayList();
		List<SysRole> userRoles = getUserRoles();
		StringBuilder tempSql = new StringBuilder();
		String dataScopeSql = "";
		if(!sysUser.isAdmin()){
			for(SysRole sr : userRoles){
				if(!dataScope.contains(sr.getDataScope()) && StringUtils.isNotBlank(officeAlias)){
					boolean isDataScopeAll = false;
					if (Constant.DATA_SCOPE_ALL.equals(sr.getDataScope())){
						isDataScopeAll = true;
					}
					else if (Constant.DATA_SCOPE_COMPANY_AND_CHILD.equals(sr.getDataScope())){
						//so.id=1 or so.parentIds like '0,1,%'
						tempSql.append(" or "+officeAlias+".id="+sysUser.getCompanyId());
						SysOffice sysOffice = sysOfficeService.selectByPrimaryKey(sysUser.getCompanyId());
						tempSql.append(" or "+officeAlias+".parent_ids like '"+sysOffice.getParentIds()+sysOffice.getId()+",%'");
					}
					else if (Constant.DATA_SCOPE_COMPANY.equals(sr.getDataScope())){
						//or so.id=1 or (so.parent_id=1 and so.type=2)
						tempSql.append(" or "+officeAlias+".id="+sysUser.getCompanyId());
						tempSql.append(" or ("+officeAlias+".parent_id="+sysUser.getCompanyId());
						tempSql.append(" and "+officeAlias+".type=2)");
					}
					else if (Constant.DATA_SCOPE_OFFICE_AND_CHILD.equals(sr.getDataScope())){
						//or so.id=5 or so.parentIds like '0,1,5,%'
						tempSql.append(" or "+officeAlias+".id="+sysUser.getOfficeId());
						SysOffice sysOffice = sysOfficeService.selectByPrimaryKey(sysUser.getOfficeId());
						tempSql.append(" or "+officeAlias+".parent_ids like '"+sysOffice.getParentIds()+sysOffice.getId()+",%'");
					}
					else if (Constant.DATA_SCOPE_OFFICE.equals(sr.getDataScope())){
						//or so.id=5
						tempSql.append(" or "+officeAlias+".id="+sysUser.getOfficeId());
					}
					else if (Constant.DATA_SCOPE_CUSTOM.equals(sr.getDataScope())){
						//or so.id in (1,2,3,4,5)
						List<Long> offices = sysOfficeService.findUserDataScopeByUserId(sysUser.getId());
						tempSql.append(" or "+officeAlias+".id in ("+StringUtils.join(offices, ",")+")");
					}
					if (!isDataScopeAll){
						if (StringUtils.isNotBlank(userAlias)){
							// or su.id=22
							if(field==null || field.length==0) field[0] = "id";
							tempSql.append(" or "+userAlias+"."+field[0]+"="+sysUser.getId());
						}else {
							//仅本人数据时候用到
							tempSql.append(" or "+officeAlias+".id is null");
						}
					}else{
						// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
						tempSql.delete(0, tempSql.length());
						break;
					}
					dataScope.add(sr.getDataScope());
				}
			}// for end
			
			if(StringUtils.isNotBlank(tempSql)){
				dataScopeSql = "("+tempSql.substring(tempSql.indexOf("or")+2, tempSql.length())+")";
			}
		}
		return dataScopeSql;
	}
	
	/**
	 * 清除缓存中用户认证
	 */
	public static void clearAllCachedAuthorizationInfo(List<Long> userIds) {
		if(CollectionUtils.isNotEmpty(userIds)){
			for (Long userId : userIds) {
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_RESOURCE + userId);
				
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_MENU + userId);
				
				CacheUtils.evict(Constant.CACHE_SYS_ROLE,
						Constant.CACHE_USER_ROLE + userId);
				
				CacheUtils.evict(Constant.CACHE_SYS_OFFICE,
						Constant.CACHE_USER_OFFICE + userId);
			}
		}
	}
	
	/**
	 * 清除缓存中的用户
	 */
	public static void clearCacheUser(Long userId){
		CacheUtils.evict(Constant.CACHE_SYS_USER,userId.toString());
	}
	
	/**
	 * 清除用户机构
	 */
	public static void clearOfficeAndDataScope(List<Long> userIds){
		for (Long userId : userIds) {
			CacheUtils.evict(Constant.CACHE_SYS_OFFICE,
					Constant.CACHE_USER_OFFICE + userId);
		}
	}
	
	/**
	 * 缓存登录用户,默认设置过期时间为20分钟,与session存活时间的同步
	* @param sysUser
	* @param sessionId
	 */
	public static void cacheLoginUser(SysUser sysUser){
		CacheUtils.put(Constant.CACHE_SYS_USER, sysUser.getId().toString(), 
				sysUser,getSession().getMaxInactiveInterval());
	}
	
	/**
	 * 从缓存中取登录的用户
	* @param sessionId
	* @return 登录用户
	 */
	public static SysUser getCacheLoginUser(){
		if(getSessionLoginUser() != null){
			return CacheUtils.get(Constant.CACHE_SYS_USER, 
					getSessionLoginUser().getId().toString());
		}
		return null;
	}
	

	/**
	 * 把session保存到局部线程中
	 * 
	 * @param sysUser
	 */
	public static void setThreadLocalSession(HttpSession session) {
		ThreadLocalUtils.set("session", session);
	}

	/**
	 * 得到session
	 */
	public static HttpSession getSession() {
		HttpSession session = ThreadLocalUtils.get("session",
				HttpSession.class);
		return session;
	}
	
	/**
	 * session中的用户
	 */
	public static SysUser getSessionLoginUser(){
		return (SysUser) getSession().getAttribute(Constant.SESSION_LOGIN_USER);
	}

}
