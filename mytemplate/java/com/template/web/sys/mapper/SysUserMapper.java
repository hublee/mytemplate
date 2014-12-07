//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;


import org.apache.ibatis.annotations.Param;

import com.template.common.mybatis.mapper.Mapper;
import com.template.web.sys.model.SysUser;

/**
 * 
 * @author 
 */

public interface SysUserMapper extends Mapper<SysUser>{

   
	/**
	 * 验证账号
	 */
	public SysUser checkUser(@Param("username") String username,@Param("password") String password);

}
