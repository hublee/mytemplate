//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysAreaMapper;
import com.template.web.sys.model.SysArea;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

@Service("sysAreaService")
@CacheConfig(cacheNames="sysCache")
public class SysAreaService extends ServiceMybatis<SysArea>{

	@Resource
	private SysAreaMapper sysAreaMapper;
	
	/**
	 *新增or更新SysArea
	 */
	@CacheEvict(key="'area_all'")
	public int saveSysArea(SysArea sysArea){
		int count = 0;
		//新的parentIds
		sysArea.setParentIds(sysArea.getParentIds()+sysArea.getParentId()+","); 
		if(null == sysArea.getId()){
			count = this.insertSelective(sysArea);
		}else{
			//getParentIds() 当前选择的父节点parentIds , getParentId()父节点的id
			//先更新parentId，此节点的parentIds以更新
			count = this.updateByPrimaryKeySelective(sysArea); 
			//不移动节点不更新子节点的pids
			if(!StringUtils.equals(sysArea.getOldParentIds(), sysArea.getParentIds())){
				sysAreaMapper.updateParentIds(sysArea); //批量更新子节点的parentIds
			}
		}
		return count;
	}
	
	/**
	 * 根据父id删除自身已经所有子节点
	* @param id
	* @return
	 */
	@CacheEvict(key="'area_all'")	
	public int deleteAreaByRootId(Long id){
		return sysAreaMapper.deleteIdsByRootId(id);
	}
	
	/**
	 * 区域管理分页显示筛选查询
	 * 
	 * @param params
	 *            {"name":"区域名字","id":"区域id"}
	 * @return
	 */
	public PageInfo<SysArea> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysArea> list = sysAreaMapper.findPageInfo(params);
		return new PageInfo<SysArea>(list);
	}
	
	/**
	 * 查询全部的区域
	* @return
	 */
	@Cacheable(key="'area_all'")
	public List<SysArea> findAllArea(){
		return this.select(null);
	}

}
