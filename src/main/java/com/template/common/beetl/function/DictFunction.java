package com.template.common.beetl.function;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
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
public class DictFunction{
	
	@Resource
	private SysDictService sysDictService;
	
	/**
	 * 字典方法
	* @param [0]类型 [1]allType
	* @return
	 */
	/*@Override
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
	}*/
	
	/**
	 * 根据类型和键值得到字典实体
	* @param type 如sys_data_scope等
	* @param value 
	 */
	public SysDict getDictByTypeAndValue(String type,String value){
		Table<String,String, SysDict> tableDicts = sysDictService.findAllDictTable();
		return tableDicts.get(type, value);
	}
	
	/*public List<SysDict> getDictListByValueList(List<String> values,String type){
		Collection<SysDict> dicts = sysDictService.findAllMultimap().get(type);
		List<SysDict> resultList = Lists.newArrayList();
		for(SysDict dict : dicts){
			if(values.contains(dict.getValue())){
				resultList.add(dict);
			}
		}
		return resultList;
	}*/
	
	public static void main(String[] args) {
		Multimap<String, Integer> multimap = ArrayListMultimap.create();
		multimap.put("1", 1);
		multimap.put("1", 2);
		System.out.println(multimap.get("1").toString());
	}

}
