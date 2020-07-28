package com.dhcc.piccbid.entity.flyMedicalDetail;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="T_FLY_MEDICAL_DETAIL")
public class FlyMedicalDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BALANCE_DATE")
	private Date balanceDate;

	@Column(name="BILLING_NO", length=100)
	private String billingNo;

	@Column(name="CHARGE_TYPE", length=100)
	private String chargeType;

	@Column(length=100)
	private String depart;

	@Column(name="DIAG_TYPE", length=100)
	private String diagType;

	@Column(name="DOC_CODE", length=100)
	private String docCode;

	@Column(name="DOC_NAME", length=100)
	private String docName;

	@Column(name="DOSE_UNIT", length=100)
	private String doseUnit;

	@Column(name="FEE_GRADE", length=100)
	private String feeGrade;
	@Id
	@Column(length=32)
	private String id;

	@Column(name="INSURANCE_CODE", length=100)
	private String insuranceCode;

	@Column(name="ITEM_CODE_HOS", length=100)
	private String itemCodeHos;

	@Column(name="ITEM_CODE_INS", length=100)
	private String itemCodeIns;

	@Column(name="ITEM_COST", precision=22)
	private BigDecimal itemCost;

	@Column(name="ITEM_NAME_HOS", length=100)
	private String itemNameHos;

	@Column(name="ITEM_NAME_INS", length=100)
	private String itemNameIns;

	@Column(name="ITEM_NUM", precision=22)
	private BigDecimal itemNum;

	@Column(name="ITEM_PRICE", precision=22)
	private BigDecimal itemPrice;

	@Column(length=100)
	private String name;

	@Column(name="ORG_CODE", length=100)
	private String orgCode;

	@Temporal(TemporalType.DATE)
	@Column(name="PRE_DATE")
	private Date preDate;

	@Column(name="RECIPEL_ID", length=100)
	private String recipelId;

	@Column(length=100)
	private String specs;

	public FlyMedicalDetail() {
	}

	public Date getBalanceDate() {
		return this.balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getBillingNo() {
		return this.billingNo;
	}

	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getDepart() {
		return this.depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getDiagType() {
		return this.diagType;
	}

	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}

	public String getDocCode() {
		return this.docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDoseUnit() {
		return this.doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}

	public String getFeeGrade() {
		return this.feeGrade;
	}

	public void setFeeGrade(String feeGrade) {
		this.feeGrade = feeGrade;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInsuranceCode() {
		return this.insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getItemCodeHos() {
		return this.itemCodeHos;
	}

	public void setItemCodeHos(String itemCodeHos) {
		this.itemCodeHos = itemCodeHos;
	}

	public String getItemCodeIns() {
		return this.itemCodeIns;
	}

	public void setItemCodeIns(String itemCodeIns) {
		this.itemCodeIns = itemCodeIns;
	}

	public BigDecimal getItemCost() {
		return this.itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemNameHos() {
		return this.itemNameHos;
	}

	public void setItemNameHos(String itemNameHos) {
		this.itemNameHos = itemNameHos;
	}

	public String getItemNameIns() {
		return this.itemNameIns;
	}

	public void setItemNameIns(String itemNameIns) {
		this.itemNameIns = itemNameIns;
	}

	public BigDecimal getItemNum() {
		return this.itemNum;
	}

	public void setItemNum(BigDecimal itemNum) {
		this.itemNum = itemNum;
	}

	public BigDecimal getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Date getPreDate() {
		return this.preDate;
	}

	public void setPreDate(Date preDate) {
		this.preDate = preDate;
	}

	public String getRecipelId() {
		return this.recipelId;
	}

	public void setRecipelId(String recipelId) {
		this.recipelId = recipelId;
	}

	public String getSpecs() {
		return this.specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

}