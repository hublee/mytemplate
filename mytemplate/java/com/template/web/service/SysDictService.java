//Powered By if, Since 2014 - 2020

package com.template.web.service;

import com.template.core.paging.PageHelper;
import com.template.core.paging.PageInfo;
import com.template.web.dao.SysDictMapper;
import com.template.web.model.SysDict;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysDictService")
public class SysDictService {

	@Resource
	private SysDictMapper sysDictMapper;
	
	/**
	 *新增SysDict
	 */
	public int insertSysDict(Map<String,Object> params){
	     return sysDictMapper.insertSysDict(params);
	}
	
	/**
	 *更新SysDict
	 *@param {"id":""}
	 */
	public int updateSysDict(Map<String,Object> params){
	     return sysDictMapper.updateSysDict(params);
	}
	
	/**
	 *删除单个SysDict
	 */
	public int deleteSysDict(Long id){
	   return sysDictMapper.deleteSysDict(id);
	}
	/**
	 *批量删除SysDict
	 */
	public int deleteSysDicts(List<Long> idList){
	   return sysDictMapper.deleteSysDicts(idList);
	}
	
	/**
	 *根据id查找一个SysDict
	 */
	 public SysDict findSysDictById(Long id){
	   return sysDictMapper.findSysDictById(id);
	}
	
	/**
	 * 根据条件分页查询SysDict列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysDict> findSysDictPageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 
        		Integer.parseInt(params.get("pageSize").toString()),isCount);
        List<SysDict> list=sysDictMapper.findSysDictListByParams(params);
        return new PageInfo<SysDict>(list);
	}
	
	/**
	 * 根据条件查询SysDict列表（不分页）
	 */
	public List<SysDict> findSysDictListByParams(Map<String,Object> params) {
	    return sysDictMapper.findSysDictListByParams(params);
	}
	

}
