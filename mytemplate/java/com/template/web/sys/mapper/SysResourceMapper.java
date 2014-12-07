//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;


import java.util.List;
import java.util.Map;

import com.template.common.mybatis.mapper.Mapper;
import com.template.web.sys.model.SysResource;

/**
 * 
 * @author 
 */

public interface SysResourceMapper extends Mapper<SysResource>{

	/**
	 * 根据节点Id查询底下全部子节点包括孙子节点
	* @param id
	* @return
	 */
	public List<Long> findResourceByRootId(Long id);
   
	public List<SysResource> findMenuPageInfo(Map<String, Object> params);
	
	public SysResource findMaxCodeAndMaxPos();
	
}
