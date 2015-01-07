//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysRoleMapper;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.model.SysUser;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

@Service("sysRoleService")
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
			count = this.updateByPrimaryKeySelective(sysRole);
			sysRoleMapper.deleteRoleResourceByRoleId(sysRole.getId());
			sysRoleMapper.deleteRoleOfficeByRoleId(sysRole.getId());
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
		sysRoleMapper.deleteRoleOfficeByRoleId(id);
		sysRoleMapper.deleteRoleResourceByRoleId(id);
		int count = this.deleteByPrimaryKey(id);
		return count;
	}
	
	/**
	 * 根据条件分页查询SysRole列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysRole> findPageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()),
				Integer.parseInt(params.get("pageSize").toString()), isCount);
        List<SysRole> list = sysRoleMapper.findPageInfo(params); 
        return new PageInfo<SysRole>(list);
	}
	
	public List<Long> findResourceIdsByRoleId(Long roleId){
		return sysRoleMapper.findResourceIdsByRoleId(roleId);
	}
	
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
	 * 根据用户id查询角色
	* @param userId
	* @return
	 */
	public List<SysRole> findRoleByUserId(Long userId){
		return sysRoleMapper.findRoleByUserId(userId);
	}
	
	/**
	 * 添加角色下的人员
	* @param sysRole
	* @return
	 */
	public int saveUserRole(SysRole sysRole){
		sysRoleMapper.deleteUserRoleByRoleId(sysRole.getId());
		if(sysRole.getUserIds().length>0) {
			sysRoleMapper.insertUserRole(sysRole);
		}
		return 1;
	}

}
