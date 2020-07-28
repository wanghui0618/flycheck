package com.dhcc.piccbid.entity.abnormalHospitalStay;

import java.io.Serializable;

public class AbnormalHospitalStayexcle implements Serializable{

	private String code;
	private String indate;
	private String outdate;
	private String hospitalName;
	private String admissionDiseaseName;
	private String paydate;
	private String zyts;
	
	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}
	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getZyts() {
		return zyts;
	}
	public void setZyts(String zyts) {
		this.zyts = zyts;
	}
}
