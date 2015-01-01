//Powered By if, Since 2014 - 2020

package com.template.web.sys.controller;

import java.util.List;
import java.util.Map;

import com.template.common.base.BaseController;
import com.template.common.utils.JsonUtils;
import com.github.pagehelper.PageInfo;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.service.SysRoleService;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "sys/role/role-list";
	}
	
	/**
	 * 添加或更新
	* @param params
	* @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute SysRole sysRole){
		return sysRoleService.saveSysRole(sysRole);
	}
	
	/**
	 * 删除
	* @param 
	* @return
	 */
	@RequestMapping(value="del",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return sysRoleService.deleteSysRole(id);
	}
	
	/**
	 * 弹窗显示
	* @param params {"mode":"1.add 2.edit 3.detail}
	* @return
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String layer(Long id,@PathVariable String mode, Model model){
		SysRole sysRole = null;
		if(StringUtils.equals("edit", mode)){
			sysRole = sysRoleService.selectByPrimaryKey(id);
			List<Long> resIds = sysRoleService.findResourceIdsByRoleId(id);
			if(sysRole.getDataScope().equals("9")){
				List<Long> officeIds = sysRoleService.findOfficeIdsByRoleId(id);
				model.addAttribute("officeIds", JsonUtils.getInstance().toJson(officeIds));
			}
			model.addAttribute("resIds", JsonUtils.getInstance().toJson(resIds));
		}
		model.addAttribute("sysrole", sysRole);
		return mode.equals("detail")?"sys/role/role-detail":"sys/role/role-save";
	}
	

}
