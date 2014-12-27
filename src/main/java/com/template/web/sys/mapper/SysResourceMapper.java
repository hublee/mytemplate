//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;


import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysResource;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

public interface SysResourceMapper extends Mapper<SysResource>{
	
	public int updateParentIds(SysResource sysResource);
	
	public int deleteIdsByRootId(Long id);
   
	public List<SysResource> findPageInfo(Map<String, Object> params);
	
	public SysResource findMaxCodeAndMaxPos();
	
}
