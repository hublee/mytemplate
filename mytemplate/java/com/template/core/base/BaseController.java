package com.template.core.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {
	
	/**
	 * 转换成json字符串
	 * 
	 * @param object
	 * @return
	 */
	public String jsonStr(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
