//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysUserMapper;
import com.template.web.sys.model.SysUser;

/**
 * 
 * @author 
 */

@Service("sysUserService")
public class SysUserService extends ServiceMybatis<SysUser>{

	@Resource
	private SysUserMapper sysUserMapper;
	
	public int saveSysUser(SysUser sysUser){
		int count = 0;
		if(null == sysUser.getId()){
			count = this.insertSelective(sysUser);
			sysUserMapper.insertUserRole(sysUser);
		}else{
			
		}
		return count;
	}
	
	/**
	 * 验证用户
	* @param username 用户名
	* @param password 密码
	* @return user
	 */
	public SysUser checkUser(String username,String password){
		return sysUserMapper.checkUser(username, password);
	}
	
}
