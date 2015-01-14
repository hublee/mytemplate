//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysOfficeMapper;
import com.template.web.sys.mapper.SysRoleMapper;
import com.template.web.sys.mapper.SysUserMapper;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.model.SysUser;

/**
 * 
 * @author 
 */

@Service("sysUserService")
public class SysUserService extends ServiceMybatis<SysUser>{

	@Resource
	private SysUserMapper sysUserMapper;
	
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Resource
	private SysOfficeMapper sysOfficeMapper;
	
	public int saveSysUser(SysUser sysUser){
		int count = 0;
		SysOffice sysOffice = sysOfficeMapper.findOfficeCompanyIdByDepId(sysUser.getOfficeId());
		sysUser.setCompanyId(sysOffice.getId());
		if(null == sysUser.getId()){
			count = this.insertSelective(sysUser);
			sysUserMapper.insertUserRole(sysUser);
		}else{
			count = this.updateByPrimaryKeySelective(sysUser);
		}
		return count;
	}
	
	/**
	 * 用户列表
	* @param params
	* @return
	 */
	public PageInfo<SysUser> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysUser> list = sysUserMapper.findPageInfo(params);
		return new PageInfo<SysUser>(list);
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
