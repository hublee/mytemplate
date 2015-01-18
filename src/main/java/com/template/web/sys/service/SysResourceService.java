//Powered By if, Since 2014 - 2020

package com.template.web.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.common.base.ServiceMybatis;
import com.template.common.beetl.util.BeetlUtils;
import com.template.common.constant.Constant;
import com.template.common.utils.CacheUtils;
import com.template.common.utils.Collections3;
import com.template.common.utils.TreeUtils;
import com.template.web.sys.mapper.SysResourceMapper;
import com.template.web.sys.model.SysResource;
import com.template.web.sys.model.SysUser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author
 */

@Service("sysResourceService")
public class SysResourceService extends ServiceMybatis<SysResource> {

	@Resource
	private SysResourceMapper sysResourceMapper;

	/**
	 * 新增or更新SysResource
	 */
	// TODO 需要删除beetl缓存
	public int saveSysResource(SysResource sysResource) {
		int count = 0;
		// 新的parentIds
		sysResource.setParentIds(sysResource.getParentIds()
				+ sysResource.getParentId() + ",");
		if (null == sysResource.getId()) {
			SysResource codeAndPos = sysResourceMapper.findMaxCodeAndMaxPos();
			sysResource
					.setCodeAndPos(codeAndPos.getCode(), codeAndPos.getPos());
			count = this.insertSelective(sysResource);
		} else {
			// getParentIds() 当前选择的父节点parentIds , getParentId()父节点的id
			// 先更新parentId，此节点的parentIds以更新
			count = this.updateByPrimaryKeySelective(sysResource);
			// 不移动节点不更新子节点的pids
			if (!StringUtils.equals(sysResource.getOldParentIds(),
					sysResource.getParentIds())) {
				sysResourceMapper.updateParentIds(sysResource); // 批量更新子节点的parentIds
			}
		}
		return count;
	}

	/**
	 * 根据父id删除自身已经所有子节点
	 * 
	 * @param id
	 * @return
	 */
	// TODO 需要删beetl缓存
	public int deleteResourceByRootId(Long id) {
		return sysResourceMapper.deleteIdsByRootId(id);
	}

	/**
	 * 最大权限位
	 * 
	 * @return
	 */
	public int maxPos() {
		return sysResourceMapper.maxPos();
	}

	/**
	 * 根据用户id得到用户持有的资源
	 * 
	 * @param userId
	 * @return
	 */
	// TODO 需要删缓存
	public List<SysResource> findUserResourceByUserId(SysUser sysUser) {
		List<SysResource> userResources = CacheUtils.get(
				Constant.CACHE_SYS_RESOURCE, Constant.CACHE_USER_RESOURCE
						+ sysUser.getId());
		if (userResources == null) {
			if ((Constant.SUPER_ADMIN).equals(sysUser.getUserType())) {
				Map<String, SysResource> allRes = BeetlUtils
						.getBeetlSharedVars(Constant.CACHE_ALL_RESOURCE);
				userResources = new ArrayList<SysResource>();
				for (SysResource res : allRes.values()) {
					userResources.add(res);
				}
			} else {
				userResources = sysResourceMapper
						.findUserResourceByUserId(sysUser.getId());
			}
			CacheUtils.put(Constant.CACHE_SYS_RESOURCE,
					Constant.CACHE_USER_RESOURCE + sysUser.getId(),
					userResources);
		}
		return userResources;
	}

	/**
	 * 获得用户持有的左侧菜单资源树结构
	 */
	// TODO 需要删缓存
	public List<SysResource> getUserMenus(SysUser sysUser) {
		List<SysResource> userMenus = CacheUtils.get(
				Constant.CACHE_SYS_RESOURCE,
				Constant.CACHE_USER_MENU + sysUser.getId());
		if (userMenus == null) {
			List<SysResource> userResources = this
					.findUserResourceByUserId(sysUser);
			userMenus = Collections3.copyTo(userResources, SysResource.class);
			for (int i=0;i<userMenus.size();i++) {
				if (!(userMenus.get(i).getType()).equals(Constant.RESOURCE_TYPE_MENU)) {
					userMenus.remove(i);
				}
			}
			userMenus = TreeUtils.toTreeNodeList(userMenus);
			CacheUtils.put(Constant.CACHE_SYS_RESOURCE,
					Constant.CACHE_USER_MENU + sysUser.getId(), userMenus);
		}
		return userMenus;
	}

	/**
	 * 菜单管理分页显示筛选查询
	 * 
	 * @param params
	 *            {"name":"菜单名字","id":"菜单id"}
	 * @return
	 */
	public PageInfo<SysResource> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysResource> list = sysResourceMapper.findPageInfo(params);
		return new PageInfo<SysResource>(list);
	}

}
