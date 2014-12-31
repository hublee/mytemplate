//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysRole;

/**
 * 
 * @author 
 */

public interface SysRoleMapper extends Mapper<SysRole> {

   public int insertRoleResource(SysRole sysRole);
   
   public int insertRoleOffice(SysRole sysRole);
   
   public int deleteRoleResource(SysRole sysRole);
   
   public int deleteRoleOffice(SysRole sysRole);
   
   public List<SysRole> findPageInfo(Map<String, Object> params);

}
