package com.dhcc.piccbid.entity.hospitalviolation;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_PICCBID_HOSPITAL_VIOLATION database table.
 * 
 */
@Entity
@Table(name="T_PICCBID_HOSPITAL_VIOLATION")
public class HospitalViolation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="CITY_NAME")
	private String cityName;

	@Column(name="ORG_NAME")
	private String orgName;
	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="VIO_COUNT")
	private String vioCount;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public HospitalViolation() {
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

}