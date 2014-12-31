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
public class SysRoleOffice extends Entity {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator="JDBC")
    private Long id; //id <>

	
    private Long officeId; //office_id <机构编号>

	
    private Long roleId; //role_id <角色编号>



	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
    }

	public Long getOfficeId() {
		return this.getLong("officeId");
    }
   
    public void setOfficeId(Long officeId) {
		this.set("officeId", officeId);
    }

	public Long getRoleId() {
		return this.getLong("roleId");
    }
   
    public void setRoleId(Long roleId) {
		this.set("roleId", roleId);
    }


}
