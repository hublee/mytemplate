//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
	 * 保存或更新
	* @param sysDict
	* @return
	 */
	@Caching(evict = {
		@CacheEvict(value="sysCache",key="'dict_'+#sysDict['type']"),
		@CacheEvict(value="sysCache",key="'dict_'")	
	})
	public int saveSysdict(SysDict sysDict){
		return this.save(sysDict);
	}
	
	/**
	 * 根据字典类型查询,做一下缓存
	 */
	@Cacheable(value="sysCache",key="'dict_'+#sysDict['type']")
	public List<SysDict> findSysDictListByParams(SysDict sysDict) {
		List<SysDict> dicts = this.select(sysDict);
	    return dicts;
	}

}
