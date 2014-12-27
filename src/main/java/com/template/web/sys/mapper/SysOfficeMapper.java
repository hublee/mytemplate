//Powered By if, Since 2014 - 2020

package com.template.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.template.web.sys.model.SysOffice;

/**
 * 
 * @author 
 */

public interface SysOfficeMapper extends Mapper<SysOffice> {

	public List<SysOffice> findPageInfo(Map<String, Object> params);
	
	public int deleteIdsByRootId(Long id);

	public int updateParentIds(SysOffice sysOffice);
}
