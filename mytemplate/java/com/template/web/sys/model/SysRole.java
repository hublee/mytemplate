//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import java.util.Date;

import com.template.common.base.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysRole extends Entity {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id <编号>

	
    private String createBy; //create_by <创建者>

	
    private Date createDate; //create_date <创建时间>

	
    private String dataScope; //data_scope <数据范围>

	
    private String delFlag; //del_flag <删除标记>

	
    private String name; //name <角色名称>

	
    private Long officeId; //office_id <归属机构>

	
    private String remarks; //remarks <备注信息>

	
    private String updateBy; //update_by <更新者>

	
    private Date updateDate; //update_date <更新时间>
    
    @Transient
    private String resourceIds;


    public String[] getResourceIds(){
    	String[] resIds = this.getString("resourceIds").split(",");
    	return (String[])this.get("resourceIds");
    }
    
    public void setResourceIds(String[] resourceIds){
    	this.set("resourceIds", resourceIds);
    }

	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
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

	public String getDataScope() {
		return this.getString("dataScope");
    }
   
    public void setDataScope(String dataScope) {
		this.set("dataScope", dataScope);
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

	public Long getOfficeId() {
		return this.getLong("officeId");
    }
   
    public void setOfficeId(Long officeId) {
		this.set("officeId", officeId);
    }

	public String getRemarks() {
		return this.getString("remarks");
    }
   
    public void setRemarks(String remarks) {
		this.set("remarks", remarks);
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
