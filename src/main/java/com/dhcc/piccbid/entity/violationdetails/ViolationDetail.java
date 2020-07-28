package com.dhcc.piccbid.entity.violationdetails;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_PICCBID_VIOLATION_DETAILS database table.
 * 
 */
@Entity
@Table(name="T_PICCBID_VIOLATION_DETAILS")
public class ViolationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;

	@Column(name="MEDICAL_DETAIL_ID")
	private String medicalDetailId;

	@Column(name="MEDICAL_ID")
	private String medicalId;

	@Column(name="RETURN_CODE")
	private String returnCode;

	@Column(name="RETURN_DESC")
	private String returnDesc;

	@Column(name="RETURN_NAME")
	private String returnName;

	@Column(name="TYPE_NAME")
	private String typeName;

	@Column(name="TYPE_NO")
	private String typeNo;
	
	@Column(name="ITEM_CODE")
	private String itemCode;
	private String itemName;
	private String itemCost;
	private String countDetail;
	private String coName;
	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCost() {
		return itemCost;
	}

	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}

	public String getCountDetail() {
		return countDetail;
	}

	public void setCountDetail(String countDetail) {
		this.countDetail = countDetail;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public ViolationDetail() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicalDetailId() {
		return this.medicalDetailId;
	}

	public void setMedicalDetailId(String medicalDetailId) {
		this.medicalDetailId = medicalDetailId;
	}

	public String getMedicalId() {
		return this.medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

	public String getReturnCode() {
		return this.returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnDesc() {
		return this.returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public String getReturnName() {
		return this.returnName;
	}

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeNo() {
		return this.typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

}