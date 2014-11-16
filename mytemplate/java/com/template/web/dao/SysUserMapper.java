//Powered By if, Since 2014 - 2020

package com.template.web.dao;

import com.template.web.model.SysUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author 
 */

public interface SysUserMapper {

   /**
    *新增sysUser
    */  
    public int insertSysUser(Map<String,Object> params);
   /**
    *修改sysUser
    */  
    public int updateSysUser(Map<String,Object> params);
    /**
    *根据id删除单个sysUser
    */ 
    public int deleteSysUser(Long id);
   /**
    *根据id集合删除多个sysUser
    */ 
    public int deleteSysUsers(List<Long> idList);
   /**
    *根据id查询单个sysUser
    */ 
    public SysUser findSysUserById(Long id);
   /**
    *根据条件查询查询sysUser列表
    */ 
	public List<SysUser> findSysUserListByParams(Map<String, Object> params);
   
	/**
	 * 验证账号
	 */
	public SysUser checkUser(@Param("username") String username,@Param("password") String password);

}
