//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.template.common.base.ServiceMybatis;
import com.template.common.base.TreeNode;
import com.template.common.mybatis.page.PageHelper;
import com.template.common.mybatis.page.PageInfo;
import com.template.common.utils.CodeUtils;
import com.template.web.sys.mapper.SysResourceMapper;
import com.template.web.sys.model.SysResource;

/**
 * 
 * @author 
 */

@Service("sysResourceService")
public class SysResourceService extends ServiceMybatis<SysResource>{

	@Resource
	private SysResourceMapper sysResourceMapper;
	
	/**
	 *新增or更新SysResource
	 */
	public int saveSysResource(SysResource sysResource){
		int count = 0;
		//新的parentIds
		sysResource.setParentIds(sysResource.getParentIds()+sysResource.getParentId()+","); 
		if(null == sysResource.getId()){
			Long[] cp = CodeUtils.getCodeAndPos(sysResourceMapper.findMaxCodeAndMaxPos());
			sysResource.setPos(cp[1]);
			sysResource.setCode(cp[0]);
			count = this.insertSelective(sysResource);
		}else{
			//getParentIds() 当前选择的父节点parentIds , getParentId()父节点的id
			//先更新parentId，此节点的parentIds以更新
			count = this.updateByPrimaryKeySelective(sysResource); 
			//不移动节点不更新子节点的pids
			if(!StringUtils.equals(sysResource.getOldParentIds(), sysResource.getParentIds())){
				sysResourceMapper.updateParentIds(sysResource); //批量更新子节点的parentIds
			}
		}
		return count;
	}
	
	/**
	 * 根据父id删除自身已经所有子节点
	* @param id
	* @return
	 */
	public int deleteResourceByRootId(Long id){
		return sysResourceMapper.deleteIdsByRootId(id);
	}
/*------------------------------------菜单操作----------------------------------------*/
	
	/**
	 * 菜单管理分页显示筛选查询
	 * 
	 * @param params
	 *            {"name":"菜单名字","id":"菜单id"}
	 * @return
	 */
	public PageInfo<SysResource> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(
				Integer.parseInt(params.get("pageNum").toString()),
				Integer.parseInt(params.get("pageSize").toString()));
		List<SysResource> list = sysResourceMapper.findPageInfo(params);
		return new PageInfo<SysResource>(list);
	}
	
	/**
	 * 构造侧边栏菜单树
	* @return
	 */
	public List<TreeNode> getMenuTree(){
		SysResource sysResource = new SysResource();
		sysResource.setType("0");
		List<SysResource> list = this.select(sysResource);
		List<TreeNode> menuList = new ArrayList<TreeNode>();
		for(int i=0;i<list.size();i++){
			SysResource res = list.get(i);
			TreeNode node = new TreeNode(res.getId(),
					res.getParentId(), res.getName(),
					res.getUrl(), res.getIcon());
			menuList.add(node);
		}
		return TreeNode.baseTreeNode(menuList);
	}
	
/*------------------------------------End菜单操作----------------------------------------*/
	
}
