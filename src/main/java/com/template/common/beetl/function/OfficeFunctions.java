package com.template.common.beetl.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.template.common.base.TreeNode;
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
	 * 全部机构 key:机构id  value:机构对象
	* @return
	 */
	public Map<Long, Object> getAllOffice(){
		Map<Long, Object> map = new HashMap<Long, Object>();
		List<SysOffice> list = sysOfficeService.getAllOffice();
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
		List<SysOffice> list = sysOfficeService.getAllOffice();
		List<SysOffice> typeList = new ArrayList<SysOffice>();
		for(SysOffice so : list){
			if(StringUtils.equals(so.getType(), type)){
				typeList.add(so);
			}
		}
		return typeList;
	}
	
	public List<SysRole> getAllRole(){
		List<SysRole> roles = sysRoleService.select(null);
		return roles;
	}
	
	/**
	 * 构造机构树
	* @return
	 */
	public List<TreeNode> getOfficeTree(){
		List<SysOffice> offices = sysOfficeService.getAllOffice();
		List<TreeNode> list = new ArrayList<TreeNode>();
		for(SysOffice so : offices){
			TreeNode treeNode = new TreeNode(so.getId(), so.getParentId(), so.getName(), null, null);
			list.add(treeNode);
		}
		return TreeNode.baseTreeNode(list);
	}
	
	/**
	 * 格式化树结构
	* @param items
	* @return
	 */
	public String formatTree(List<TreeNode> items){
		StringBuilder builder = new StringBuilder();
		formatTree(builder, items);
		return builder.toString();
	}
	
	protected void formatTree(StringBuilder builder, List<TreeNode> items){
		for(TreeNode tn : items){
			if(tn.getHasChild()){
				builder.append("<option value='"+tn.getId()+"' style='font-weight:bold;' data-level="+tn.getLevel()+">"
			+getTabStr(tn.getLevel()-1)+tn.getName()+"</option>");
				formatTree(builder, tn.getItems());
			}else{
				builder.append("<option value='"+tn.getId()+"' data-pid='"+tn.getParentId()+"'>"+getTabStr(tn.getLevel())+tn.getName()+"</option>");
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
