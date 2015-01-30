package com.template.common.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.template.common.excel.bean.ExcelConfig;
import com.template.common.excel.utils.XlsUtil;

/**
 * excel，对导入导出进行封装
 *
 * @author liuzh
 */
public class EasyXls {
	/**
	 * 打开代码生成器，请在项目中执行，只有这样才能加载相应的类
	 */
	/*
	 * public static void openGenerater() { XlsUtil.openGenerater(); }
	 */

	/**
	 * 导入xls到List
	 *
	 * @param xmlPath
	 *            xml完整路径
	 * @param xlsFile
	 *            xls文件路径
	 * @return List对象
	 * @throws Exception
	 */
	public static List<?> xls2List(String xmlPath, File xlsFile)
			throws Exception {
		return XlsUtil.xls2List(xmlPath, xlsFile);
	}

	/**
	 * 导入xls到List
	 *
	 * @param config
	 *            配置
	 * @param xlsFile
	 *            xls文件路径
	 * @return List对象
	 * @throws Exception
	 */
	public static List<?> xls2List(ExcelConfig config, File xlsFile)
			throws Exception {
		return XlsUtil.xls2List(config, xlsFile);
	}

	/**
	 * 导入xls到List
	 *
	 * @param xmlPath
	 *            xml完整路径
	 * @param inputStream
	 *            xls文件流
	 * @return List对象
	 * @throws Exception
	 */
	public static List<?> xls2List(String xmlPath, InputStream inputStream)
			throws Exception {
		return XlsUtil.xls2List(xmlPath, inputStream);
	}

	/**
	 * 导入xls到List
	 *
	 * @param config
	 *            配置
	 * @param inputStream
	 *            xls文件流
	 * @return List对象
	 * @throws Exception
	 */
	public static List<?> xls2List(ExcelConfig config, InputStream inputStream)
			throws Exception {
		return XlsUtil.xls2List(config, inputStream);
	}

	/**
	 * 导出list对象到excel
	 *
	 * @param list
	 *            导出的list
	 * @param xmlPath
	 *            xml完整路径
	 * @param filePath
	 *            保存xls路径
	 * @param fileName
	 *            保存xls文件名
	 * @return 处理结果，true成功，false失败
	 * @throws Exception
	 */
	public static boolean list2Xls(List<?> list, String xmlPath,
			String filePath, String fileName) throws Exception {
		return XlsUtil.list2Xls(list, xmlPath, filePath, fileName);
	}

	/**
	 * 导出list对象到excel
	 *
	 * @param config
	 *            配置
	 * @param list
	 *            导出的list
	 * @param filePath
	 *            保存xls路径
	 * @param fileName
	 *            保存xls文件名
	 * @return 处理结果，true成功，false失败
	 * @throws Exception
	 */
	public static boolean list2Xls(ExcelConfig config, List<?> list,String filePath, String fileName)
			throws Exception {
		return XlsUtil.list2Xls(config, list, filePath, fileName);
	}
	
	/**
	 * 以流的形式下载
	* @param config
	* @param list
	* @param fileName 文件名
	* @param response
	 */
	public static void list2Xls(ExcelConfig config, List<?> list,String fileName,
			HttpServletResponse response){
		 try {
			InputStream is = XlsUtil.list2Xls(config, list);
			if (!fileName.toLowerCase().endsWith(".xls")) {
                fileName += ".xls";
            }
			downloadFile(response, is, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载
	* @param response
	* @param is
	* @param realName
	 */
	public static void downloadFile(HttpServletResponse response, InputStream is, String realName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			//request.setCharacterEncoding("UTF-8");
			long fileLength = is.available();

			response.setContentType("application/octet-stream");
			realName = new String(realName.getBytes("GBK"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ realName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			// e.printStackTrace();//如果取消下载，这里会捕捉到异常
		} finally {
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
