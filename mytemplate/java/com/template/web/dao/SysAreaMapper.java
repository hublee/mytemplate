//Powered By if, Since 2014 - 2020

package com.template.web.dao;

import com.template.web.model.SysArea;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

public interface SysAreaMapper {

   /**
    *新增sysArea
    */  
    public int insertSysArea(Map<String,Object> params);
   /**
    *修改sysArea
    */  
    public int updateSysArea(Map<String,Object> params);
    /**
    *根据id删除单个sysArea
    */ 
    public int deleteSysArea(Long id);
   /**
    *根据id集合删除多个sysArea
    */ 
    public int deleteSysAreas(List<Long> idList);
   /**
    *根据id查询单个sysArea
    */ 
    public SysArea findSysAreaById(Long id);
   /**
    *根据条件查询查询sysArea列表
    */ 
	public List<SysArea> findSysAreaListByParams(Map<String, Object> params);
   

}
