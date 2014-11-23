package com.template.core.util;

import com.template.web.model.SysResource;

public class CodeUtil {
	
	public static Long[] getCodeAndPos(SysResource resource){
		long pos = 0,code = 1L;
		Long maxCode = resource.getCode(),maxPos = resource.getPos();
		if(null == maxCode){
			pos = 0;
			code = 1L;
		}else{
			if(maxCode >= 1L << 60){
				pos = maxCode + 1L;
				code = 1L;
			}else{
				pos = maxPos;
				code = maxCode << 1;
			}
		}
		return new Long[]{code,pos};
	}
	
}
