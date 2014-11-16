//Powered By if, Since 2014 - 2020

package com.template.web.service;

import com.template.core.paging.PageHelper;
import com.template.core.paging.PageInfo;
import com.template.web.dao.SysUserMapper;
import com.template.web.model.SysUser;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author 
 */

@Service("sysUserService")
public class SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	
	/**
	 *新增SysUser
	 */
	public int insertSysUser(Map<String,Object> params){
	     return sysUserMapper.insertSysUser(params);
	}
	
	/**
	 *更新SysUser
	 *@param {"id":""}
	 */
	public int updateSysUser(Map<String,Object> params){
	     return sysUserMapper.updateSysUser(params);
	}
	
	/**
	 *删除单个SysUser
	 */
	public int deleteSysUser(Long id){
	   return sysUserMapper.deleteSysUser(id);
	}
	/**
	 *批量删除SysUser
	 */
	public int deleteSysUsers(List<Long> idList){
	   return sysUserMapper.deleteSysUsers(idList);
	}
	
	/**
	 *根据id查找一个SysUser
	 */
	 public SysUser findSysUserById(Long id){
	   return sysUserMapper.findSysUserById(id);
	}
	
	/**
	 * 根据条件分页查询SysUser列表
	 * @param {"pageNum":"页码","pageSize":"条数","isCount":"是否生成count sql",......}
	 */
	public PageInfo<SysUser> findSysUserPageInfo(Map<String,Object> params) {
		boolean isCount = params.containsKey("isCount")?
				Boolean.parseBoolean(params.get("isCount").toString()):true;
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 
        		Integer.parseInt(params.get("pageSize").toString()),isCount);
        List<SysUser> list=sysUserMapper.findSysUserListByParams(params);
        return new PageInfo<SysUser>(list);
	}
	
	/**
	 * 根据条件查询SysUser列表（不分页）
	 */
	public List<SysUser> findSysUserListByParams(Map<String,Object> params) {
	    return sysUserMapper.findSysUserListByParams(params);
	}
	
	/**
	 * 验证用户
	* @param username 用户名
	* @param password 密码
	* @return user
	 */
	public SysUser checkUser(String username,String password){
		return sysUserMapper.checkUser(username, password);
	}
	
}
