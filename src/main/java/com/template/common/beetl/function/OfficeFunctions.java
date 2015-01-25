package com.template.common.beetl.function;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.template.common.constant.Constant;
import com.template.common.utils.CacheUtils;
import com.template.common.utils.StringConvert;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.service.SysOfficeService;
import com.template.web.sys.utils.SysUserUtils;

@Component
public class OfficeFunctions {
	
	@Resource
	private SysOfficeService sysOfficeService;
	
	/**
	 * 全部机构 key:机构id  value:机构对象
	* @return
	 */
	public Map<Long, SysOffice> getAllOfficeMap(){
		Map<Long, SysOffice> allOfficeMap = CacheUtils.get(Constant.CACHE_SYS_OFFICE, "allOffice");
		if(allOfficeMap == null){
			allOfficeMap = Maps.newHashMap();
			List<SysOffice> list = sysOfficeService.select(new SysOffice());
			if(list!=null && list.size()>0){
				for(SysOffice so : list){
					allOfficeMap.put(so.getId(), so);
				}
			}
			CacheUtils.put(Constant.CACHE_SYS_OFFICE, "allOffice", allOfficeMap);
		}
		return allOfficeMap;
	}
	
	/**
	 * 根据部门id拼接所属机构层级字符
	* @param officeId 部门id
	* @param offices 全部机构map
	* @return
	 */
	public String getOfficeStrByOfficeId(Long officeId){
		Map<Long, SysOffice> offices = getAllOfficeMap();
		String str = "";
		if(offices!=null){
			if(offices.get(officeId) != null){
				String[] pids = ((SysOffice)offices.get(officeId)).getParentIds().split(",");
				for(String id : pids){
					SysOffice so = (SysOffice)offices.get(StringConvert.toLong(id));
					if(so!=null)str+=so.getName()+" - ";
				}
				SysOffice so = (SysOffice)offices.get(officeId);
				str+=so.getName();
			}
		}
		return str;
	}
	
	/**
	 * 得到用户机构
	* @return
	 */
	public List<SysOffice> getUserOfficeList(){
		return SysUserUtils.getUserOffice();
	}
	
}
