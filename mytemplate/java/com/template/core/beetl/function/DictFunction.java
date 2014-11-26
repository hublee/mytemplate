package com.template.core.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import com.template.core.constant.Constant;
import com.template.web.model.SysDict;
import com.template.web.service.SysDictService;

/**
 * 字典方法
 * 传入参数 type
* @author  
* @date 2014年11月26日 上午10:00:52 
*
 */
@Component
public class DictFunction implements Function{
	
	@Resource
	private SysDictService sysDictService;

	@Override
	public List<SysDict> call(Object[] paras, Context ctx) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", paras[0]);
			params.put(Constant.FIELD_DEL_FLAG, Constant.DEL_FLAG_NORMAL);
			List<SysDict> dicts = null;
			try {
				dicts = sysDictService.findSysDictListByParams(params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dicts;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
