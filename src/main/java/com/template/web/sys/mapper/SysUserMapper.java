//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysUser;

/**
 * 
 * @author 
 */

public interface SysUserMapper extends Mapper<SysUser>{
	
	public int insertUserRole(SysUser sysUser);
	
	public List<SysUser> findUserByRoleId(Long roleId);
   
	/**
	 * 验证账号
	 */
	public SysUser checkUser(@Param("username") String username,@Param("password") String password);

}
