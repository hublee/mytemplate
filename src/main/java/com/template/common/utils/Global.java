package com.template.common.utils;

public class Global {
	
	/**
	 *管理根路径
	* @return
	 */
	public static String getAdminPath(){
		return SysConstant.getValue("adminPath");
	}
	
	/**
	 * web上下文
	* @return
	 */
	public static String getCtxPath(){
		return SysConstant.getValue("ctxPath");
	}
	
}
