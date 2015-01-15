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
	
	/**
	 * 添加或更新用户
	* @param sysUser
	* @return
	 */
	public int saveSysUser(SysUser sysUser){
		int count = 0;
		SysOffice sysOffice = sysOfficeMapper.findOfficeCompanyIdByDepId(sysUser.getOfficeId());
		Long companyId = sysUser.getOfficeId();
		if(sysOffice != null){
			companyId = sysOffice.getId();
		}
		sysUser.setCompanyId(companyId);
		if(null == sysUser.getId()){
			count = this.insertSelective(sysUser);
		}else{
			sysRoleMapper.deleteUserRoleByUserId(sysUser.getId());
			count = this.updateByPrimaryKeySelective(sysUser);
		}
		sysRoleMapper.insertUserRoleByUserId(sysUser);
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
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser.setPassword(password);
		return this.select(sysUser).get(0);
	}
	
}
