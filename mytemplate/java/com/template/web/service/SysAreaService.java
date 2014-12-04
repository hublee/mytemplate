//Powered By if, Since 2014 - 2020

package com.template.web.service;

import com.template.core.paging.PageHelper;
import com.template.core.paging.PageInfo;
import com.template.web.dao.SysAreaMapper;
import com.template.web.model.SysArea;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysAreaService")
public class SysAreaService {

	@Resource
	private SysAreaMapper sysAreaMapper;
	
	public List<SysArea> findSysArea(){
		//Map<String, Object> params = new HashMap<String, Object>();
		//params.put("parentId", 1);
		return null;//sysAreaMapper.select(params);
	}
	

}
