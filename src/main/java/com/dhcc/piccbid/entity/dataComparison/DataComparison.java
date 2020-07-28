package com.dhcc.piccbid.entity.dataComparison;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DataComparison {
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	private String orgCode1;
	private String orgCode2;
	private String orgName1;
	private String orgName2;
	private String diagType;
	private String balanceDate;
	private String totalAmount;
	private String hospitalId;
	private String hospitalName;
	private String bmiPayAmount;
	private String personTimes;
	private String personNum;
	private String eachCost;
	private String avgDays;
	private String result;
	private String year;
	private String sum;
	private String personType;//人群类型
	private String admission_disease_name;

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getAdmission_disease_name() {
		return admission_disease_name;
	}

	public void setAdmission_disease_name(String admission_disease_name) {
		this.admission_disease_name = admission_disease_name;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAvgDays() {
		return avgDays;
	}

	public void setAvgDays(String avgDays) {
		this.avgDays = avgDays;
	}

	public String getEachCost() {
		return eachCost;
	}

	public void setEachCost(String eachCost) {
		this.eachCost = eachCost;
	}

	public String getPersonTimes() {
		return personTimes;
	}

	public void setPersonTimes(String personTimes) {
		this.personTimes = personTimes;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getBmiPayAmount() {
		return bmiPayAmount;
	}

	public void setBmiPayAmount(String bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getDiagType() {
		return diagType;
	}

	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}

	public String getOrgCode1() {
		return orgCode1;
	}

	public void setOrgCode1(String orgCode1) {
		this.orgCode1 = orgCode1;
	}

	public String getOrgCode2() {
		return orgCode2;
	}

	public void setOrgCode2(String orgCode2) {
		this.orgCode2 = orgCode2;
	}

	public String getOrgName1() {
		return orgName1;
	}

	public void setOrgName1(String orgName1) {
		this.orgName1 = orgName1;
	}

	public String getOrgName2() {
		return orgName2;
	}

	public void setOrgName2(String orgName2) {
		this.orgName2 = orgName2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
