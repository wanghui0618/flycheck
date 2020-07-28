package com.dhcc.piccbid.entity.documentCenter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class MedicalId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String sex;
	
	private String idcard;
	
	private String sscno;
	
	private String billingNo;
	
	private String handdingInsName;
	
	private String orgName;
	
	private String orgCode;
	
	private String visitingCardNumber;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="INHOS_DATE")
	private  Date inhosDate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="OUTHOS_DATE")
	private Date outhosDate;
	
    private String stayLength;
    
    private String diagType;
    
    private String inDiagnosisName;
    
    private String outDiagnosisName;
    
    private String departName;
    
    private String doctorName;
    
	private String sumAmount;
	
	private String itemCost;
	
	private String itemPrice;
	
	private String applyPayAmount;
	
	private String selfPayAmount;
	
	private String fullOrdination;
	
	private String partialOrdination;
	
	private String partialPayment;
	
	private String fullPayment;
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getSscno() {
		return sscno;
	}

	public void setSscno(String sscno) {
		this.sscno = sscno;
	}

	public String getBillingNo() {
		return billingNo;
	}

	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}

	public String getHanddingInsName() {
		return handdingInsName;
	}

	public void setHanddingInsName(String handdingInsName) {
		this.handdingInsName = handdingInsName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getVisitingCardNumber() {
		return visitingCardNumber;
	}

	public void setVisitingCardNumber(String visitingCardNumber) {
		this.visitingCardNumber = visitingCardNumber;
	}

	public Date getInhosDate() {
		return inhosDate;
	}

	public void setInhosDate(Date inhosDate) {
		this.inhosDate = inhosDate;
	}

	public Date getOuthosDate() {
		return outhosDate;
	}

	public void setOuthosDate(Date outhosDate) {
		this.outhosDate = outhosDate;
	}

	public String getStayLength() {
		return stayLength;
	}

	public void setStayLength(String stayLength) {
		this.stayLength = stayLength;
	}

	public String getDiagType() {
		return diagType;
	}

	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}

	public String getInDiagnosisName() {
		return inDiagnosisName;
	}

	public void setInDiagnosisName(String inDiagnosisName) {
		this.inDiagnosisName = inDiagnosisName;
	}

	public String getOutDiagnosisName() {
		return outDiagnosisName;
	}

	public void setOutDiagnosisName(String outDiagnosisName) {
		this.outDiagnosisName = outDiagnosisName;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getItemCost() {
		return itemCost;
	}

	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getApplyPayAmount() {
		return applyPayAmount;
	}

	public void setApplyPayAmount(String applyPayAmount) {
		this.applyPayAmount = applyPayAmount;
	}

	public String getSelfPayAmount() {
		return selfPayAmount;
	}

	public void setSelfPayAmount(String selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	public String getFullOrdination() {
		return fullOrdination;
	}

	public void setFullOrdination(String fullOrdination) {
		this.fullOrdination = fullOrdination;
	}

	public String getPartialOrdination() {
		return partialOrdination;
	}

	public void setPartialOrdination(String partialOrdination) {
		this.partialOrdination = partialOrdination;
	}

	public String getPartialPayment() {
		return partialPayment;
	}

	public void setPartialPayment(String partialPayment) {
		this.partialPayment = partialPayment;
	}

	public String getFullPayment() {
		return fullPayment;
	}

	public void setFullPayment(String fullPayment) {
		this.fullPayment = fullPayment;
	}
	
	
    
    
    
    
    
    
    
    
	
	
	
	
	
	
	
	
}
