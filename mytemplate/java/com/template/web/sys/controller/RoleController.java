//Powered By if, Since 2014 - 2020

package com.template.web.sys.controller;

import java.util.Map;

import com.template.common.base.BaseController;
import com.template.common.mybatis.page.PageInfo;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.service.SysRoleService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 
 */

@Controller
@RequestMapping("${adminPath}/role")
public class RoleController extends BaseController {
	
	
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 跳转到模块页面
	* @param model
	* @return 模块html
	 */
	@RequestMapping
	public String toSysRole(Model model){
		return "sys/role/role";
	}
	
	/**
	 * 分页显示
	* @param params
	* @return
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params,Model model){
		PageInfo<SysRole> page = sysRoleService.findPageInfo(params);
		model.addAttribute("page", page);
		return "table页面html";
	}
	
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute SysRole sysRole){
		return null;//sysRoleService.saveSysRole(sysRole);
	}
	
	
	/**
	 * 单个删除
	* @param 
	* @return
	 */
	@RequestMapping(value="del",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return sysRoleService.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * 弹窗显示
	* @param params {"mode":"1.add 2.edit 3.detail}
	* @return
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String layer(Model model){
		
		return "";
	}
	

}
