package com.gzpy.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "gzpy_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6333698545673663267L;
	
	@Id
	@Column(name = "userId")
	private String userId;
	@Column(name = "loginName")
	private String loginName;
	@Column(name = "userName")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "sex")
	private String sex;
	@Column(name = "employDate")
	private Date employDate;
	@Column(name = "email")
	private String email;
	@Column(name = "officePhone")
	private String officePhone;
	@Column(name = "homePhone")
	private String homePhone;
	@Column(name = "mobilePhone")
	private String mobilePhone;
	@Column(name = "delStatus")
	private String delStatus;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
