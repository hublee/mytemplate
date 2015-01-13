package com.template.common.beetl.function;

import org.springframework.stereotype.Component;

import com.template.common.utils.StringConvert;


@Component
public class StringUtilFunctions {

	public Long toLong(String str){
		return StringConvert.toLong(str);
	}
	
}
