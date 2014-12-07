//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysDictMapper;
import com.template.web.sys.model.SysDict;

/**
 * 
 * @author 
 */

@Service("sysDictService")
public class SysDictService extends ServiceMybatis<SysDict>{

	@Resource
	private SysDictMapper sysDictMapper;
	
	
	/**
	 * 根据字典类型查询,做一下缓存
	 */
	@Cacheable(value="sysCache",key="'dict_'+#sysDict['type']")
	public List<SysDict> findSysDictListByParams(SysDict sysDict) {
		List<SysDict> dicts = this.select(sysDict);
	    return dicts;
	}
	

}
