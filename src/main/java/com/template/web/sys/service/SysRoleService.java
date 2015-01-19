//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysRoleMapper;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

@Service("sysRoleService")
@CacheConfig(cacheNames="sysRole")
public class SysRoleService extends ServiceMybatis<SysRole> {

	@Resource
	private SysRoleMapper sysRoleMapper;
	
	/**
	 *新增或更新SysRole
	 */
	public int saveSysRole(SysRole sysRole){
		int count = 0;
		if(null == sysRole.getId()){
			count = this.insertSelective(sysRole);
		}else{
			sysRoleMapper.deleteRoleResourceByRoleId(sysRole.getId());
			sysRoleMapper.deleteRoleOfficeByRoleId(sysRole.getId());
			List<Long> userIds = sysRoleMapper.findUserIdsByRoleId(sysRole.getId());
			SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
			count = this.updateByPrimaryKeySelective(sysRole);
		}
		if(sysRole.getResourceIds().length>0){
			sysRoleMapper.insertRoleResource(sysRole);
		}
		if(("9").equals(sysRole.getDataScope()) && sysRole.getOfficeIds().length>0){
			sysRoleMapper.insertRoleOffice(sysRole);
		}
	    return count;
	}
	
	/**
	 * 删除角色
	* @param id
	 */
	public int deleteSysRole(Long id){
		sysRoleMapper.deleteUserRoleByRoleId(id);
		sysRoleMapper.deleteRoleOfficeByRoleId(id);
		sysRoleMapper.deleteRoleResourceByRoleId(id);
		List<Long> userIds = sysRoleMapper.findUserIdsByRoleId(id);
		SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
		int count = this.deleteByPrimaryKey(id);
		return count;
	}
	
	/**
	 * 添加角色绑定的人员
	* @param sysRole
	* @return
	 */
	public int saveUserRole(SysRole sysRole){
		sysRoleMapper.deleteUserRoleByRoleId(sysRole.getId());
		if(sysRole.getUserIds().length>0) {
			sysRoleMapper.insertUserRoleByRoleId(sysRole);
			List<Long> userIds = sysRoleMapper.findUserIdsByRoleId(sysRole.getId());
			SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
		}
		return 1;
	}
	
	
	/**
	 * 根据条件分页查询SysRole列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysRole> findPageInfo(Map<String,Object> params) {
        PageHelper.startPage(params);
        List<SysRole> list = sysRoleMapper.findPageInfo(params); 
        return new PageInfo<SysRole>(list);
	}
	
	/**
	 * 根据角色id查询拥有的资源id集合
	* @param roleId
	* @return
	 */
	public List<Long> findResourceIdsByRoleId(Long roleId){
		return sysRoleMapper.findResourceIdsByRoleId(roleId);
	}
	
	/**
	 * 根据角色id查询拥有的机构id集合
	* @param roleId
	* @return
	 */
	public List<Long> findOfficeIdsByRoleId(Long roleId){
		return sysRoleMapper.findOfficeIdsByRoleId(roleId);
	}
	
	/**
	 * 根据角色id查询拥有的资源 
	* @param roleId
	* @return
	 */
	public List<SysResource> findResourceByRoleId(Long roleId){
		return sysRoleMapper.findResourceByRoleId(roleId);
	}
	
	/**
	 * 根据角色id查询拥有此角色的用户
	* @param roleId
	* @return
	 */
	public List<SysUser> findUserByRoleId(Long roleId){
		return sysRoleMapper.findUserByRoleId(roleId);
	}
	
	/**
	 * 用户的角色List列表
	* @param userId
	* @return userRoleList
	 */
	public List<SysRole> findUserRoleListByUserId(Long userId){
		List<SysRole> userRoles = sysRoleMapper.findUserRoleByUserId(userId);
		return userRoles;
	}
	
	/**
	 * 用户的角色Map
	* @param userId
	* @return userRoleMap
	 */
	public Map<Long, SysRole> findUserRoleMapByUserId(Long userId){
		List<SysRole> roleList = this.findUserRoleListByUserId(userId);
		Map<Long, SysRole> userRoleMap = new HashMap<Long, SysRole>();
		if(roleList != null && roleList.size() > 0){
			for(SysRole role : roleList){
				userRoleMap.put(role.getId(), role);
			}
		}
		return userRoleMap;
	}
	
}
