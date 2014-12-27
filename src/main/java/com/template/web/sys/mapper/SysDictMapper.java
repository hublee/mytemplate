//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysDict;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

public interface SysDictMapper extends Mapper<SysDict>{

	public List<SysDict> findPageInfo(Map<String, Object> params);
	
}
