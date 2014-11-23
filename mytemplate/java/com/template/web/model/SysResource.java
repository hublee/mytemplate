//Powered By if, Since 2014 - 2020

package com.template.web.model;

import com.template.core.base.Entity;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysResource extends Entity {

	private static final long serialVersionUID = 1L;

    private Long id; //id <>

    private Long code; //code <权限码 1<<n>

    private Integer common; //common <是否是公共资源(0.不是 1.是)>

    private String description; //description <描述>

    private String icon; //icon <图标>

    private String name; //name <资源名称>

    private Long pid; //pid <父级id>

    private Long pos; //pos <权限位,相当于对权限分组,从0开始>

    private Integer sort; //sort <排序号>

    private Integer status; //status <状态(0.正常 1.禁用)>

    private Integer type; //type <类型(0.菜单 1.按钮)>

    private String url; //url <链接>



	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
    }

	public Long getCode() {
		return this.getLong("code");
    }
   
    public void setCode(Long code) {
		this.set("code", code);
    }

	public Integer getCommon() {
		return this.getInteger("common");
    }
   
    public void setCommon(Integer common) {
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

	public Long getPid() {
		return this.getLong("pid");
    }
   
    public void setPid(Long pid) {
		this.set("pid", pid);
    }

	public Long getPos() {
		return this.getLong("pos");
    }
   
    public void setPos(Long pos) {
		this.set("pos", pos);
    }

	public Integer getSort() {
		return this.getInteger("sort");
    }
   
    public void setSort(Integer sort) {
		this.set("sort", sort);
    }

	public Integer getStatus() {
		return this.getInteger("status");
    }
   
    public void setStatus(Integer status) {
		this.set("status", status);
    }

	public Integer getType() {
		return this.getInteger("type");
    }
   
    public void setType(Integer type) {
		this.set("type", type);
    }

	public String getUrl() {
		return this.getString("url");
    }
   
    public void setUrl(String url) {
		this.set("url", url);
    }


}
