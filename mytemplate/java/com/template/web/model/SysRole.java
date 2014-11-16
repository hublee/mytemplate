//Powered By if, Since 2014 - 2020

package com.template.web.model;

import com.template.core.base.Entity;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysRole extends Entity {

	private static final long serialVersionUID = 1L;

    private Long id; //id <>

    private String code; //code <唯一键>

    private String description; //description <描述>

    private String name; //name <角色名称>

    private Integer sort; //sort <排序号>

    private Integer state; //state <状态(0.正常 1.禁用)>



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

	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
		this.set("name", name);
    }

	public Integer getSort() {
		return this.getInteger("sort");
    }
   
    public void setSort(Integer sort) {
		this.set("sort", sort);
    }

	public Integer getState() {
		return this.getInteger("state");
    }
   
    public void setState(Integer state) {
		this.set("state", state);
    }


}
