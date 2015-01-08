package com.template.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import com.template.common.constant.Constant;
import com.template.web.sys.model.SysDict;
import com.template.web.sys.service.SysDictService;

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
	
	/**
	 * 字典方法
	* @param [0]类型 [1]allType
	* @return
	 */
	@Override
	public Object call(Object[] paras, Context ctx) {
		try {
			SysDict sysDict = new SysDict();
			sysDict.setType(paras[0].toString());
			sysDict.setDelFlag(Constant.DEL_FLAG_NORMAL);
			List<SysDict> dicts = null;
			try {
				dicts = sysDictService.findSysDictListByParams(sysDict);
				if(paras.length>=2){
					Map<String, Object> map = new HashMap<String, Object>();
					if("allType".equals(paras[1])){
						for(SysDict dict : dicts){
							map.put(dict.getType(), dict);
						}
						return map;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dicts;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
