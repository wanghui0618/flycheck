package com.dhcc.piccbid.entity.violationdetails;

import java.io.Serializable;
/*
 * 用于违规医院分布实体类
 */
public class HospitalSpread implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orgName;
	private String cityName;
	private String coName;
	private String countNum;
	private String orgCode;
	
	
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCountNum() {
		return countNum;
	}
	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}
	
	
}
