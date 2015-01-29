package com.template.common.beetl.function;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.template.common.constant.Constant;
import com.template.web.sys.mapper.SysRoleMapper;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.service.SysRoleService;
import com.template.web.sys.utils.SysUserUtils;

@Component
public class RoleFunctions {
	
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	/**
	 * 用户的角色List形式(用户看到的角色)
	 */
	public List<SysRole> getUserRoleList(){
		Map<String, Object> params = Maps.newHashMap();
		params.put(Constant.CACHE_USER_DATASCOPE, SysUserUtils.dataScopeFilterString("so", "sur","user_id"));
		return sysRoleMapper.findPageInfo(params);
	}
	
	/**
	 * 用户角色map形式 key:角色id
	 */
	public Map<Long, SysRole> getUserRoleMap(){
		return SysUserUtils.getUserRolesMap();
	}
	
}
