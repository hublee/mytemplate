//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.common.utils.PasswordEncoder;
import com.template.web.sys.mapper.SysOfficeMapper;
import com.template.web.sys.mapper.SysRoleMapper;
import com.template.web.sys.mapper.SysUserMapper;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.model.SysUser;
import com.template.web.sys.utils.SysUserUtils;

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
			String encryptPwd = PasswordEncoder.encrypt(sysUser.getPassword(), sysUser.getUsername());
			sysUser.setPassword(encryptPwd);
			count = this.insertSelective(sysUser);
		}else{
			sysRoleMapper.deleteUserRoleByUserId(sysUser.getId());
			List<Long> userIds = new ArrayList<Long>();
			userIds.add(sysUser.getId());
			SysUserUtils.clearAllCachedAuthorizationInfo(userIds);
			count = this.updateByPrimaryKeySelective(sysUser);
		}
		if(sysUser.getRoleIds()!=null) sysRoleMapper.insertUserRoleByUserId(sysUser);
		return count;
	}
	
	/**
	 * 删除用户
	* @param userId
	* @return
	 */
	public int deleteUser(Long userId){
		sysRoleMapper.deleteUserRoleByUserId(userId);
		return this.updateDelFlagToDelStatusById(SysUser.class, userId);
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
		List<SysUser> users = this.select(sysUser);
		if(users != null && users.size() == 1 && users.get(0) != null) {
			return users.get(0);
		}
		return null;
	}
	
}
