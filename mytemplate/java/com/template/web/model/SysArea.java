//Powered By if, Since 2014 - 2020

package com.template.web.model;

import java.util.Date;

import com.template.core.base.Entity;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysArea extends Entity{

	private static final long serialVersionUID = 1L;

    private Long id; //id <编号>

    private String code; //code <区域编码>

    private String createBy; //create_by <创建者>

    private Date createDate; //create_date <创建时间>

    private String delFlag; //del_flag <删除标记>

    private String name; //name <区域名称>

    private String parentId; //parent_id <父级编号>

    private String parentIds; //parent_ids <所有父级编号>

    private String remarks; //remarks <备注信息>

    private String type; //type <区域类型>

    private String updateBy; //update_by <更新者>

    private Date updateDate; //update_date <更新时间>

	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
    	this.id = id;
		this.set("id", id);
    }

	public String getCode() {
		return this.getString("code");
    }
   
    public void setCode(String code) {
    	this.code = code;
		this.set("code", code);
    }

	public String getCreateBy() {
		return this.getString("create_by");
    }
   
    public void setCreateBy(String createBy) {
    	this.createBy = createBy;
		this.set("create_by", createBy);
    }

	public Date getCreateDate() {
		return this.getDate("create_date");
    }
   
    public void setCreateDate(Date createDate) {
    	this.createDate = createDate;
		this.set("create_date", createDate);
    }

	public String getDelFlag() {
		return this.getString("del_flag");
    }
   
    public void setDelFlag(String delFlag) {
    	this.delFlag = delFlag;
		this.set("del_flag", delFlag);
    }

	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
    	this.name = name;
		this.set("name", name);
    }

	public String getParentId() {
		return this.getString("parent_id");
    }
   
    public void setParentId(String parentId) {
    	this.parentId = parentId;
		this.set("parent_id", parentId);
    }

	public String getParentIds() {
		return this.getString("parent_ids");
    }
   
    public void setParentIds(String parentIds) {
    	this.parentIds = parentIds; 
		this.set("parent_ids", parentIds);
    }

	public String getRemarks() {
		return this.getString("remarks");
    }
   
    public void setRemarks(String remarks) {
    	this.remarks = remarks;
		this.set("remarks", remarks);
    }

	public String getType() {
		return this.getString("type");
    }
   
    public void setType(String type) {
    	this.type = type;
		this.set("type", type);
    }

	public String getUpdateBy() {
		return this.getString("update_by");
    }
   
    public void setUpdateBy(String updateBy) {
    	this.updateBy = updateBy;
		this.set("update_by", updateBy);
    }

	public Date getUpdateDate() {
		return this.getDate("update_date");
    }
   
    public void setUpdateDate(Date updateDate) {
    	this.updateDate = updateDate;
		this.set("update_date", updateDate);
    }


}
