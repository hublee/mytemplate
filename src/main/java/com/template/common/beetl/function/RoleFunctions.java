package com.template.common.beetl.function;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.template.web.sys.model.SysRole;
import com.template.web.sys.service.SysRoleService;

@Component
public class RoleFunctions {
	
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 全部角色列表(需要做缓存，未做)
	* @return
	 */
	public List<SysRole> getAllRoleList(){
		List<SysRole> roles = sysRoleService.select(new SysRole());
		return roles;
	}
	
}
