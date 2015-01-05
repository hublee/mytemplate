//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.model.SysUser;

/**
 * 
 * @author 
 */

public interface SysRoleMapper extends Mapper<SysRole> {

   public int insertRoleResource(SysRole sysRole);
   
   public int insertRoleOffice(SysRole sysRole);
   
   public int insertUserRole(SysRole sysRole);
   
   public int deleteRoleResourceByRoleId(Long roleId);
   
   public int deleteRoleOfficeByRoleId(Long roleId);
   
   public int deleteUserRoleByRoleId(Long roleId);
   
   public List<Long> findResourceIdsByRoleId(Long roleId);
   
   public List<Long> findOfficeIdsByRoleId(Long roleId);
   
   public List<SysUser> findUserByRoleId(Long roleId);
   
   public List<SysResource> findResourceByRoleId(Long roleId);
   
   public List<SysRole> findPageInfo(Map<String, Object> params);

}
