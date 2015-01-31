package com.template.web.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.excel.EasyXls;
import com.template.common.excel.bean.ExcelConfig;
import com.template.common.utils.FileUtils;
import com.template.common.utils.JsonUtils;
import com.template.web.sys.model.SysArea;
import com.template.web.sys.service.SysAreaService;

@Controller
@RequestMapping("${adminPath}/area")
public class AreaController {

	@Resource
	private SysAreaService sysAreaService;

	@RequestMapping
	public String toArea(Model model) {
		model.addAttribute("treeList",
				JsonUtils.getInstance().toJson(sysAreaService.findAllArea()));
		return "sys/area/area";
	}

	/**
	 * 区域树
	 * 
	 * @return
	 */
	@RequestMapping(value = "tree", method = RequestMethod.POST)
	public @ResponseBody List<SysArea> getAreaTreeList() {
		List<SysArea> list = sysAreaService.findAllArea();
		return list;
	}

	/**
	 * 添加或更新区域
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute SysArea sysArea) {
		return sysAreaService.saveSysArea(sysArea);
	}

	/**
	 * 删除区域及其子区域
	 * 
	 * @param resourceId
	 *            区域id
	 * @return
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody Integer dels(Long id) {
		Integer count = 0;
		if (null != id) {
			count = sysAreaService.deleteAreaByRootId(id);
		}
		return count;
	}

	/**
	 * 分页显示区域table
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageHelper.startPage(params);
		List<SysArea> list = sysAreaService.findSysAreaList(params);
		model.addAttribute("page", new PageInfo<SysArea>(list));
		return "sys/area/area-list";
	}

	/**
	 * 弹窗
	 * 
	 * @param id
	 * @param parentId
	 *            父类id
	 * @param mode
	 *            模式(add,edit,detail)
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{mode}/showlayer", method = RequestMethod.POST)
	public String showLayer(Long id, Long parentId,
			@PathVariable("mode") String mode, Model model) {
		SysArea area = null, pArea = null;
		if (StringUtils.equalsIgnoreCase(mode, "add")) {
			pArea = sysAreaService.selectByPrimaryKey(parentId);
		} else if (StringUtils.equalsIgnoreCase(mode, "edit")) {
			area = sysAreaService.selectByPrimaryKey(id);
			pArea = sysAreaService.selectByPrimaryKey(parentId);
		} else if (StringUtils.equalsIgnoreCase(mode, "detail")) {
			area = sysAreaService.selectByPrimaryKey(id);
			pArea = sysAreaService.selectByPrimaryKey(area.getParentId());
		}
		model.addAttribute("pArea", pArea).addAttribute("area", area);
		return mode.equals("detail") ? "sys/area/area-detail"
				: "sys/area/area-save";
	}

	/**
	 * 导出execl
	 */
	@RequestMapping(value = "export")
	public void exportFile(@RequestParam Map<String, Object> params,
			HttpServletResponse response) throws Exception {

		List<SysArea> list = sysAreaService.findSysAreaList(params);

		ExcelConfig config = new ExcelConfig.Builder(Map.class)
				.sheetNum(0)
				.startRow(1)
				.sheetName("区域")
				.separater(",")
				.addColumn("name,区域名称", "code,区域编码,150,java.lang.String",
						"pname,上级区域", "parentId,父级编号", "parentIds,所有父级编号",
						"type,类型", "icon,图标", "delFlag,状态", "remarks,备注",
						"createBy,创建人", "createDate,创建时间,200", "updateBy,更新者",
						"updateDate,更新时间").build();

		EasyXls.list2Xls(config, list, "区域.xls", response);

	}

	/**
	 * execl导入数据
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "import")
	public @ResponseBody void importFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ExcelConfig config = new ExcelConfig.Builder(Map.class)
				.sheetNum(0)
				.startRow(1)
				.separater(",")
				.mode(true)
				.addColumn("name", "code,java.lang.String", "pname",
						"parentId,java.lang.Long", "parentIds", "type", "icon",
						"delFlag", "remarks", "createBy",
						"createDate,java.util.Date", "updateBy",
						"updateDate,java.util.Date").build();
		int count = 0;
		try {
			List<SysArea> list = EasyXls.xls2List(config,
					FileUtils.uploadFile(request), SysArea.class);
			for (SysArea sysArea : list) {
				sysAreaService.insertSelective(sysArea);
				count++;
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("成功导入"+count + "条数据!");
		} catch (Exception e) {
			response.getWriter().write("导入失败");
			e.printStackTrace();
		}

	}

}
