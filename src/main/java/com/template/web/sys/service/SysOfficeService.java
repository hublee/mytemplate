//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysOfficeMapper;
import com.template.web.sys.model.SysOffice;

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

@Service("sysOfficeService")
@CacheConfig(cacheNames="sysCache")
public class SysOfficeService extends ServiceMybatis<SysOffice> {

	@Resource
	private SysOfficeMapper sysOfficeMapper;
	
	/**
	 *新增或更新SysOffice
	 */
	@CacheEvict(key="'office_all'")
	public int saveSysOffice(SysOffice sysOffice){
		int count = 0;
		//新的parentIds
		sysOffice.setParentIds(sysOffice.getParentIds()+sysOffice.getParentId()+","); 
		if(null == sysOffice.getId()){
			count = this.insertSelective(sysOffice);
		}else{
			//getParentIds() 当前选择的父节点parentIds , getParentId()父节点的id
			//先更新parentId，此节点的parentIds以更新
			count = this.updateByPrimaryKeySelective(sysOffice); 
			//不移动节点不更新子节点的pids
			if(!StringUtils.equals(sysOffice.getOldParentIds(), sysOffice.getParentIds())){
				sysOfficeMapper.updateParentIds(sysOffice); //批量更新子节点的parentIds
			}
		}
		return count;
	}
	
	@CacheEvict(key="'office_all'")
	public int deleteOfficeByRootId(Long id){
		return sysOfficeMapper.deleteIdsByRootId(id);
	}
	
	
	/**
	 * 根据条件分页查询SysOffice列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysOffice> findPageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 
        		Integer.parseInt(params.get("pageSize").toString()),isCount);
        List<SysOffice> list=sysOfficeMapper.findPageInfo(params); 
        return new PageInfo<SysOffice>(list);
	}
	
	@Cacheable(key="'office_all'")
	public List<SysOffice> findAllOffice(){
		return this.select(null);
	}

}
