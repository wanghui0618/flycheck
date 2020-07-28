package com.dhcc.piccbid.entity.hospitalviolation;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_PICCBID_HOSPITAL_VIOLATION database table.
 * 
 */

public class HospitalViolationVo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String id;

	
	private String cityName;


	private String orgName;
	
	private String orgCode;

	
	private String vioCount;

	private Integer sumCount;
	
	private String susCount;
	
	private String vioMoney;
	
	private String susMoney;
	
	
	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public HospitalViolationVo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getVioCount() {
		return this.vioCount;
	}

	public void setVioCount(String vioCount) {
		this.vioCount = vioCount;
	}

	public String getSusCount() {
		return susCount;
	}

	public void setSusCount(String susCount) {
		this.susCount = susCount;
	}

	public String getVioMoney() {
		return vioMoney;
	}

	public void setVioMoney(String vioMoney) {
		this.vioMoney = vioMoney;
	}

	public String getSusMoney() {
		return susMoney;
	}

	public void setSusMoney(String susMoney) {
		this.susMoney = susMoney;
	}
	
	

}