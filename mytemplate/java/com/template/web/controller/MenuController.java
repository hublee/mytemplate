package com.template.web.controller;

import com.template.core.base.BaseController;
import com.template.core.paging.PageInfo;
import com.template.web.model.SysResource;
import com.template.web.service.SysResourceService;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜单管理
* @ClassName: MenuController  
* @author  
* @date 2014年10月11日 上午11:38:28 
*
 */

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {

	@Resource
	private SysResourceService sysResourceService;

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @param model
	 * @return 菜单管理模块html
	 */
	@RequestMapping
	public String toMenu(Model model) {
		model.addAttribute("menuTreeList",
				this.jsonStr(sysResourceService.getMenuTreeList()));
		return "sysmanage/menu";
	}

	/**
	 * 分页显示菜单table
	 * 
	 * @param params
	 *            {"formId":"表单id",......}
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<SysResource> page = sysResourceService.findMenuPageById(params);
		model.addAttribute("page", page).addAttribute("formId",
				params.get("formId"));
		return "sysmanage/menu-page";
	}
	
	/**
	 * 添加菜单
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Integer add(@RequestParam Map<String, Object> params) {
		return sysResourceService.insertSysResource(params);
	}

	/**
	 * 更新菜单
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Integer update(@RequestParam Map<String, Object> params) {
		Integer count = 0;
		if (params.containsKey("id")) {
			count = sysResourceService.updateSysResource(params);
		}
		return count;
	}
	
	/**
	 * 删除菜单及其子菜单
	* @param resourceId 菜单id
	* @return
	 */
	@RequestMapping(value="/dels",method=RequestMethod.POST)
	public @ResponseBody Integer dels(Long resourceId){
		Integer count = 0;
		if(null != resourceId){
			count = sysResourceService.deleteResourceByRootId(resourceId);
		}
		return count;
	}


	/**
	 * 菜单添加弹窗
	* @param pResourceId 菜单的父级id
	* @param model
	* @return
	 */
	@RequestMapping(value = "/toaddlayer", method = RequestMethod.POST)
	public String toAddLayer(Long pResourceId, Model model) {
		if (null != pResourceId) {
			SysResource pResource = sysResourceService
					.findSysResourceById(pResourceId);
			model.addAttribute("pResource", pResource);
		}
		model.addAttribute("menuTreeList",
				this.jsonStr(sysResourceService.getMenuTreeList()));
		return "sysmanage/menu-add";
	}

	/**
	 * 菜单编辑弹窗
	* @param resourceId 菜单id
	* @param pResourceId 父级菜单id
	* @param model
	* @return
	 */
	@RequestMapping(value = "/toeditlayer", method = RequestMethod.POST)
	public String toEditLayer(Long resourceId,Long pResourceId, Model model) {
		if(null != resourceId){
			SysResource resource = sysResourceService.findSysResourceById(resourceId);
			model.addAttribute("sysResource", resource);
		}
		if(null != pResourceId && !pResourceId.equals("0")){
			SysResource pResource = sysResourceService
					.findSysResourceById(pResourceId);
			model.addAttribute("pResource", pResource);
		}
		model.addAttribute("menuTreeList",
				this.jsonStr(sysResourceService.getMenuTreeList()));
		return "sysmanage/menu-edit";
	}

}
