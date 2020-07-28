package com.dhcc.piccbid.entity.medicaldetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MedicalDetailVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String itemId;
	private String status;
	private String itemIdStatus;//item_id_STATUS
	private String itemIdIsExist;//item_id_is_exist
	private String examineComments;//EXAMINE_COMMENTS
	private String countTime;//count_time
	private String adviceId;
	private String violationStatus;
	private String userStatus;
	private String appealResult;
	private BigDecimal applyPayAmount;

	private String applyPayLevel;

	
	private String billingNo;

	
	private String chargeType;

	
	private String deliverWay;

	
	private String doseForm;

	
	private String doseUnit;

	
	private String drugType;

	
	private Date feeCreateDate;

	
	private BigDecimal fullOrdination;


	private BigDecimal fullPayment;

	
	private String ilegalType;

	
	private String isIlegal;

	
	private String isInsuranceProject;

	
	private String itemCode;

	
	private BigDecimal itemCost;

	
	private String itemName;

	
	private BigDecimal itemNum;

	
	private BigDecimal itemPrice;

	
	private String itemStandard;

	
	private BigDecimal limitPrice;

	
	private String medicalId;

	
	private BigDecimal partialOrdination;

	
	private BigDecimal partialPayment;

	
	private String recipelId;

	
	private BigDecimal selfPayAmount;

	
	private String singleDose;

	
	private BigDecimal sumAmount;
	
	private String projectType;
	
	private String takeFrequence;
	
	private String specs;
	
	private BigDecimal totalDrugIntake;
	
	private BigDecimal dosageDays;
	
	private String prescriptionDischarge;
	
	private String medicalInsCode;
	
	private String medicalInsName;
	
	private String departCode;
	
	private String departName;
	
	private String docCode;
	
	private String docName;
	
	private String drugMeaUnit;
	
	private String feeMeaUnit;
	
	private String hosServiceName;
	
	private Date balanceDate;
	
	private BigDecimal insCost;
	
	private BigDecimal fundCost;
	
	private BigDecimal notInsCost;
	
	private String durgDosage;
	
	private String itemUnit;
	
	private String adviceGroup;
	
	private String adviceOrder;
	
	private String selfPayRate;
	
	private Date createDate;
	
	
	
	private String useDay;
	
	private String aduitStatus;
	private String sysStatus;
	
	private String createUserName;
	
	private String name;
	
	private String orgName;
	
	private String sex;
	
	private String age;
	
	private Date inhosDate;
	
	private Date outhosDate;
	
	private String admissionNo;
	
	private String medical;
	
	private String treatmentProject;
	
	private String consumableMaterial;
	
	private String medicalCost;
	
	private String treatmentProjectCost;
	
	private String consumableMaterialCost;
	
	private String treatmentCost;
	private String inspectionCost;
	private String inspectionFee;
	private String laboratoryFee;
	private String operationCost;
	private String bloodChargesCost;
	private String western;
	private String chinesePatentCost;
	private String chineseMedicineCost;
	private String material;
	private String medicalServiceCost;
	private String other;
	
	

	public String getInspectionFee() {
		return inspectionFee;
	}


	public void setInspectionFee(String inspectionFee) {
		this.inspectionFee = inspectionFee;
	}


	public String getLaboratoryFee() {
		return laboratoryFee;
	}


	public void setLaboratoryFee(String laboratoryFee) {
		this.laboratoryFee = laboratoryFee;
	}


	public String getWestern() {
		return western;
	}


	public void setWestern(String western) {
		this.western = western;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public String getTreatmentCost() {
		return treatmentCost;
	}


	public void setTreatmentCost(String treatmentCost) {
		this.treatmentCost = treatmentCost;
	}




	public String getChineseMedicineCost() {
		return chineseMedicineCost;
	}


	public void setChineseMedicineCost(String chineseMedicineCost) {
		this.chineseMedicineCost = chineseMedicineCost;
	}


	public String getChinesePatentCost() {
		return chinesePatentCost;
	}


	public void setChinesePatentCost(String chinesePatentCost) {
		this.chinesePatentCost = chinesePatentCost;
	}




	public String getInspectionCost() {
		return inspectionCost;
	}


	public void setInspectionCost(String inspectionCost) {
		this.inspectionCost = inspectionCost;
	}




	public String getBloodChargesCost() {
		return bloodChargesCost;
	}


	public void setBloodChargesCost(String bloodChargesCost) {
		this.bloodChargesCost = bloodChargesCost;
	}


	public String getMedicalServiceCost() {
		return medicalServiceCost;
	}


	public void setMedicalServiceCost(String medicalServiceCost) {
		this.medicalServiceCost = medicalServiceCost;
	}


	public String getOperationCost() {
		return operationCost;
	}


	public void setOperationCost(String operationCost) {
		this.operationCost = operationCost;
	}




	public String getMedicalCost() {
		return medicalCost;
	}


	public void setMedicalCost(String medicalCost) {
		this.medicalCost = medicalCost;
	}


	public String getTreatmentProjectCost() {
		return treatmentProjectCost;
	}


	public void setTreatmentProjectCost(String treatmentProjectCost) {
		this.treatmentProjectCost = treatmentProjectCost;
	}


	public String getConsumableMaterialCost() {
		return consumableMaterialCost;
	}


	public void setConsumableMaterialCost(String consumableMaterialCost) {
		this.consumableMaterialCost = consumableMaterialCost;
	}


	public String getMedical() {
		return medical;
	}


	public void setMedical(String medical) {
		this.medical = medical;
	}


	public String getTreatmentProject() {
		return treatmentProject;
	}


	public void setTreatmentProject(String treatmentProject) {
		this.treatmentProject = treatmentProject;
	}


	public String getConsumableMaterial() {
		return consumableMaterial;
	}


	public void setConsumableMaterial(String consumableMaterial) {
		this.consumableMaterial = consumableMaterial;
	}


	/**
	 * @return the sysStatus
	 */
	public String getSysStatus() {
		return sysStatus;
	}


	/**
	 * @param sysStatus the sysStatus to set
	 */
	public void setSysStatus(String sysStatus) {
		this.sysStatus = sysStatus;
	}


	public String getAdmissionNo() {
		return admissionNo;
	}


	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
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


	public String getUserStatus() {
		return userStatus;
	}


	public String getItemIdStatus() {
		return itemIdStatus;
	}


	public void setItemIdStatus(String itemIdStatus) {
		this.itemIdStatus = itemIdStatus;
	}


	public String getExamineComments() {
		return examineComments;
	}


	public void setExamineComments(String examineComments) {
		this.examineComments = examineComments;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	/**
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}


	/**
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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


	public String getId() {
		return id;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAdviceId() {
		return adviceId;
	}


	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}


	public BigDecimal getApplyPayAmount() {
		return applyPayAmount;
	}


	public void setApplyPayAmount(BigDecimal applyPayAmount) {
		this.applyPayAmount = applyPayAmount;
	}


	public String getApplyPayLevel() {
		return applyPayLevel;
	}


	public void setApplyPayLevel(String applyPayLevel) {
		this.applyPayLevel = applyPayLevel;
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


	public String getDeliverWay() {
		return deliverWay;
	}


	public void setDeliverWay(String deliverWay) {
		this.deliverWay = deliverWay;
	}


	public String getDoseForm() {
		return doseForm;
	}


	public void setDoseForm(String doseForm) {
		this.doseForm = doseForm;
	}


	public String getDoseUnit() {
		return doseUnit;
	}


	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}


	public String getDrugType() {
		return drugType;
	}


	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}


	public Date getFeeCreateDate() {
		return feeCreateDate;
	}


	public void setFeeCreateDate(Date feeCreateDate) {
		this.feeCreateDate = feeCreateDate;
	}


	public BigDecimal getFullOrdination() {
		return fullOrdination;
	}


	public void setFullOrdination(BigDecimal fullOrdination) {
		this.fullOrdination = fullOrdination;
	}


	public BigDecimal getFullPayment() {
		return fullPayment;
	}


	public void setFullPayment(BigDecimal fullPayment) {
		this.fullPayment = fullPayment;
	}


	public String getIlegalType() {
		return ilegalType;
	}


	public void setIlegalType(String ilegalType) {
		this.ilegalType = ilegalType;
	}


	public String getIsIlegal() {
		return isIlegal;
	}


	public void setIsIlegal(String isIlegal) {
		this.isIlegal = isIlegal;
	}


	public String getIsInsuranceProject() {
		return isInsuranceProject;
	}


	public String getViolationStatus() {
		return violationStatus;
	}


	public void setViolationStatus(String violationStatus) {
		this.violationStatus = violationStatus;
	}


	public void setIsInsuranceProject(String isInsuranceProject) {
		this.isInsuranceProject = isInsuranceProject;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public BigDecimal getItemCost() {
		return itemCost;
	}


	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
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


	public String getItemStandard() {
		return itemStandard;
	}


	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}


	public BigDecimal getLimitPrice() {
		return limitPrice;
	}


	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}


	public String getMedicalId() {
		return medicalId;
	}


	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}


	public BigDecimal getPartialOrdination() {
		return partialOrdination;
	}


	public void setPartialOrdination(BigDecimal partialOrdination) {
		this.partialOrdination = partialOrdination;
	}


	public BigDecimal getPartialPayment() {
		return partialPayment;
	}


	public void setPartialPayment(BigDecimal partialPayment) {
		this.partialPayment = partialPayment;
	}


	public String getRecipelId() {
		return recipelId;
	}


	public void setRecipelId(String recipelId) {
		this.recipelId = recipelId;
	}


	public BigDecimal getSelfPayAmount() {
		return selfPayAmount;
	}


	public void setSelfPayAmount(BigDecimal selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}


	public String getSingleDose() {
		return singleDose;
	}


	public void setSingleDose(String singleDose) {
		this.singleDose = singleDose;
	}


	public BigDecimal getSumAmount() {
		return sumAmount;
	}


	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}


	public String getTakeFrequence() {
		return takeFrequence;
	}


	public void setTakeFrequence(String takeFrequence) {
		this.takeFrequence = takeFrequence;
	}


	public String getUseDay() {
		return useDay;
	}


	public void setUseDay(String useDay) {
		this.useDay = useDay;
	}


	public String getProjectType() {
		return projectType;
	}


	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	
	public String getSpecs() {
		return specs;
	}

	
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	

	public BigDecimal getTotalDrugIntake() {
		return totalDrugIntake;
	}

	
	public void setTotalDrugIntake(BigDecimal totalDrugIntake) {
		this.totalDrugIntake = totalDrugIntake;
	}

	
	public BigDecimal getDosageDays() {
		return dosageDays;
	}

	
	public void setDosageDays(BigDecimal dosageDays) {
		this.dosageDays = dosageDays;
	}

	
	public String getPrescriptionDischarge() {
		return prescriptionDischarge;
	}

	
	public void setPrescriptionDischarge(String prescriptionDischarge) {
		this.prescriptionDischarge = prescriptionDischarge;
	}

	
	public String getMedicalInsCode() {
		return medicalInsCode;
	}

	
	public void setMedicalInsCode(String medicalInsCode) {
		this.medicalInsCode = medicalInsCode;
	}

	
	public String getMedicalInsName() {
		return medicalInsName;
	}

	
	public void setMedicalInsName(String medicalInsName) {
		this.medicalInsName = medicalInsName;
	}

	
	public String getDepartCode() {
		return departCode;
	}

	
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	
	public String getItemIdIsExist() {
		return itemIdIsExist;
	}


	public void setItemIdIsExist(String itemIdIsExist) {
		this.itemIdIsExist = itemIdIsExist;
	}


	public String getDepartName() {
		return departName;
	}

	
	public void setDepartName(String departName) {
		this.departName = departName;
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

	
	public String getDrugMeaUnit() {
		return drugMeaUnit;
	}

	
	public void setDrugMeaUnit(String drugMeaUnit) {
		this.drugMeaUnit = drugMeaUnit;
	}

	
	public String getFeeMeaUnit() {
		return feeMeaUnit;
	}

	
	public void setFeeMeaUnit(String feeMeaUnit) {
		this.feeMeaUnit = feeMeaUnit;
	}
	

	public String getHosServiceName() {
		return hosServiceName;
	}

	
	public void setHosServiceName(String hosServiceName) {
		this.hosServiceName = hosServiceName;
	}

	
	public Date getBalanceDate() {
		return balanceDate;
	}

	
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	
	public BigDecimal getInsCost() {
		return insCost;
	}

	
	public void setInsCost(BigDecimal insCost) {
		this.insCost = insCost;
	}

	
	public BigDecimal getFundCost() {
		return fundCost;
	}
	

	public void setFundCost(BigDecimal fundCost) {
		this.fundCost = fundCost;
	}

	
	public BigDecimal getNotInsCost() {
		return notInsCost;
	}

	
	public void setNotInsCost(BigDecimal notInsCost) {
		this.notInsCost = notInsCost;
	}

	
	public String getCountTime() {
		return countTime;
	}


	public void setCountTime(String countTime) {
		this.countTime = countTime;
	}


	public String getDurgDosage() {
		return durgDosage;
	}

	
	public void setDurgDosage(String durgDosage) {
		this.durgDosage = durgDosage;
	}

	
	public String getItemUnit() {
		return itemUnit;
	}

	
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	
	public String getAdviceGroup() {
		return adviceGroup;
	}

	
	public void setAdviceGroup(String adviceGroup) {
		this.adviceGroup = adviceGroup;
	}

	
	public String getAdviceOrder() {
		return adviceOrder;
	}

	
	public void setAdviceOrder(String adviceOrder) {
		this.adviceOrder = adviceOrder;
	}

	
	public String getAppealResult() {
		return appealResult;
	}


	public void setAppealResult(String appealResult) {
		this.appealResult = appealResult;
	}


	public String getSelfPayRate() {
		return selfPayRate;
	}

	
	public void setSelfPayRate(String selfPayRate) {
		this.selfPayRate = selfPayRate;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
