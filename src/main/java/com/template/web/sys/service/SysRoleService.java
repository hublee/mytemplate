//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.web.sys.mapper.SysRoleMapper;
import com.template.web.sys.model.SysRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 */

@Service("sysRoleService")
public class SysRoleService extends ServiceMybatis<SysRole> {

	@Resource
	private SysRoleMapper sysRoleMapper;
	
	/**
	 *新增或更新SysRole
	 */
	public int saveSysRole(SysRole sysRole){
		int count = 0;
		if(null == sysRole.getId()){
			count = this.insertSelective(sysRole);
		}else{
			count = this.updateByPrimaryKeySelective(sysRole);
		}
	    return count;
	}
	
	/**
	 * 根据条件分页查询SysRole列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysRole> findPageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()),
				Integer.parseInt(params.get("pageSize").toString()), isCount);
        List<SysRole> list=null; //自己实现
        return new PageInfo<SysRole>(list);
	}
	

}
