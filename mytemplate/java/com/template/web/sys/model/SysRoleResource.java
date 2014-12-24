//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import com.template.common.base.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysRoleResource extends Entity {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id <>

	
    private Long resourceId; //resource_id <>

	
    private Long roleId; //role_id <>



	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
    }

	public Long getResourceId() {
		return this.getLong("resourceId");
    }
   
    public void setResourceId(Long resourceId) {
		this.set("resourceId", resourceId);
    }

	public Long getRoleId() {
		return this.getLong("roleId");
    }
   
    public void setRoleId(Long roleId) {
		this.set("roleId", roleId);
    }


}
