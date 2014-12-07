//Powered By if, Since 2014 - 2020

package com.template.web.sys.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.template.common.base.Entity;


/**
 * 
 * @author 
 */

@SuppressWarnings({ "unused"})
public class SysUser extends Entity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id <用户Id>

    private Integer age; //age <年龄>

    private String area; //area <地区字符串>

    private String cityId; //city_id <城市id>

    private String code; //code <唯一键>

    private String gender; //gender <性别>

    private String name; //name <>

    private String password; //password <密码>

    private String phone; //phone <电话>

    private String provinceId; //province_id <省id>

    private Date regtime; //regtime <注册时间>

    private String salt; //salt <加密盐>

    private Integer status; //status <账号状态(0.正常 1.禁用)>

    private String username; //username <账号>



	public Long getId() {
		return this.getLong("id");
    }
   
    public void setId(Long id) {
		this.set("id", id);
    }

	public Integer getAge() {
		return this.getInteger("age");
    }
   
    public void setAge(Integer age) {
		this.set("age", age);
    }

	public String getArea() {
		return this.getString("area");
    }
   
    public void setArea(String area) {
		this.set("area", area);
    }

	public String getCityId() {
		return this.getString("cityId");
    }
   
    public void setCityId(String cityId) {
		this.set("cityId", cityId);
    }

	public String getCode() {
		return this.getString("code");
    }
   
    public void setCode(String code) {
		this.set("code", code);
    }

	public String getGender() {
		return this.getString("gender");
    }
   
    public void setGender(String gender) {
		this.set("gender", gender);
    }

	public String getName() {
		return this.getString("name");
    }
   
    public void setName(String name) {
		this.set("name", name);
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

	public String getProvinceId() {
		return this.getString("provinceId");
    }
   
    public void setProvinceId(String provinceId) {
		this.set("provinceId", provinceId);
    }

	public Date getRegtime() {
		return this.getDate("regtime");
    }
   
    public void setRegtime(Date regtime) {
		this.set("regtime", regtime);
    }

	public String getSalt() {
		return this.getString("salt");
    }
   
    public void setSalt(String salt) {
		this.set("salt", salt);
    }

	public Integer getStatus() {
		return this.getInteger("status");
    }
   
    public void setStatus(Integer status) {
		this.set("status", status);
    }

	public String getUsername() {
		return this.getString("username");
    }
   
    public void setUsername(String username) {
		this.set("username", username);
    }


}
