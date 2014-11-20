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

    private String code; //code <唯一键>

    private String description; //description <描述>

    private String icon; //icon <图标>

    private String name; //name <资源名称>

    private Long pid; //pid <父级id>

    private Integer sort; //sort <排序号>

    private Integer status; //status <状态>

    private Integer type; //type <类型(0.菜单 1.按钮)>

    private String url; //url <链接>



	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
    }

	public String getCode() {
		return this.getString("code");
    }
   
    public void setCode(String code) {
		this.set("code", code);
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

	public Integer getSort() {
		return this.getInteger("sort");
    }
   
    public void setSort(Integer sort) {
		this.set("sort", sort);
    }

	public Integer getStatus() {
		return this.getInteger("state");
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
