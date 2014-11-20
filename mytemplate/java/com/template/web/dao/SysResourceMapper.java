//Powered By if, Since 2014 - 2020

package com.template.web.dao;

import com.template.web.model.SysResource;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

public interface SysResourceMapper {

   /**
    *新增sysResource
    */  
    public int insertSysResource(Map<String,Object> params);
   /**
    *修改sysResource
    */  
    public int updateSysResource(Map<String,Object> params);
    /**
    *根据id删除单个sysResource
    */ 
    public int deleteSysResource(Long id);
   /**
    *根据id集合删除多个sysResource
    */ 
    public int deleteSysResources(List<Long> idList);
   /**
    *根据id查询单个sysResource
    */ 
    public SysResource findSysResourceById(Long id);
   /**
    *根据条件查询查询sysResource列表
    */ 
	public List<SysResource> findSysResourceListByParams(Map<String, Object> params);
   
	/**
	 * 菜单管理分页显示筛选查询
	 */
	public List<SysResource> findMenuPageById(Map<String, Object> params);
	
	public int deleteResourceByRootId(Long id);

}
