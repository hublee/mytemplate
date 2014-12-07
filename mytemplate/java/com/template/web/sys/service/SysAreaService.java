//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import java.util.List;
import java.util.Map;

import com.template.common.base.ServiceMybatis;
import com.template.common.mybatis.page.PageHelper;
import com.template.common.mybatis.page.PageInfo;
import com.template.web.sys.mapper.SysAreaMapper;
import com.template.web.sys.model.SysArea;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysAreaService")
public class SysAreaService extends ServiceMybatis<SysArea>{

	@Resource
	private SysAreaMapper sysAreaMapper;
	
	/**
	 * 根据父id删除自身已经所有子节点
	* @param id
	* @return
	 */
	public int deleteAreaByRootId(Long id){
		List<Long> idList = sysAreaMapper.findIdsByRootId(id);
		int count = 0;
		for(Long cid : idList){
			this.deleteByPrimaryKey(cid);
			count++;
		}
		return count;
	}
	
	/**
	 * 区域管理分页显示筛选查询
	 * 
	 * @param params
	 *            {"name":"区域名字","id":"区域id"}
	 * @return
	 */
	public PageInfo<SysArea> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(
				Integer.parseInt(params.get("pageNum").toString()),
				Integer.parseInt(params.get("pageSize").toString()));
		List<SysArea> list = sysAreaMapper.findPageInfo(params);
		return new PageInfo<SysArea>(list);
	}

}
