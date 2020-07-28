package com.dhcc.piccbid.entity.hospitalization;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * 诊疗明细表
 * 
 */
@Entity
@Table(name="T_PATIENT_COST_DETAIL")
public class CostdetailHos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_CODE")
	private String itemCode;
	
	@Column(name="ADMISSION_NO")
	private String admissionNo;

	@Column(name="BILLING_NO")
	private String billingNo;

	@Column(name="DRUG_TYPE")
	private String drugType;

	@Column(name="ID_NO")
	private String idNo;

	@Column(name="ITEM_COST")
	private BigDecimal itemCost;

	@Column(name="ITEM_NAME")
	private String itemName;

	@Column(name="ITEM_PRICE")
	private BigDecimal itemPrice;

	@Column(name="ITEM_QUANTITY")
	private BigDecimal itemQuantity;

	@Column(name="ITEM_STANDARD")
	private String itemStandard;

	@Column(name="ORG_CODE")
	private String orgCode;

	@Column(name="PATIENT_NAME")
	private String patientName;

	@Column(name="ILEGAL_HOSPITALIZATION")
	private String ilegalHospitalization;
	
	@Column(name="age")
	private Integer age;
	
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	private String caseNo;

	public CostdetailHos() {
		super();
	}
	
	/**
	 * @return the caseNo
	 */
	public String getCaseNo() {
		return caseNo;
	}

	/**
	 * @param caseNo the caseNo to set
	 */
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the admissionNo
	 */
	public String getAdmissionNo() {
		return admissionNo;
	}

	/**
	 * @param admissionNo the admissionNo to set
	 */
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	/**
	 * @return the billingNo
	 */
	public String getBillingNo() {
		return billingNo;
	}

	/**
	 * @param billingNo the billingNo to set
	 */
	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}

	/**
	 * @return the drugType
	 */
	public String getDrugType() {
		return drugType;
	}

	/**
	 * @param drugType the drugType to set
	 */
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return the itemCost
	 */
	public BigDecimal getItemCost() {
		return itemCost;
	}

	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemPrice
	 */
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the itemQuantity
	 */
	public BigDecimal getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(BigDecimal itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * @return the itemStandard
	 */
	public String getItemStandard() {
		return itemStandard;
	}

	/**
	 * @param itemStandard the itemStandard to set
	 */
	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the ilegalHospitalization
	 */
	public String getIlegalHospitalization() {
		return ilegalHospitalization;
	}

	/**
	 * @param ilegalHospitalization the ilegalHospitalization to set
	 */
	public void setIlegalHospitalization(String ilegalHospitalization) {
		this.ilegalHospitalization = ilegalHospitalization;
	}
	

}