//Powered By if, Since 2014 - 2020

package com.template.web.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.template.core.base.Entity;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysArea extends Entity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id <编号>

    private String code; //code <区域编码>

    private String createBy; //create_by <创建者>

    private Date createDate; //create_date <创建时间>

    private String delFlag; //del_flag <删除标记(0活null 正常 1,删除)>

    private String name; //name <区域名称>

    private Long parentId; //parent_id <父级编号>

    private String parentIds; //parent_ids <所有父级编号>

    private String remarks; //remarks <备注信息>

    private String type; //type <区域类型>

    private String updateBy; //update_by <更新者>

    private Date updateDate; //update_date <更新时间>



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

	public String getCreateBy() {
		return this.getString("createBy");
    }
   
    public void setCreateBy(String createBy) {
		this.set("createBy", createBy);
    }

	public Date getCreateDate() {
		return this.getDate("createDate");
    }
   
    public void setCreateDate(Date createDate) {
		this.set("createDate", createDate);
    }

	public String getDelFlag() {
		return this.getString("delFlag");
    }
   
    public void setDelFlag(String delFlag) {
		this.set("delFlag", delFlag);
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

	public String getUpdateBy() {
		return this.getString("updateBy");
    }
   
    public void setUpdateBy(String updateBy) {
		this.set("updateBy", updateBy);
    }

	public Date getUpdateDate() {
		return this.getDate("updateDate");
    }
   
    public void setUpdateDate(Date updateDate) {
		this.set("updateDate", updateDate);
    }


}
