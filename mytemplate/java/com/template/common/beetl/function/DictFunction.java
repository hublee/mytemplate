package com.template.common.beetl.function;

import java.util.ArrayList;
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

	@Override
	public List<SysDict> call(Object[] paras, Context ctx) {
		try {
			SysDict sysDict = new SysDict();
			sysDict.setType(paras[0].toString());
			sysDict.setDelFlag(Constant.DEL_FLAG_NORMAL);
			List<SysDict> dicts = null;
			try {
				dicts = sysDictService.findSysDictListByParams(sysDict);
				if(paras.length>1){
					if("group".equals(paras[1])){
						Map<String, Object> map = new HashMap<String, Object>();
						List<SysDict> newDicts = new ArrayList<SysDict>();
						for(SysDict dict : dicts){
							map.put(dict.getType(), dict);
						}
						for(Object sd : map.values()){
							newDicts.add((SysDict)sd);
						}
						return newDicts;
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
