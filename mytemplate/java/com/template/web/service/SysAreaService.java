//Powered By if, Since 2014 - 2020

package com.template.web.service;

import com.template.core.paging.PageHelper;
import com.template.core.paging.PageInfo;
import com.template.web.dao.SysAreaMapper;
import com.template.web.model.SysArea;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysAreaService")
public class SysAreaService {

	@Resource
	private SysAreaMapper sysAreaMapper;
	
	/**
	 *新增SysArea
	 */
	public int insertSysArea(Map<String,Object> params){
	     return sysAreaMapper.insertSysArea(params);
	}
	
	/**
	 *更新SysArea
	 *@param {"id":""}
	 */
	public int updateSysArea(Map<String,Object> params){
	     return sysAreaMapper.updateSysArea(params);
	}
	
	/**
	 *删除单个SysArea
	 */
	public int deleteSysArea(Long id){
	   return sysAreaMapper.deleteSysArea(id);
	}
	/**
	 *批量删除SysArea
	 */
	public int deleteSysAreas(List<Long> idList){
	   return sysAreaMapper.deleteSysAreas(idList);
	}
	
	/**
	 *根据id查找一个SysArea
	 */
	 public SysArea findSysAreaById(Long id){
	   return sysAreaMapper.findSysAreaById(id);
	}
	
	/**
	 * 根据条件分页查询SysArea列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysArea> findSysAreaPageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 
        		Integer.parseInt(params.get("pageSize").toString()),isCount);
        List<SysArea> list=sysAreaMapper.findSysAreaListByParams(params);
        return new PageInfo<SysArea>(list);
	}
	
	/**
	 * 根据条件查询SysArea列表（不分页）
	 */
	public List<SysArea> findSysAreaListByParams(Map<String,Object> params) {
	    return sysAreaMapper.findSysAreaListByParams(params);
	}
	

}
