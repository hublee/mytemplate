//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysRole;

/**
 * 
 * @author 
 */

public interface SysRoleMapper extends Mapper<SysRole> {

   public int insertRoleResource(SysRole sysRole);
   
   public int insertRoleOffice(SysRole sysRole);

}
