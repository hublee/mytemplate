//Powered By if, Since 2014 - 2020

package com.template.web.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.BaseController;
import com.template.common.excel.EasyXls;
import com.template.common.excel.bean.ExcelConfig;
import com.template.common.utils.FileUtils;
import com.template.web.sys.model.SysLog;
import com.template.web.sys.service.SysLogService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("${adminPath}/syslog")
public class SysLogController extends BaseController {
	
	
	@Resource
	private SysLogService sysLogService;
	
	/**
	 * 跳转到模块页面
	 */
	@RequestMapping
	public String toSysLog(Model model){
		return "文件名/html的名字";
	}
	
	/**
	 * 分页显示
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params,Model model){
		PageHelper.startPage(params);
		List<SysLog> list = sysLogService.findSysLogList(params);
		model.addAttribute("page", new PageInfo<SysLog>(list));
		return "table页面html";
	}
	
	/**
	 * 添加或更新
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public @ResponseBody Integer save(@ModelAttribute SysLog sysLog){
		return sysLogService.saveSysLog(sysLog);
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody Integer del(Long id){
		return sysLogService.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * 弹窗显示
	 * @param params {"mode":"1.add 2.edit 3.detail}
	 */
	@RequestMapping(value="{mode}/showlayer",method=RequestMethod.POST)
	public String layer(Model model){
		
		return "";
	}
	
	
	/**
	 * 导出execl
	 */
	@RequestMapping(value = "export",method = RequestMethod.POST)
	public void exportFile(@RequestParam Map<String, Object> params,
			HttpServletResponse response) throws Exception {

		List<SysLog> list = sysLogService.findSysLogList(params);

		ExcelConfig config = new ExcelConfig.Builder(Map.class)
				.sheetNum(0)
				.startRow(1)
				.sheetName("日志")
				.separater(",")
				.addColumn(
					"exception,异常信息,200,java.lang.String",
					"method,操作方式,200,java.lang.String",
					"params,操作提交的数据,200,java.lang.String",
					"remoteAddr,操作IP地址,200,java.lang.String",
					"requestUri,请求URI,200,java.lang.String",
					"type,日志类型,200,java.lang.String",
					"userAgent,用户代理,200,java.lang.String"
				).build();
		EasyXls.list2Xls(config, list, "日志.xls", response);

	}

	/**
	 * execl导入数据
	 */
	@RequestMapping(value = "import",method = RequestMethod.POST)
	public @ResponseBody void importFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ExcelConfig config = new ExcelConfig.Builder(Map.class)
				.sheetNum(0)
				.startRow(1)
				.separater(",")
				.mode(true)
				.addColumn(
					"exception,异常信息,200,java.lang.String",
					"method,操作方式,200,java.lang.String",
					"params,操作提交的数据,200,java.lang.String",
					"remoteAddr,操作IP地址,200,java.lang.String",
					"requestUri,请求URI,200,java.lang.String",
					"type,日志类型,200,java.lang.String",
					"userAgent,用户代理,200,java.lang.String"
				).build();
		int count = 0;
		try {
			List<SysLog> list = EasyXls.xls2List(config,
					FileUtils.uploadFile(request), SysLog.class);
			for (SysLog sysLog : list) {
				sysLogService.insertSelective(sysLog);
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
