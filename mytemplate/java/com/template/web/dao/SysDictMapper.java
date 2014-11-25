//Powered By if, Since 2014 - 2020

package com.template.web.dao;

import com.template.web.model.SysDict;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

public interface SysDictMapper {

   /**
    *新增sysDict
    */  
    public int insertSysDict(Map<String,Object> params);
   /**
    *修改sysDict
    */  
    public int updateSysDict(Map<String,Object> params);
    /**
    *根据id删除单个sysDict
    */ 
    public int deleteSysDict(Long id);
   /**
    *根据id集合删除多个sysDict
    */ 
    public int deleteSysDicts(List<Long> idList);
   /**
    *根据id查询单个sysDict
    */ 
    public SysDict findSysDictById(Long id);
   /**
    *根据条件查询查询sysDict列表
    */ 
	public List<SysDict> findSysDictListByParams(Map<String, Object> params);
   

}
