/**
 * 
 */
package com.dhcc.piccbid.entity.hospitalization;

import java.io.Serializable;

/**
 * 限住院违规项目查询
 * @author DongHuan
 *
 */
public class Hospitalization implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseNo;
	
	private String orgCode;
	
	private String admissionType;
	
	private String idNo;
	
	private String admissionNo;

	private String billingNo;

	private String patientName;
	
	private String itemName;
	
	private String itemCode;
	
	private String ilegalHospitalization;
	
	private String comments;
	
	private String commentsCodex;
	//类别
	private String xmlb;
	//年龄
	private String age;
	//诊断
	private String condition;
	
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * 
	 */
	public Hospitalization() {
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
	 * @return the admissionType
	 */
	public String getAdmissionType() {
		return admissionType;
	}

	/**
	 * @param admissionType the admissionType to set
	 */
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
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

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the commentsCodex
	 */
	public String getCommentsCodex() {
		return commentsCodex;
	}

	/**
	 * @param commentsCodex the commentsCodex to set
	 */
	public void setCommentsCodex(String commentsCodex) {
		this.commentsCodex = commentsCodex;
	}

	/**
	 * @return the xmlb
	 */
	public String getXmlb() {
		return xmlb;
	}

	/**
	 * @param xmlb the xmlb to set
	 */
	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}
	
}
