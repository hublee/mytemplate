//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.ehcache.pool.sizeof.annotations.IgnoreSizeOf;

import com.template.common.base.BaseEntity;


/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
@Table(name="sys_resource")
public class SysResource extends BaseEntity {

	private static final long serialVersionUID = 1L;

    private Long code; //code <权限码 1<<n>

    private String common; //common <是否是公共资源(0.权限资源 1.公共资源)>

    private String description; //description <描述>

    private String icon; //icon <图标>

    private String name; //name <资源名称>

    private Long parentId; //parent_id <父级id>

    private Integer pos; //pos <权限位,相当于对权限分组,从0开始>

    private Integer sort; //sort <排序号>

    private String status; //status <状态(0.正常 1.禁用)>

    private String type; //type <类型(0.菜单 1.按钮)>

    private String url; //url <链接>
    
    private String parentIds;
    
    @Transient
    private String oldParentIds; //旧的pids,非表中字段，用作更新用


	public Long getCode() {
		return this.getLong("code");
    }
   
    public void setCode(Long code) {
		this.set("code", code);
    }

	public String getCommon() {
		return this.getString("common");
    }
   
    public void setCommon(String common) {
		this.set("common", common);
    }

	public String getDescription() {
		return this.getString("description");
    }
   
    public void setDescription(String description) {
		this.set("description", description);
    }

	public String getIcon() {
		return this.getString("icon");
    }
   
    public void setIcon(String icon) {
		this.set("icon", icon);
    }

	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
		this.set("name", name);
    }

	public Long getParentId() {
		return this.getLong("parentId");
    }
   
    public void setParentId(Long parentId) {
		this.set("parentId", parentId);
    }

	public Integer getPos() {
		return this.getInteger("pos");
    }
   
    public void setPos(Integer pos) {
		this.set("pos", pos);
    }

	public Integer getSort() {
		return this.getInteger("sort");
    }
   
    public void setSort(Integer sort) {
		this.set("sort", sort);
    }

	public String getStatus() {
		return this.getString("status");
    }
   
    public void setStatus(String status) {
		this.set("status", status);
    }

	public String getType() {
		return this.getString("type");
    }
   
    public void setType(String type) {
		this.set("type", type);
    }

	public String getUrl() {
		return this.getString("url");
    }
   
    public void setUrl(String url) {
		this.set("url", url);
    }

    public String getParentIds() {
		return this.getString("parentIds");
    }
   
    public void setParentIds(String parentIds) {
		this.set("parentIds", parentIds);
    }
    
    public String getOldParentIds() {
		return this.getString("oldParentIds");
    }
   
    public void setOldParentIds(String oldParentIds) {
		this.set("oldParentIds", oldParentIds);
    }
    
    //设置权限码和权限位
    public void setCodeAndPos(Long maxCode,Integer maxPos){
    	Integer pos = 0;Long code = 1L;
    	//没有资源
		if(null == maxCode){
			pos = 0;
			code = 1L;
		}else{ 
			if(maxCode >= 1L << 32){ //权限码边界检测
				pos = maxPos + 1;
				code = 1L;
			}else{
				pos = maxPos;
				code = maxCode << 1;
			}
		}
		this.setCode(code);
		this.setPos(maxPos);
	}

}
