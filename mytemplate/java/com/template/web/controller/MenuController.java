package com.template.web.controller;

import com.template.core.base.BaseController;
import com.template.core.paging.PageInfo;
import com.template.core.spring.SpringContextHolder;
import com.template.web.model.SysResource;
import com.template.web.service.SysResourceService;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("${adminPath}/menu")
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
		SpringContextHolder.getBean(SysResourceService.class);
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
	 * 添加或更新菜单
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/addorupdate", method = RequestMethod.POST)
	public @ResponseBody Integer add(@RequestParam Map<String, Object> params) {
		return sysResourceService.insertOrUpdateSysResource(params);
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
	 * 弹窗
	* @param resourceId id
	* @param pResourceId 父类id
	* @param mode 模式(add,edit,detail)
	* @param model
	* @return
	 */
	@RequestMapping(value="/{mode}/showlayer",method=RequestMethod.POST)
	public String showLayer(Long resourceId,Long pResourceId,@PathVariable("mode") String mode, Model model){
		SysResource resource = null, pResource = null;
		if(StringUtils.equalsIgnoreCase(mode, "add")){
			pResource = sysResourceService.findSysResourceById(pResourceId);
			model.addAttribute("menuTreeList",this.jsonStr(sysResourceService.getMenuTreeList()));
		}else if(StringUtils.equalsIgnoreCase(mode, "edit")){
			resource = sysResourceService.findSysResourceById(resourceId);
			pResource = sysResourceService.findSysResourceById(pResourceId);
			model.addAttribute("menuTreeList",this.jsonStr(sysResourceService.getMenuTreeList()));
		}else if(StringUtils.equalsIgnoreCase(mode, "detail")){
			resource = sysResourceService.findSysResourceById(resourceId);
			pResource = sysResourceService.findSysResourceById(resource.getPid());
		}
		model.addAttribute("pResource", pResource)
			.addAttribute("sysResource", resource);
		return mode.equals("detail")?"sysmanage/menu-detail":"sysmanage/menu-edit";
	}

}
