package com.dhcc.piccbid.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="T_PICCBID_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", unique=true, nullable=false, length=32)
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;
	
	@Column(name="PHONE")
	private String phone;

	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="ADD_DATE")
	private Date addDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="UPDATE_DATE")
	private Date updateDate;
	
	@Column(name="ROLE_ID")
	private String roleId;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="LOGIN_NAME")
	private String loginName;
	
	@Column(name="ROLE_ID_CHANGE")
	private String roleIdChange;
	
	@Column(name="USER_CHANGE")
	private String userChange;

	@Column(name="UNIT_ID")
	private String unitId;
	
	@Column(name="UNIT_TYPE")
	private String unitType;
	
	@Column(name="PHOTO")
	private String photo;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**  
	* @return id 
	*/
	public String getId() {
		return id;
	}

	/**  
	* @param id id 
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**  
	* @return phone 
	*/
	public String getPhone() {
		return phone;
	}

	/**  
	* @param phone phone 
	*/
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**  
	* @return password 
	*/
	public String getPassword() {
		return password;
	}

	/**  
	* @param password password 
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	/**  
	* @return name 
	*/
	public String getName() {
		return name;
	}

	/**  
	* @param name name 
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**  
	* @return addDate 
	*/
	public Date getAddDate() {
		return addDate;
	}

	/**  
	* @param addDate addDate 
	*/
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	/**  
	* @return updateDate 
	*/
	public Date getUpdateDate() {
		return updateDate;
	}

	/**  
	* @param updateDate updateDate 
	*/
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	

	public String getRoleIdChange() {
		return roleIdChange;
	}

	public void setRoleIdChange(String roleIdChange) {
		this.roleIdChange = roleIdChange;
	}

	public String getUserChange() {
		return userChange;
	}

	public void setUserChange(String userChange) {
		this.userChange = userChange;
	}

	/**  
	* @return unitType 
	*/
	public String getUnitType() {
		return unitType;
	}

	/**  
	* @param unitType unitType 
	*/
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

}