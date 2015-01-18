//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import java.util.Date;
import java.util.List;

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
@Table(name="sys_user")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	
    private Long companyId; //company_id <归属公司>

	
    private String email; //email <邮箱>

	
    private Date loginDate; //login_date <最后登陆时间>

	
    private String loginIp; //login_ip <最后登陆IP>

	
    private String mobile; //mobile <手机>

	
    private String name; //name <姓名>

	
    private String no; //no <工号>

	
    private Long officeId; //office_id <归属部门>

	
    private String password; //password <密码>

	
    private String phone; //phone <电话>

	
    private String remarks; //remarks <备注信息>

	
    private String username; //username <登录名>

	
    private String userType; //user_type <用户类型>
    
    @Transient
    private Long[] roleIds; //角色
    @Transient
    private long[] posSum; //权限组


    public void setRoleIds(Long[] roleIds){
    	this.set("roleIds", roleIds);
    }
    
    public Long[] getRoleIds(){
    	return (Long[])this.get("roleIds");
    }

	public Long getCompanyId() {
		return this.getLong("companyId");
    }
   
    public void setCompanyId(Long companyId) {
		this.set("companyId", companyId);
    }


	public String getEmail() {
		return this.getString("email");
    }
   
    public void setEmail(String email) {
		this.set("email", email);
    }

	public Date getLoginDate() {
		return this.getDate("loginDate");
    }
   
    public void setLoginDate(Date loginDate) {
		this.set("loginDate", loginDate);
    }

	public String getLoginIp() {
		return this.getString("loginIp");
    }
   
    public void setLoginIp(String loginIp) {
		this.set("loginIp", loginIp);
    }

	public String getMobile() {
		return this.getString("mobile");
    }
   
    public void setMobile(String mobile) {
		this.set("mobile", mobile);
    }

	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
		this.set("name", name);
    }

	public String getNo() {
		return this.getString("no");
    }
   
    public void setNo(String no) {
		this.set("no", no);
    }

	public Long getOfficeId() {
		return this.getLong("officeId");
    }
   
    public void setOfficeId(Long officeId) {
		this.set("officeId", officeId);
    }

	public String getPassword() {
		return this.getString("password");
    }
   
    public void setPassword(String password) {
		this.set("password", password);
    }

	public String getPhone() {
		return this.getString("phone");
    }
   
    public void setPhone(String phone) {
		this.set("phone", phone);
    }

	public String getRemarks() {
		return this.getString("remarks");
    }
   
    public void setRemarks(String remarks) {
		this.set("remarks", remarks);
    }


	public String getUsername() {
		return this.getString("username");
    }
   
    public void setUsername(String username) {
		this.set("username", username);
    }

	public String getUserType() {
		return this.getString("userType");
    }
   
    public void setUserType(String userType) {
		this.set("userType", userType);
    }
    
    public long[] getPosSum() {
		return (long[]) this.get("posSum");
    }
   
    public void setPosSum(long[] posSum) {
		this.set("posSum", posSum);
    }

    /**
	 * 计算用户权限总和
	 */
    public void calculatePermissionSum(List<SysResource> userResources){
    	int pos = 0;
    	long code = 0;
    	for(SysResource res : userResources){
    		if(res!=null){
    			pos = res.getPos();
        		code = res.getCode();
        		long[] posSum = this.getPosSum();
        		posSum[pos] = posSum[pos] | code;
        		this.setPosSum(posSum); //与运算
    		}
    	}
    	
    }
    
    public static void main(String[] args) {
		long[] test = new long[2];
		System.out.println(test[1] | 1);
		System.out.println(test.toString());
	}

}
