//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.template.common.mybatis.mapper.Mapper;
import com.template.web.sys.model.SysArea;

/**
 * 
 * @author 
 */

public interface SysAreaMapper extends Mapper<SysArea>{
	
	public List<Long> findIdsByRootId(Long id);

	public List<SysArea> findPageInfo(Map<String, Object> params);
	
}
