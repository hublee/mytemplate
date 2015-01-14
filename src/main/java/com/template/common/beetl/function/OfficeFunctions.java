package com.template.common.beetl.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.template.common.utils.Collections3;
import com.template.common.utils.StringConvert;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.service.SysOfficeService;

@Component
public class OfficeFunctions {
	
	@Resource
	private SysOfficeService sysOfficeService;
	
	/**
	 * 全部机构列表(缓存)
	* @param flag 是否深度copy一个缓存的对象,当操作缓存对象时候要copy
	* @return
	 */
	public List<SysOffice> getAllOfficeList(boolean flag){
		List<SysOffice> cacheOfficeList = null;
		if(flag){
			//深度copy一个缓存集合，防止因操作缓存list造成ehcache共享的线程不安全
			cacheOfficeList = Collections3.copyTo(sysOfficeService.getAllOffice(),
					SysOffice.class);
		}else{
			cacheOfficeList = sysOfficeService.getAllOffice();
		}
		return cacheOfficeList;
	}
	
	/**
	 * 全部机构 key:机构id  value:机构对象
	* @return
	 */
	public Map<Long, Object> getAllOfficeMap(){
		Map<Long, Object> map = new HashMap<Long, Object>();
		List<SysOffice> list = getAllOfficeList(false);
		for(SysOffice so : list){
			map.put(so.getId(), so);
		}
		return map;
	}
	
	/**
	 * 根据部门id拼接所属机构层级字符
	* @param officeId 部门id
	* @param offices 全部机构map
	* @return
	 */
	public String getOfficeStrByOfficeId(Long officeId,Map<Long, SysOffice> offices){
		String[] pids = ((SysOffice)offices.get(officeId)).getParentIds().split(",");
		String str = "";
		for(String id : pids){
			SysOffice so = (SysOffice)offices.get(StringConvert.toLong(id));
			if(so!=null)str+=so.getName()+" - ";
		}
		SysOffice so = (SysOffice)offices.get(officeId);
		str+=so.getName();
		return str;
	}
	
}
