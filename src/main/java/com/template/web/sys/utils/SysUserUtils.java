package com.template.web.sys.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.template.common.base.BaseEntity;
import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.spring.utils.SpringContextHolder;
import com.template.common.utils.CacheUtils;
import com.template.common.utils.Collections3;
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
	 * @param sysUser
	 */
	public static void setUserAuth(SysUser sysUser) {
		//菜单树
		getUserMenus(sysUser);
		//角色列表
		getUserRoles(sysUser);
		//数据范围set<Long>
		getUserDataScope(sysUser);
	}
	
	/**
	 * 用户持有的资源
	* @param sysUser
	* @return
	 */
	public static Map<String, SysResource> getUserResources(SysUser sysUser){
		Map<String, SysResource> userResources = CacheUtils.get(
				Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_RESOURCE
						+ sysUser.getId());
		if (userResources == null) {
			if ((Constant.SUPER_ADMIN).equals(sysUser.getUserType())) {
				userResources = BeetlUtils
						.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
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
	 * 用户菜单树
	 */
	public static List<SysResource> getUserMenus(SysUser sysUser){
		List<SysResource> userMenus = CacheUtils.get(
				Constant.CACHE_SYS_RESOURCE,
				Constant.CACHE_USER_MENU + sysUser.getId());
		if (userMenus == null) {
			Map<String, SysResource> userResources = getUserResources(sysUser);
			userMenus = new ArrayList<SysResource>();
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
	 * 用户的角色
	 */
	public static List<SysRole> getUserRoles(SysUser sysUser){
		List<SysRole> userRoles = CacheUtils.get(
				Constant.CACHE_SYS_ROLE,
				Constant.CACHE_USER_ROLE + sysUser.getId());
		if(userRoles == null){
			 userRoles = sysRoleService.findUserRoleListByUserId(sysUser.getId());
			 CacheUtils.put(Constant.CACHE_SYS_ROLE,
						Constant.CACHE_USER_ROLE + sysUser.getId(), userRoles);
		}
		return userRoles;
	}
	
	/**
	 * 得到用户的全部数据范围(持有的机构)，适应于多角色
	 */
	public static Set<Long> getUserDataScope(SysUser sysUser){
		List<SysRole> userRoles = CacheUtils.get(
				Constant.CACHE_SYS_ROLE,
				Constant.CACHE_USER_ROLE + sysUser.getId());
		Set<Long> userDataScope = CacheUtils.get(
				Constant.CACHE_SYS_OFFICE,
				Constant.CACHE_USER_DATASCOPE + sysUser.getId());
		if(userRoles!=null && userDataScope == null){
			userDataScope = new HashSet<Long>();
			boolean flag = false;
			 for(SysRole sr : userRoles){
				 if(Constant.DATA_SCOPE_ALL.equals(sr.getDataScope())){
					 break;
				 }else if(Constant.DATA_SCOPE_COMPANY_AND_CHILD.equals(sr.getDataScope())){
					 List<Long> offices = sysOfficeService.findOfficeIdsByRootId(sysUser.getCompanyId());
					 for(Long officeId : offices){
						 userDataScope.add(officeId);
					 }
				 }else if(Constant.DATA_SCOPE_COMPANY.equals(sr.getDataScope())){
					 userDataScope.add(sysUser.getCompanyId());
				 }else if(Constant.DATA_SCOPE_OFFICE_AND_CHILD.equals(sr.getDataScope())){
					 List<Long> offices = sysOfficeService.findOfficeIdsByRootId(sysUser.getOfficeId());
					 for(Long officeId : offices){
						 userDataScope.add(officeId);
					 }
				 }else if(Constant.DATA_SCOPE_OFFICE.equals(sr.getDataScope())){
					 userDataScope.add(sysUser.getOfficeId());
				 }else if(Constant.DATA_SCOPE_CUSTOM.equals(sr.getDataScope()) && !flag){
					 flag = true;
					 List<Long> offices = sysOfficeService.findUserDataScopeByUserId(sysUser.getId());
					 for(Long officeId : offices){
						 userDataScope.add(officeId);
					 }
				 }
			 }
			 CacheUtils.put(Constant.CACHE_SYS_OFFICE,
						Constant.CACHE_USER_DATASCOPE + sysUser.getId(), userDataScope);
		}
		return userDataScope;
	}
	
	/**
	 * 用户持有的机构
	* @return
	 */
	public static List<SysOffice> getUserOffice(){
		SysUser sysUser = getSessionUser();
		List<SysOffice> userOffices = CacheUtils.get(
				Constant.CACHE_SYS_OFFICE,
				Constant.CACHE_USER_OFFICE + sysUser.getId());
		if(userOffices == null){
			SysOffice office = new SysOffice();
			office.setUserDataScope(SysUserUtils.singleTableDataScopeFilter("","id"));
			userOffices = sysOfficeService.findEntityListByDataScope(office);
			CacheUtils.put(Constant.CACHE_SYS_OFFICE,
					Constant.CACHE_USER_OFFICE + sysUser.getId(), userOffices);
		}
		return userOffices;
	}
	
	
	/**
	 * 得到用户数据范围拼接的字符串
	* @param officeAlias 表的别名
	* @param field 字段的名称
	* @return 拼接完毕的字符串  如(office_id in (?,?,?) or ....)
	 */
	public static String dataScopeFilter(String[] officeAlias,String[] field){
		SysUser sysUser = getSessionUser();
		Set<Long> dataScope = getUserDataScope(sysUser);
		if(dataScope==null || dataScope.size()==0) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(" (");
		for(int i=0;i<officeAlias.length;i++){
			if(i>0) sb.append(" or ");
			if(StringUtils.isBlank(officeAlias[i])){
				sb.append(field[i]+" in ");
			}else{
				sb.append(officeAlias[i]+"."+field[i]+" in ");
			}
			if(dataScope !=null && dataScope.size() > 0){
				String so = StringUtils.join(dataScope.toArray(), ",");
				sb.append("("+so+")");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 单表根据范围查询 
	 * @param info {"officeAlias"表别名:,"field":字段名称}
	 * 泛型为空则 where office_id = ?
	 */
	public static String singleTableDataScopeFilter(String... info){
		if(info.length == 0){
			info = new String[]{"","office_id"};
		}
		return dataScopeFilter(new String[]{info[0]},new String[]{info[1]});
	}
	
	/**
	 * 清除缓存中用户认证
	 */
	public static void clearAllCachedAuthorizationInfo(List<Long> userIds) {
		for (Long userId : userIds) {
			//清除用户持有的资源
			boolean hasRes = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_RESOURCE
							+ userId);
			if (hasRes) {
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_RESOURCE + userId);
			}
			//清除用户菜单树
			boolean hasMenu = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_MENU
							+ userId);
			if (hasMenu) {
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_MENU + userId);
			}
			//清除用户角色
			boolean hasRole = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_ROLE, Constant.CACHE_USER_ROLE
							+ userId);
			if (hasRole) {
				CacheUtils.evict(Constant.CACHE_SYS_ROLE,
						Constant.CACHE_USER_ROLE + userId);
			}
			//清除用户数据范围
			boolean hasDataScope = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_OFFICE, Constant.CACHE_USER_DATASCOPE
							+ userId);
			if (hasDataScope) {
				CacheUtils.evict(Constant.CACHE_SYS_RESOURCE,
						Constant.CACHE_USER_DATASCOPE + userId);
			}
			//清除用户机构
			boolean hasOffice = CacheUtils.isCacheByKey(
					Constant.CACHE_SYS_OFFICE,
					Constant.CACHE_USER_OFFICE + userId);
			if(hasOffice){
				CacheUtils.evict(Constant.CACHE_SYS_OFFICE,
						Constant.CACHE_USER_OFFICE + userId);
			}
		}
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
