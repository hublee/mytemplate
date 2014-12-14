//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.template.common.mybatis.mapper.Mapper;
import com.template.web.sys.model.SysDict;

/**
 * 
 * @author 
 */

public interface SysDictMapper extends Mapper<SysDict>{

	public List<SysDict> findPageInfo(Map<String, Object> params);
	
}
