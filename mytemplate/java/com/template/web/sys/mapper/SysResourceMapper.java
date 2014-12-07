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
	
	public int updateParentIds(SysResource sysResource);
	
	public List<Long> findIdsByRootId(Long id);
   
	public List<SysResource> findPageInfo(Map<String, Object> params);
	
	public SysResource findMaxCodeAndMaxPos();
	
}
