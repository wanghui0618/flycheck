package com.dhcc.piccbid.entity.admin;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


public class superviseRuleIndex implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String ruleName;
	
	private String moneyNumber;
	
	private String provinceName;
	
	private int totalNumber;
	
	private int personNumber;
	
	private String  pnumber;
	
	private String  pname;
	
	private String  pdate;
	
	private float  cvNumber;



	public float getCvNumber() {
		return cvNumber;
	}

	public void setCvNumber(float cvNumber) {
		this.cvNumber = cvNumber;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getMoneyNumber() {
		return moneyNumber;
	}

	public void setMoneyNumber(String moneyNumber) {
		this.moneyNumber = moneyNumber;
	}
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}
	
	
	


}
