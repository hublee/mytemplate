package com.template.common.beetl.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.template.common.utils.Collections3;
import com.template.common.utils.TreeUtils;
import com.template.web.sys.model.SysOffice;
import com.template.web.sys.model.SysRole;
import com.template.web.sys.service.SysOfficeService;
import com.template.web.sys.service.SysRoleService;

@Component
public class OfficeFunctions {
	
	@Resource
	private SysOfficeService sysOfficeService;
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 全部机构列表(缓存)
	* @param flag 是否深度copy一个缓存的对象
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
	 * 根据类型得到机构
	* @param type 类型
	 */
	public List<SysOffice> getOfficeByType(String type){
		List<SysOffice> list = getAllOfficeList(true);
		List<SysOffice> typeList = new ArrayList<SysOffice>();
		for(int i=0;i<list.size();i++){
			SysOffice sysOffice = list.get(i);
			if(StringUtils.equals(sysOffice.getType(), type)){
				if(type.equals("1")) sysOffice.remove("children");
				typeList.add(sysOffice);
			}
		}
		return typeList;
	}
	
	/**
	 * 得到全部的部门
	* @return
	 */
	public List<SysOffice> getOfficeDep(){
		List<SysOffice> newList = getOfficeByType("2");
		newList = TreeUtils.toTreeNodeList(newList);
		return newList;
	}
	
	public List<SysRole> getAllRole(){
		List<SysRole> roles = sysRoleService.select(null);
		return roles;
	}
	
	
	//一下都是暂时用
	/**
	 * 构造机构树
	* @return
	 */
	public List<SysOffice> getOfficeTree(){
		List<SysOffice> list = TreeUtils.toTreeNodeList(getAllOfficeList(true));
		return list;
	}
	
	
	/**
	 * 格式化树结构
	* @param items
	* @return
	 */
	public String formatTree(List<SysOffice> items){
		StringBuilder builder = new StringBuilder();
		formatTree(builder, items);
		return builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected void formatTree(StringBuilder builder, List<SysOffice> items){
		for(SysOffice tn : items){
			if(tn.getBooleanValue("hasChild")){
				builder.append("<option value='"+tn.getId()+"' style='font-weight:bold;' data-level="+tn.getIntValue("level")+">"
			+getTabStr(tn.getIntValue("level")-1)+tn.getName()+"</option>");
				formatTree(builder, (List<SysOffice>) tn.get("children"));
			}else{
				builder.append("<option value='"+tn.getId()+"' data-pid='"+tn.getParentId()+"'>"+getTabStr(tn.getIntValue("level"))+tn.getName()+"</option>");
			}
		}
	}
	
	protected String getTabStr(int level){
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<level;i++){
			builder.append("&emsp;");
		}
		return builder.toString();
	}
	
}
