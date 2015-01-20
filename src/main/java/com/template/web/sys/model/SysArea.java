//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.template.common.base.BaseEntity;


/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
@Table(name="sys_area")
public class SysArea extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    private String code; //code <区域编码>

    private String name; //name <区域名称>

    private Long parentId; //parent_id <父级编号>

    private String parentIds; //parent_ids <所有父级编号>

    private String remarks; //remarks <备注信息>

    private String type; //type <区域类型>
    
    private String icon; //icon <图标>

    @Transient
    private String oldParentIds; //旧的pids,非表中字段，用作更新用

	public String getCode() {
		return this.getString("code");
    }
   
    public void setCode(String code) {
		this.set("code", code);
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

	public String getParentIds() {
		return this.getString("parentIds");
    }
   
    public void setParentIds(String parentIds) {
		this.set("parentIds", parentIds);
    }

	public String getRemarks() {
		return this.getString("remarks");
    }
   
    public void setRemarks(String remarks) {
		this.set("remarks", remarks);
    }

	public String getType() {
		return this.getString("type");
    }
   
    public void setType(String type) {
		this.set("type", type);
    }

    public String getOldParentIds() {
		return this.getString("oldParentIds");
    }
   
    public void setOldParentIds(String oldParentIds) {
		this.set("oldParentIds", oldParentIds);
    }
    
    public String getIcon() {
		return this.getString("icon");
    }
   
    public void setIcon(String icon) {
		this.set("icon", icon);
    }

}
