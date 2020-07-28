package com.dhcc.piccbid.entity.documentCenter;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_PICCBID_MEDICAL_DETAIL database table.
 * 
 */
public class DocumentBasicVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userStatus;
	
	private String aduitStatus;
	
	private String gsStatus;
	
	
	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the aduitStatus
	 */
	public String getAduitStatus() {
		return aduitStatus;
	}

	/**
	 * @param aduitStatus the aduitStatus to set
	 */
	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	/**
	 * @return the gsStatus
	 */
	public String getGsStatus() {
		return gsStatus;
	}

	/**
	 * @param gsStatus the gsStatus to set
	 */
	public void setGsStatus(String gsStatus) {
		this.gsStatus = gsStatus;
	}

	@Id
	@Column(name="ID", unique=true, nullable=false, length=32)
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;
	
	@Column(name="DEDUCTIONS")
	private String deductions;
	
	@Column(name="WITHHOLDING_AMOUNT")
	private String withholdingAmount;

	@Column(name="APPEAL_RESULT")
	private String appealResult;
	
	@Column(name="WITHHOLDING_QUANTITY")
	private String withholdingQuantity;

	@Column(name="ADVICE_GROUP", length=32)
	private String adviceGroup;

	@Column(name="ADVICE_ID", length=32)
	private String adviceId;

	@Column(name="ADVICE_ORDER", length=32)
	private String adviceOrder;

	@Column(name="APPLY_PAY_AMOUNT", precision=22)
	private BigDecimal applyPayAmount;

	@Column(name="APPLY_PAY_LEVEL", length=32)
	private String applyPayLevel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BALANCE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date balanceDate;

	@Column(name="BILLING_NO", length=32)
	private String billingNo;

	@Column(name="CHARGE_TYPE", length=50)
	private String chargeType;

	@Column(name="CITY_CODE", length=32)
	private String cityCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date createDate;

	@Column(name="DELIVER_WAY", length=50)
	private String deliverWay;

	@Column(name="DEPART_CODE", length=60)
	private String departCode;

	@Column(name="DEPART_NAME", length=100)
	private String departName;

	@Column(name="DOC_CODE", length=50)
	private String docCode;

	@Column(name="DOC_NAME", length=50)
	private String docName;
	
	@Column(name="DOSAGE_DAYS", precision=22)
	private BigDecimal dosageDays;

	@Column(name="DOSE_FORM", length=32)
	private String doseForm;

	@Column(name="DOSE_UNIT", length=50)
	private String doseUnit;

	@Column(name="DRUG_MEA_UNIT", length=50)
	private String drugMeaUnit;

	@Column(name="DRUG_TYPE", length=20)
	private String drugType;

	@Column(name="DURG_DOSAGE", length=50)
	private String durgDosage;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FEE_CREATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date feeCreateDate;

	@Column(name="FEE_MEA_UNIT", length=50)
	private String feeMeaUnit;

	@Column(name="FULL_ORDINATION", precision=22)
	private BigDecimal fullOrdination;

	@Column(name="FULL_PAYMENT", precision=22)
	private BigDecimal fullPayment;

	@Column(name="FUND_COST", precision=12, scale=2)
	private BigDecimal fundCost;

	@Column(name="HANDDING_INS_CODE", length=32)
	private String handdingInsCode;

	@Column(name="HOS_SERVICE_NAME", length=100)
	private String hosServiceName;

	@Column(name="ILEGAL_TYPE", length=50)
	private String ilegalType;

	@Column(name="INS_COST", precision=12, scale=2)
	private BigDecimal insCost;

	@Column(name="IS_ILEGAL", length=50)
	private String isIlegal;

	@Column(name="IS_INSURANCE_PROJECT", length=32)
	private String isInsuranceProject;

	@Column(name="ITEM_CODE", length=32)
	private String itemCode;

	@Column(name="ITEM_COST", precision=22)
	private BigDecimal itemCost;

	@Column(name="ITEM_NAME", length=255)
	private String itemName;

	@Column(name="ITEM_NUM", precision=22)
	private BigDecimal itemNum;

	@Column(name="ITEM_PRICE", precision=22)
	private BigDecimal itemPrice;

	@Column(name="ITEM_STANDARD", length=20)
	private String itemStandard;

	@Column(name="ITEM_UNIT", length=30)
	private String itemUnit;

	@Column(name="LIMIT_PRICE", precision=22)
	private BigDecimal limitPrice;

	@Column(name="MEDICAL_CLOSE_NUMBER", length=32)
	private String medicalCloseNumber;

	@Column(name="MEDICAL_ID", length=32)
	private String medicalId;

	@Column(name="MEDICAL_INS_CODE", length=50)
	private String medicalInsCode;

	@Column(name="MEDICAL_INS_NAME", length=90)
	private String medicalInsName;

	@Column(name="NOT_INS_COST", precision=12, scale=2)
	private BigDecimal notInsCost;

	@Column(name="PARTIAL_ORDINATION", precision=22)
	private BigDecimal partialOrdination;

	@Column(name="PARTIAL_PAYMENT", precision=22)
	private BigDecimal partialPayment;

	@Column(name="PRESCRIPTION_DISCHARGE", length=10)
	private String prescriptionDischarge;

	@Column(name="PROJECT_TYPE", length=50)
	private String projectType;

	@Column(name="RECIPEL_ID", length=32)
	private String recipelId;

	@Column(name="SEE_DOC_CODE", length=32)
	private String seeDocCode;

	@Column(name="SELF_PAY_AMOUNT", precision=22)
	private BigDecimal selfPayAmount;

	@Column(name="SELF_PAY_RATE", length=10)
	private String selfPayRate;

	@Column(name="SINGLE_DOSE", length=50)
	private String singleDose;

	@Column(length=100)
	private String specs;

	@Column(name="SUM_AMOUNT", precision=22)
	private BigDecimal sumAmount;

	@Column(name="TAKE_FREQUENCE", length=50)
	private String takeFrequence;

	@Column(name="TOTAL_DRUG_INTAKE", precision=22)
	private BigDecimal totalDrugIntake;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date updateDate;

	@Column(name="USE_DAY", length=50)
	private String useDay;

	public DocumentBasicVo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdviceGroup() {
		return this.adviceGroup;
	}

	public void setAdviceGroup(String adviceGroup) {
		this.adviceGroup = adviceGroup;
	}

	public String getAdviceId() {
		return this.adviceId;
	}

	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}

	public String getAdviceOrder() {
		return this.adviceOrder;
	}

	public void setAdviceOrder(String adviceOrder) {
		this.adviceOrder = adviceOrder;
	}

	public BigDecimal getApplyPayAmount() {
		return this.applyPayAmount;
	}

	public void setApplyPayAmount(BigDecimal applyPayAmount) {
		this.applyPayAmount = applyPayAmount;
	}

	public String getApplyPayLevel() {
		return this.applyPayLevel;
	}

	public void setApplyPayLevel(String applyPayLevel) {
		this.applyPayLevel = applyPayLevel;
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

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDeliverWay() {
		return this.deliverWay;
	}

	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}

	public String getDepartCode() {
		return this.departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getDepartName() {
		return this.departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
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

	public BigDecimal getDosageDays() {
		return this.dosageDays;
	}

	public void setDosageDays(BigDecimal dosageDays) {
		this.dosageDays = dosageDays;
	}

	public String getDoseForm() {
		return this.doseForm;
	}

	public void setDoseForm(String doseForm) {
		this.doseForm = doseForm;
	}

	public String getDoseUnit() {
		return this.doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}

	public String getDrugMeaUnit() {
		return this.drugMeaUnit;
	}

	public void setDrugMeaUnit(String drugMeaUnit) {
		this.drugMeaUnit = drugMeaUnit;
	}

	public String getDrugType() {
		return this.drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getDurgDosage() {
		return this.durgDosage;
	}

	public void setDurgDosage(String durgDosage) {
		this.durgDosage = durgDosage;
	}

	public Date getFeeCreateDate() {
		return this.feeCreateDate;
	}

	public void setFeeCreateDate(Date feeCreateDate) {
		this.feeCreateDate = feeCreateDate;
	}

	public String getFeeMeaUnit() {
		return this.feeMeaUnit;
	}

	public void setFeeMeaUnit(String feeMeaUnit) {
		this.feeMeaUnit = feeMeaUnit;
	}

	public BigDecimal getFullOrdination() {
		return this.fullOrdination;
	}

	public void setFullOrdination(BigDecimal fullOrdination) {
		this.fullOrdination = fullOrdination;
	}

	public BigDecimal getFullPayment() {
		return this.fullPayment;
	}

	public void setFullPayment(BigDecimal fullPayment) {
		this.fullPayment = fullPayment;
	}

	public BigDecimal getFundCost() {
		return this.fundCost;
	}

	public void setFundCost(BigDecimal fundCost) {
		this.fundCost = fundCost;
	}

	public String getHanddingInsCode() {
		return this.handdingInsCode;
	}

	public void setHanddingInsCode(String handdingInsCode) {
		this.handdingInsCode = handdingInsCode;
	}

	public String getHosServiceName() {
		return this.hosServiceName;
	}

	public void setHosServiceName(String hosServiceName) {
		this.hosServiceName = hosServiceName;
	}

	public String getIlegalType() {
		return this.ilegalType;
	}

	public void setIlegalType(String ilegalType) {
		this.ilegalType = ilegalType;
	}

	public BigDecimal getInsCost() {
		return this.insCost;
	}

	public void setInsCost(BigDecimal insCost) {
		this.insCost = insCost;
	}

	public String getIsIlegal() {
		return this.isIlegal;
	}

	public void setIsIlegal(String isIlegal) {
		this.isIlegal = isIlegal;
	}

	public String getIsInsuranceProject() {
		return this.isInsuranceProject;
	}

	public void setIsInsuranceProject(String isInsuranceProject) {
		this.isInsuranceProject = isInsuranceProject;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public BigDecimal getItemCost() {
		return this.itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getItemStandard() {
		return this.itemStandard;
	}

	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}

	public String getItemUnit() {
		return this.itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public BigDecimal getLimitPrice() {
		return this.limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}

	public String getMedicalCloseNumber() {
		return this.medicalCloseNumber;
	}

	public void setMedicalCloseNumber(String medicalCloseNumber) {
		this.medicalCloseNumber = medicalCloseNumber;
	}

	public String getMedicalId() {
		return this.medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

	public String getMedicalInsCode() {
		return this.medicalInsCode;
	}

	public void setMedicalInsCode(String medicalInsCode) {
		this.medicalInsCode = medicalInsCode;
	}

	public String getMedicalInsName() {
		return this.medicalInsName;
	}
	

	public String getAppealResult() {
		return appealResult;
	}

	public void setAppealResult(String appealResult) {
		this.appealResult = appealResult;
	}

	public void setMedicalInsName(String medicalInsName) {
		this.medicalInsName = medicalInsName;
	}

	public BigDecimal getNotInsCost() {
		return this.notInsCost;
	}

	public void setNotInsCost(BigDecimal notInsCost) {
		this.notInsCost = notInsCost;
	}

	public BigDecimal getPartialOrdination() {
		return this.partialOrdination;
	}

	public void setPartialOrdination(BigDecimal partialOrdination) {
		this.partialOrdination = partialOrdination;
	}

	public BigDecimal getPartialPayment() {
		return this.partialPayment;
	}

	public void setPartialPayment(BigDecimal partialPayment) {
		this.partialPayment = partialPayment;
	}

	public String getPrescriptionDischarge() {
		return this.prescriptionDischarge;
	}

	public void setPrescriptionDischarge(String prescriptionDischarge) {
		this.prescriptionDischarge = prescriptionDischarge;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getRecipelId() {
		return this.recipelId;
	}

	public void setRecipelId(String recipelId) {
		this.recipelId = recipelId;
	}

	public String getSeeDocCode() {
		return this.seeDocCode;
	}

	public void setSeeDocCode(String seeDocCode) {
		this.seeDocCode = seeDocCode;
	}

	public BigDecimal getSelfPayAmount() {
		return this.selfPayAmount;
	}

	public void setSelfPayAmount(BigDecimal selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	public String getSelfPayRate() {
		return this.selfPayRate;
	}

	public void setSelfPayRate(String selfPayRate) {
		this.selfPayRate = selfPayRate;
	}

	public String getSingleDose() {
		return this.singleDose;
	}

	public void setSingleDose(String singleDose) {
		this.singleDose = singleDose;
	}

	public String getSpecs() {
		return this.specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public BigDecimal getSumAmount() {
		return this.sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getTakeFrequence() {
		return this.takeFrequence;
	}

	public void setTakeFrequence(String takeFrequence) {
		this.takeFrequence = takeFrequence;
	}

	public String getDeductions() {
		return deductions;
	}

	public void setDeductions(String deductions) {
		this.deductions = deductions;
	}

	public String getWithholdingAmount() {
		return withholdingAmount;
	}

	public void setWithholdingAmount(String withholdingAmount) {
		this.withholdingAmount = withholdingAmount;
	}

	public String getWithholdingQuantity() {
		return withholdingQuantity;
	}

	public void setWithholdingQuantity(String withholdingQuantity) {
		this.withholdingQuantity = withholdingQuantity;
	}

	public BigDecimal getTotalDrugIntake() {
		return this.totalDrugIntake;
	}

	public void setTotalDrugIntake(BigDecimal totalDrugIntake) {
		this.totalDrugIntake = totalDrugIntake;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUseDay() {
		return this.useDay;
	}

	public void setUseDay(String useDay) {
		this.useDay = useDay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DocumentBasicVo [userStatus=" + userStatus + ", aduitStatus=" + aduitStatus + ", gsStatus=" + gsStatus
				+ ", id=" + id + ", deductions=" + deductions + ", withholdingAmount=" + withholdingAmount
				+ ", appealResult=" + appealResult + ", withholdingQuantity=" + withholdingQuantity + ", adviceGroup="
				+ adviceGroup + ", adviceId=" + adviceId + ", adviceOrder=" + adviceOrder + ", applyPayAmount="
				+ applyPayAmount + ", applyPayLevel=" + applyPayLevel + ", balanceDate=" + balanceDate + ", billingNo="
				+ billingNo + ", chargeType=" + chargeType + ", cityCode=" + cityCode + ", createDate=" + createDate
				+ ", deliverWay=" + deliverWay + ", departCode=" + departCode + ", departName=" + departName
				+ ", docCode=" + docCode + ", docName=" + docName + ", dosageDays=" + dosageDays + ", doseForm="
				+ doseForm + ", doseUnit=" + doseUnit + ", drugMeaUnit=" + drugMeaUnit + ", drugType=" + drugType
				+ ", durgDosage=" + durgDosage + ", feeCreateDate=" + feeCreateDate + ", feeMeaUnit=" + feeMeaUnit
				+ ", fullOrdination=" + fullOrdination + ", fullPayment=" + fullPayment + ", fundCost=" + fundCost
				+ ", handdingInsCode=" + handdingInsCode + ", hosServiceName=" + hosServiceName + ", ilegalType="
				+ ilegalType + ", insCost=" + insCost + ", isIlegal=" + isIlegal + ", isInsuranceProject="
				+ isInsuranceProject + ", itemCode=" + itemCode + ", itemCost=" + itemCost + ", itemName=" + itemName
				+ ", itemNum=" + itemNum + ", itemPrice=" + itemPrice + ", itemStandard=" + itemStandard + ", itemUnit="
				+ itemUnit + ", limitPrice=" + limitPrice + ", medicalCloseNumber=" + medicalCloseNumber
				+ ", medicalId=" + medicalId + ", medicalInsCode=" + medicalInsCode + ", medicalInsName="
				+ medicalInsName + ", notInsCost=" + notInsCost + ", partialOrdination=" + partialOrdination
				+ ", partialPayment=" + partialPayment + ", prescriptionDischarge=" + prescriptionDischarge
				+ ", projectType=" + projectType + ", recipelId=" + recipelId + ", seeDocCode=" + seeDocCode
				+ ", selfPayAmount=" + selfPayAmount + ", selfPayRate=" + selfPayRate + ", singleDose=" + singleDose
				+ ", specs=" + specs + ", sumAmount=" + sumAmount + ", takeFrequence=" + takeFrequence
				+ ", totalDrugIntake=" + totalDrugIntake + ", updateDate=" + updateDate + ", useDay=" + useDay + "]";
	}

	

}