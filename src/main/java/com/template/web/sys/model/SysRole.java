//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import java.util.Date;

import com.template.common.base.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
@Table(name="sys_role")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	

    private String dataScope; //data_scope <数据范围>

	
    private String name; //name <角色名称>

	
    private Long officeId; //office_id <归属机构>

	
    private String remarks; //remarks <备注信息>

    @Transient
    private Long[] resourceIds; //持有的资源
    @Transient 
    private Long[] officeIds; //机构
    @Transient
    private Long[] userIds; //绑定的用户
    
    public Long[] getUserIds(){
    	return (Long[])this.get("userIds");
    }
    
    public void setUserIds(Long[] userIds){
    	this.set("userIds", userIds);
    }

    public Long[] getOfficeIds(){
    	return (Long[])this.get("officeIds");
    }
    
    public void setOfficeIds(Long[] officeIds){
    	this.set("officeIds", officeIds);
    }

    public Long[] getResourceIds(){
    	return (Long[])this.get("resourceIds");
    }
    
    public void setResourceIds(Long[] resourceIds){
    	this.set("resourceIds", resourceIds);
    }

	public String getDataScope() {
		return this.getString("dataScope");
    }
   
    public void setDataScope(String dataScope) {
		this.set("dataScope", dataScope);
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


}
