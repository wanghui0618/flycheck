package com.dhcc.piccbid.entity.flyDrug;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="T_FLY_MEDICAL_DETAIL")
public class FlyDrug implements Serializable{
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

		public Date getBalanceDate() {
			return balanceDate;
		}

		public void setBalanceDate(Date balanceDate) {
			this.balanceDate = balanceDate;
		}

		public String getBillingNo() {
			return billingNo;
		}

		public void setBillingNo(String billingNo) {
			this.billingNo = billingNo;
		}

		public String getChargeType() {
			return chargeType;
		}

		public void setChargeType(String chargeType) {
			this.chargeType = chargeType;
		}

		public String getDepart() {
			return depart;
		}

		public void setDepart(String depart) {
			this.depart = depart;
		}

		public String getDiagType() {
			return diagType;
		}

		public void setDiagType(String diagType) {
			this.diagType = diagType;
		}

		public String getDocCode() {
			return docCode;
		}

		public void setDocCode(String docCode) {
			this.docCode = docCode;
		}

		public String getDocName() {
			return docName;
		}

		public void setDocName(String docName) {
			this.docName = docName;
		}

		public String getDoseUnit() {
			return doseUnit;
		}

		public void setDoseUnit(String doseUnit) {
			this.doseUnit = doseUnit;
		}

		public String getFeeGrade() {
			return feeGrade;
		}

		public void setFeeGrade(String feeGrade) {
			this.feeGrade = feeGrade;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getInsuranceCode() {
			return insuranceCode;
		}

		public void setInsuranceCode(String insuranceCode) {
			this.insuranceCode = insuranceCode;
		}

		public String getItemCodeHos() {
			return itemCodeHos;
		}

		public void setItemCodeHos(String itemCodeHos) {
			this.itemCodeHos = itemCodeHos;
		}

		public String getItemCodeIns() {
			return itemCodeIns;
		}

		public void setItemCodeIns(String itemCodeIns) {
			this.itemCodeIns = itemCodeIns;
		}

		public BigDecimal getItemCost() {
			return itemCost;
		}

		public void setItemCost(BigDecimal itemCost) {
			this.itemCost = itemCost;
		}

		public String getItemNameHos() {
			return itemNameHos;
		}

		public void setItemNameHos(String itemNameHos) {
			this.itemNameHos = itemNameHos;
		}

		public String getItemNameIns() {
			return itemNameIns;
		}

		public void setItemNameIns(String itemNameIns) {
			this.itemNameIns = itemNameIns;
		}

		public BigDecimal getItemNum() {
			return itemNum;
		}

		public void setItemNum(BigDecimal itemNum) {
			this.itemNum = itemNum;
		}

		public BigDecimal getItemPrice() {
			return itemPrice;
		}

		public void setItemPrice(BigDecimal itemPrice) {
			this.itemPrice = itemPrice;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getOrgCode() {
			return orgCode;
		}

		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}

		public Date getPreDate() {
			return preDate;
		}

		public void setPreDate(Date preDate) {
			this.preDate = preDate;
		}

		public String getRecipelId() {
			return recipelId;
		}

		public void setRecipelId(String recipelId) {
			this.recipelId = recipelId;
		}

		public String getSpecs() {
			return specs;
		}

		public void setSpecs(String specs) {
			this.specs = specs;
		}
}
