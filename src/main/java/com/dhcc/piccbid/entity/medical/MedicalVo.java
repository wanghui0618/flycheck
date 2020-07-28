package com.dhcc.piccbid.entity.medical;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicalVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String admissionNo;
	private String admissionType;
	private String aduitStatus;
	private BigDecimal age;
	private String attendingDocCode;
	private String attendingDocTitle;
	private String auditStatus;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "BALANCE_DATE")
	private Date balanceDate;
	private BigDecimal basicCostM;
	private BigDecimal basicCostR;
	private String billingNo;
	private BigDecimal cashCost;
	private String changeHos;
	private String chargeNurse;
	private String cityCode;
	private BigDecimal civilAffairSubsidy;
	private BigDecimal claimCost;
	private String condition;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_Date")
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME")
	private Date createTime;
	private String crowdType;
	private String departCode;
	private String departName;
	private String diagType;
	private String directorDoc;
	private String dischargeState;
	private String doctorName;
	private String finaStatus;
	private BigDecimal financeSubsidy;
	private BigDecimal fullOrdination;
	private BigDecimal fullPayment;
	private BigDecimal fundCost;
	private String handdingPerson;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "HANDING_TIME")
	private Date handingTime;
	private String hospCount;
	private String hreadDepart;
	private String idcard;
	private String inDiagnosisName;
	private String inDiagnosisNo;
	private String inHosMethod;
	private String inHosMethodCode;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "INHOS_DATE")
	private Date inhosDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "INSURANCE_CLOSE_DATE")
	private Date insuranceCloseDate;
	private String insuranceMark;
	private BigDecimal insureCost;
	private String insurePersonCode;
	private String insurePersonType;
	private BigDecimal largeCostM;
	private BigDecimal largeCostR;
	private String medicalCloseNumber;
	private String medicalTreatmentState;
	private String medicalType;
	private String name;
	private BigDecimal notInsureCost;
	private BigDecimal officialSubsidy;
	private String orgCode;
	private String orgName;
	private String outDiagnosisName;
	private String outDiagnosisNo;
	private String outHosDepart;
	private String outHosDepartCode;
	private String outHosMethod;
	private String outHosMethodCode;
	private String outHosSummary;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "OUTHOS_DATE")
	private Date outhosDate;
	private BigDecimal partialOrdination;
	private BigDecimal partialPayment;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "PAYMENT_DATE")
	private Date paymentDate;
	private BigDecimal povertyAlleviationSubsidy;
	private BigDecimal rangeCost;
	private BigDecimal rangeYearCost;
	private String reimbursementType;
	private String residentDoc;
	private String reversalMark;
	private String seeDocDetail;
	private String seeDocType;
	private BigDecimal selfCost;
	private BigDecimal selfExpenditureAmount;
	private BigDecimal selfPayAmount;
	private String sex;
	private BigDecimal sscAccountCost;
	private String sscno;
	private String status;
	private BigDecimal stayLength;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "SYS_AUDITING_DATE")
	private Date sysAuditingDate;
	private String sysStatus;
	private BigDecimal totalCost;
	private String treatmentType;
	private String treatmentWay;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	private String userStatus;
	private String visitingCardNumber;

	private String resultAppealStatu;

	private String cityName;

	private String filterCondition;

	private Date userVerifyDate;

	private String userVerifyPerson;

	private String userVerifyDirection;

	private String userVerifyMoney;

	private String userVerifyRemarks;

	private Date finaVerifyDate;

	private String finaVerifyPerson;

	private String finaVerifyMoney;

	private String finaVerifyRemarks;

	private String auditMethods;

	private String handdingInsCode;

	private String handdingInsName;

	private String ilegalType;

	private BigDecimal sumAmount;

	private int total;

	private String medicalInsName;

	public String getMedicalInsName() {
		return medicalInsName;
	}

	public void setMedicalInsName(String medicalInsName) {
		this.medicalInsName = medicalInsName;
	}

	public String getAuditMethods() {
		return auditMethods;
	}

	public void setAuditMethods(String auditMethods) {
		this.auditMethods = auditMethods;
	}

	public Date getFinaVerifyDate() {
		return finaVerifyDate;
	}

	public void setFinaVerifyDate(Date finaVerifyDate) {
		this.finaVerifyDate = finaVerifyDate;
	}

	public String getFinaVerifyPerson() {
		return finaVerifyPerson;
	}

	public void setFinaVerifyPerson(String finaVerifyPerson) {
		this.finaVerifyPerson = finaVerifyPerson;
	}

	public String getFinaVerifyMoney() {
		return finaVerifyMoney;
	}

	public void setFinaVerifyMoney(String finaVerifyMoney) {
		this.finaVerifyMoney = finaVerifyMoney;
	}

	public String getFinaVerifyRemarks() {
		return finaVerifyRemarks;
	}

	public void setFinaVerifyRemarks(String finaVerifyRemarks) {
		this.finaVerifyRemarks = finaVerifyRemarks;
	}

	public String getUserVerifyMoney() {
		return userVerifyMoney;
	}

	public void setUserVerifyMoney(String userVerifyMoney) {
		this.userVerifyMoney = userVerifyMoney;
	}

	public String getUserVerifyRemarks() {
		return userVerifyRemarks;
	}

	public void setUserVerifyRemarks(String userVerifyRemarks) {
		this.userVerifyRemarks = userVerifyRemarks;
	}

	public Date getUserVerifyDate() {
		return userVerifyDate;
	}

	public void setUserVerifyDate(Date userVerifyDate) {
		this.userVerifyDate = userVerifyDate;
	}

	public String getUserVerifyPerson() {
		return userVerifyPerson;
	}

	public void setUserVerifyPerson(String userVerifyPerson) {
		this.userVerifyPerson = userVerifyPerson;
	}

	public String getUserVerifyDirection() {
		return userVerifyDirection;
	}

	public void setUserVerifyDirection(String userVerifyDirection) {
		this.userVerifyDirection = userVerifyDirection;
	}

	public String getResultAppealStatu() {
		return resultAppealStatu;
	}

	public void setResultAppealStatu(String resultAppealStatu) {
		this.resultAppealStatu = resultAppealStatu;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the age
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
	}

	/**
	 * @return the attendingDocCode
	 */
	public String getAttendingDocCode() {
		return attendingDocCode;
	}

	/**
	 * @param attendingDocCode the attendingDocCode to set
	 */
	public void setAttendingDocCode(String attendingDocCode) {
		this.attendingDocCode = attendingDocCode;
	}

	/**
	 * @return the attendingDocTitle
	 */
	public String getAttendingDocTitle() {
		return attendingDocTitle;
	}

	/**
	 * @param attendingDocTitle the attendingDocTitle to set
	 */
	public void setAttendingDocTitle(String attendingDocTitle) {
		this.attendingDocTitle = attendingDocTitle;
	}

	/**
	 * @return the auditStatus
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the balanceDate
	 */
	public Date getBalanceDate() {
		return balanceDate;
	}

	/**
	 * @param balanceDate the balanceDate to set
	 */
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	/**
	 * @return the basicCostM
	 */
	public BigDecimal getBasicCostM() {
		return basicCostM;
	}

	/**
	 * @param basicCostM the basicCostM to set
	 */
	public void setBasicCostM(BigDecimal basicCostM) {
		this.basicCostM = basicCostM;
	}

	/**
	 * @return the basicCostR
	 */
	public BigDecimal getBasicCostR() {
		return basicCostR;
	}

	/**
	 * @param basicCostR the basicCostR to set
	 */
	public void setBasicCostR(BigDecimal basicCostR) {
		this.basicCostR = basicCostR;
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
	 * @return the cashCost
	 */
	public BigDecimal getCashCost() {
		return cashCost;
	}

	/**
	 * @param cashCost the cashCost to set
	 */
	public void setCashCost(BigDecimal cashCost) {
		this.cashCost = cashCost;
	}

	/**
	 * @return the changeHos
	 */
	public String getChangeHos() {
		return changeHos;
	}

	/**
	 * @param changeHos the changeHos to set
	 */
	public void setChangeHos(String changeHos) {
		this.changeHos = changeHos;
	}

	/**
	 * @return the chargeNurse
	 */
	public String getChargeNurse() {
		return chargeNurse;
	}

	/**
	 * @param chargeNurse the chargeNurse to set
	 */
	public void setChargeNurse(String chargeNurse) {
		this.chargeNurse = chargeNurse;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the civilAffairSubsidy
	 */
	public BigDecimal getCivilAffairSubsidy() {
		return civilAffairSubsidy;
	}

	/**
	 * @param civilAffairSubsidy the civilAffairSubsidy to set
	 */
	public void setCivilAffairSubsidy(BigDecimal civilAffairSubsidy) {
		this.civilAffairSubsidy = civilAffairSubsidy;
	}

	/**
	 * @return the claimCost
	 */
	public BigDecimal getClaimCost() {
		return claimCost;
	}

	/**
	 * @param claimCost the claimCost to set
	 */
	public void setClaimCost(BigDecimal claimCost) {
		this.claimCost = claimCost;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the crowdType
	 */
	public String getCrowdType() {
		return crowdType;
	}

	/**
	 * @param crowdType the crowdType to set
	 */
	public void setCrowdType(String crowdType) {
		this.crowdType = crowdType;
	}

	/**
	 * @return the departCode
	 */
	public String getDepartCode() {
		return departCode;
	}

	/**
	 * @param departCode the departCode to set
	 */
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the diagType
	 */
	public String getDiagType() {
		return diagType;
	}

	/**
	 * @param diagType the diagType to set
	 */
	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}

	/**
	 * @return the directorDoc
	 */
	public String getDirectorDoc() {
		return directorDoc;
	}

	/**
	 * @param directorDoc the directorDoc to set
	 */
	public void setDirectorDoc(String directorDoc) {
		this.directorDoc = directorDoc;
	}

	/**
	 * @return the dischargeState
	 */
	public String getDischargeState() {
		return dischargeState;
	}

	/**
	 * @param dischargeState the dischargeState to set
	 */
	public void setDischargeState(String dischargeState) {
		this.dischargeState = dischargeState;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the finaStatus
	 */
	public String getFinaStatus() {
		return finaStatus;
	}

	/**
	 * @param finaStatus the finaStatus to set
	 */
	public void setFinaStatus(String finaStatus) {
		this.finaStatus = finaStatus;
	}

	/**
	 * @return the financeSubsidy
	 */
	public BigDecimal getFinanceSubsidy() {
		return financeSubsidy;
	}

	/**
	 * @param financeSubsidy the financeSubsidy to set
	 */
	public void setFinanceSubsidy(BigDecimal financeSubsidy) {
		this.financeSubsidy = financeSubsidy;
	}

	/**
	 * @return the fullOrdination
	 */
	public BigDecimal getFullOrdination() {
		return fullOrdination;
	}

	/**
	 * @param fullOrdination the fullOrdination to set
	 */
	public void setFullOrdination(BigDecimal fullOrdination) {
		this.fullOrdination = fullOrdination;
	}

	/**
	 * @return the fullPayment
	 */
	public BigDecimal getFullPayment() {
		return fullPayment;
	}

	/**
	 * @param fullPayment the fullPayment to set
	 */
	public void setFullPayment(BigDecimal fullPayment) {
		this.fullPayment = fullPayment;
	}

	/**
	 * @return the fundCost
	 */
	public BigDecimal getFundCost() {
		return fundCost;
	}

	/**
	 * @param fundCost the fundCost to set
	 */
	public void setFundCost(BigDecimal fundCost) {
		this.fundCost = fundCost;
	}

	/**
	 * @return the handdingPerson
	 */
	public String getHanddingPerson() {
		return handdingPerson;
	}

	/**
	 * @param handdingPerson the handdingPerson to set
	 */
	public void setHanddingPerson(String handdingPerson) {
		this.handdingPerson = handdingPerson;
	}

	/**
	 * @return the handingTime
	 */
	public Date getHandingTime() {
		return handingTime;
	}

	/**
	 * @param handingTime the handingTime to set
	 */
	public void setHandingTime(Date handingTime) {
		this.handingTime = handingTime;
	}

	/**
	 * @return the hospCount
	 */
	public String getHospCount() {
		return hospCount;
	}

	/**
	 * @param hospCount the hospCount to set
	 */
	public void setHospCount(String hospCount) {
		this.hospCount = hospCount;
	}

	/**
	 * @return the hreadDepart
	 */
	public String getHreadDepart() {
		return hreadDepart;
	}

	/**
	 * @param hreadDepart the hreadDepart to set
	 */
	public void setHreadDepart(String hreadDepart) {
		this.hreadDepart = hreadDepart;
	}

	/**
	 * @return the idcard
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * @param idcard the idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * @return the inDiagnosisName
	 */
	public String getInDiagnosisName() {
		return inDiagnosisName;
	}

	/**
	 * @param inDiagnosisName the inDiagnosisName to set
	 */
	public void setInDiagnosisName(String inDiagnosisName) {
		this.inDiagnosisName = inDiagnosisName;
	}

	/**
	 * @return the inDiagnosisNo
	 */
	public String getInDiagnosisNo() {
		return inDiagnosisNo;
	}

	/**
	 * @param inDiagnosisNo the inDiagnosisNo to set
	 */
	public void setInDiagnosisNo(String inDiagnosisNo) {
		this.inDiagnosisNo = inDiagnosisNo;
	}

	/**
	 * @return the inHosMethod
	 */
	public String getInHosMethod() {
		return inHosMethod;
	}

	/**
	 * @param inHosMethod the inHosMethod to set
	 */
	public void setInHosMethod(String inHosMethod) {
		this.inHosMethod = inHosMethod;
	}

	/**
	 * @return the inHosMethodCode
	 */
	public String getInHosMethodCode() {
		return inHosMethodCode;
	}

	/**
	 * @param inHosMethodCode the inHosMethodCode to set
	 */
	public void setInHosMethodCode(String inHosMethodCode) {
		this.inHosMethodCode = inHosMethodCode;
	}

	/**
	 * @return the inhosDate
	 */
	public Date getInhosDate() {
		return inhosDate;
	}

	/**
	 * @param inhosDate the inhosDate to set
	 */
	public void setInhosDate(Date inhosDate) {
		this.inhosDate = inhosDate;
	}

	/**
	 * @return the insuranceCloseDate
	 */
	public Date getInsuranceCloseDate() {
		return insuranceCloseDate;
	}

	/**
	 * @param insuranceCloseDate the insuranceCloseDate to set
	 */
	public void setInsuranceCloseDate(Date insuranceCloseDate) {
		this.insuranceCloseDate = insuranceCloseDate;
	}

	/**
	 * @return the insuranceMark
	 */
	public String getInsuranceMark() {
		return insuranceMark;
	}

	/**
	 * @param insuranceMark the insuranceMark to set
	 */
	public void setInsuranceMark(String insuranceMark) {
		this.insuranceMark = insuranceMark;
	}

	/**
	 * @return the insureCost
	 */
	public BigDecimal getInsureCost() {
		return insureCost;
	}

	/**
	 * @param insureCost the insureCost to set
	 */
	public void setInsureCost(BigDecimal insureCost) {
		this.insureCost = insureCost;
	}

	/**
	 * @return the insurePersonCode
	 */
	public String getInsurePersonCode() {
		return insurePersonCode;
	}

	/**
	 * @param insurePersonCode the insurePersonCode to set
	 */
	public void setInsurePersonCode(String insurePersonCode) {
		this.insurePersonCode = insurePersonCode;
	}

	/**
	 * @return the insurePersonType
	 */
	public String getInsurePersonType() {
		return insurePersonType;
	}

	/**
	 * @param insurePersonType the insurePersonType to set
	 */
	public void setInsurePersonType(String insurePersonType) {
		this.insurePersonType = insurePersonType;
	}

	/**
	 * @return the largeCostM
	 */
	public BigDecimal getLargeCostM() {
		return largeCostM;
	}

	/**
	 * @param largeCostM the largeCostM to set
	 */
	public void setLargeCostM(BigDecimal largeCostM) {
		this.largeCostM = largeCostM;
	}

	/**
	 * @return the largeCostR
	 */
	public BigDecimal getLargeCostR() {
		return largeCostR;
	}

	/**
	 * @param largeCostR the largeCostR to set
	 */
	public void setLargeCostR(BigDecimal largeCostR) {
		this.largeCostR = largeCostR;
	}

	/**
	 * @return the medicalCloseNumber
	 */
	public String getMedicalCloseNumber() {
		return medicalCloseNumber;
	}

	/**
	 * @param medicalCloseNumber the medicalCloseNumber to set
	 */
	public void setMedicalCloseNumber(String medicalCloseNumber) {
		this.medicalCloseNumber = medicalCloseNumber;
	}

	/**
	 * @return the medicalTreatmentState
	 */
	public String getMedicalTreatmentState() {
		return medicalTreatmentState;
	}

	/**
	 * @param medicalTreatmentState the medicalTreatmentState to set
	 */
	public void setMedicalTreatmentState(String medicalTreatmentState) {
		this.medicalTreatmentState = medicalTreatmentState;
	}

	/**
	 * @return the medicalType
	 */
	public String getMedicalType() {
		return medicalType;
	}

	/**
	 * @param medicalType the medicalType to set
	 */
	public void setMedicalType(String medicalType) {
		this.medicalType = medicalType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the notInsureCost
	 */
	public BigDecimal getNotInsureCost() {
		return notInsureCost;
	}

	/**
	 * @param notInsureCost the notInsureCost to set
	 */
	public void setNotInsureCost(BigDecimal notInsureCost) {
		this.notInsureCost = notInsureCost;
	}

	/**
	 * @return the officialSubsidy
	 */
	public BigDecimal getOfficialSubsidy() {
		return officialSubsidy;
	}

	/**
	 * @param officialSubsidy the officialSubsidy to set
	 */
	public void setOfficialSubsidy(BigDecimal officialSubsidy) {
		this.officialSubsidy = officialSubsidy;
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
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the outDiagnosisName
	 */
	public String getOutDiagnosisName() {
		return outDiagnosisName;
	}

	/**
	 * @param outDiagnosisName the outDiagnosisName to set
	 */
	public void setOutDiagnosisName(String outDiagnosisName) {
		this.outDiagnosisName = outDiagnosisName;
	}

	/**
	 * @return the outDiagnosisNo
	 */
	public String getOutDiagnosisNo() {
		return outDiagnosisNo;
	}

	/**
	 * @param outDiagnosisNo the outDiagnosisNo to set
	 */
	public void setOutDiagnosisNo(String outDiagnosisNo) {
		this.outDiagnosisNo = outDiagnosisNo;
	}

	/**
	 * @return the outHosDepart
	 */
	public String getOutHosDepart() {
		return outHosDepart;
	}

	/**
	 * @param outHosDepart the outHosDepart to set
	 */
	public void setOutHosDepart(String outHosDepart) {
		this.outHosDepart = outHosDepart;
	}

	/**
	 * @return the outHosDepartCode
	 */
	public String getOutHosDepartCode() {
		return outHosDepartCode;
	}

	/**
	 * @param outHosDepartCode the outHosDepartCode to set
	 */
	public void setOutHosDepartCode(String outHosDepartCode) {
		this.outHosDepartCode = outHosDepartCode;
	}

	/**
	 * @return the outHosMethod
	 */
	public String getOutHosMethod() {
		return outHosMethod;
	}

	/**
	 * @param outHosMethod the outHosMethod to set
	 */
	public void setOutHosMethod(String outHosMethod) {
		this.outHosMethod = outHosMethod;
	}

	/**
	 * @return the outHosMethodCode
	 */
	public String getOutHosMethodCode() {
		return outHosMethodCode;
	}

	/**
	 * @param outHosMethodCode the outHosMethodCode to set
	 */
	public void setOutHosMethodCode(String outHosMethodCode) {
		this.outHosMethodCode = outHosMethodCode;
	}

	/**
	 * @return the outHosSummary
	 */
	public String getOutHosSummary() {
		return outHosSummary;
	}

	/**
	 * @param outHosSummary the outHosSummary to set
	 */
	public void setOutHosSummary(String outHosSummary) {
		this.outHosSummary = outHosSummary;
	}

	/**
	 * @return the outhosDate
	 */
	public Date getOuthosDate() {
		return outhosDate;
	}

	/**
	 * @param outhosDate the outhosDate to set
	 */
	public void setOuthosDate(Date outhosDate) {
		this.outhosDate = outhosDate;
	}

	/**
	 * @return the partialOrdination
	 */
	public BigDecimal getPartialOrdination() {
		return partialOrdination;
	}

	/**
	 * @param partialOrdination the partialOrdination to set
	 */
	public void setPartialOrdination(BigDecimal partialOrdination) {
		this.partialOrdination = partialOrdination;
	}

	/**
	 * @return the partialPayment
	 */
	public BigDecimal getPartialPayment() {
		return partialPayment;
	}

	/**
	 * @param partialPayment the partialPayment to set
	 */
	public void setPartialPayment(BigDecimal partialPayment) {
		this.partialPayment = partialPayment;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the povertyAlleviationSubsidy
	 */
	public BigDecimal getPovertyAlleviationSubsidy() {
		return povertyAlleviationSubsidy;
	}

	/**
	 * @param povertyAlleviationSubsidy the povertyAlleviationSubsidy to set
	 */
	public void setPovertyAlleviationSubsidy(BigDecimal povertyAlleviationSubsidy) {
		this.povertyAlleviationSubsidy = povertyAlleviationSubsidy;
	}

	/**
	 * @return the rangeCost
	 */
	public BigDecimal getRangeCost() {
		return rangeCost;
	}

	/**
	 * @param rangeCost the rangeCost to set
	 */
	public void setRangeCost(BigDecimal rangeCost) {
		this.rangeCost = rangeCost;
	}

	/**
	 * @return the rangeYearCost
	 */
	public BigDecimal getRangeYearCost() {
		return rangeYearCost;
	}

	/**
	 * @param rangeYearCost the rangeYearCost to set
	 */
	public void setRangeYearCost(BigDecimal rangeYearCost) {
		this.rangeYearCost = rangeYearCost;
	}

	/**
	 * @return the reimbursementType
	 */
	public String getReimbursementType() {
		return reimbursementType;
	}

	/**
	 * @param reimbursementType the reimbursementType to set
	 */
	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	/**
	 * @return the residentDoc
	 */
	public String getResidentDoc() {
		return residentDoc;
	}

	/**
	 * @param residentDoc the residentDoc to set
	 */
	public void setResidentDoc(String residentDoc) {
		this.residentDoc = residentDoc;
	}

	/**
	 * @return the reversalMark
	 */
	public String getReversalMark() {
		return reversalMark;
	}

	/**
	 * @param reversalMark the reversalMark to set
	 */
	public void setReversalMark(String reversalMark) {
		this.reversalMark = reversalMark;
	}

	/**
	 * @return the seeDocDetail
	 */
	public String getSeeDocDetail() {
		return seeDocDetail;
	}

	/**
	 * @param seeDocDetail the seeDocDetail to set
	 */
	public void setSeeDocDetail(String seeDocDetail) {
		this.seeDocDetail = seeDocDetail;
	}

	/**
	 * @return the seeDocType
	 */
	public String getSeeDocType() {
		return seeDocType;
	}

	/**
	 * @param seeDocType the seeDocType to set
	 */
	public void setSeeDocType(String seeDocType) {
		this.seeDocType = seeDocType;
	}

	/**
	 * @return the selfCost
	 */
	public BigDecimal getSelfCost() {
		return selfCost;
	}

	/**
	 * @param selfCost the selfCost to set
	 */
	public void setSelfCost(BigDecimal selfCost) {
		this.selfCost = selfCost;
	}

	/**
	 * @return the selfExpenditureAmount
	 */
	public BigDecimal getSelfExpenditureAmount() {
		return selfExpenditureAmount;
	}

	/**
	 * @param selfExpenditureAmount the selfExpenditureAmount to set
	 */
	public void setSelfExpenditureAmount(BigDecimal selfExpenditureAmount) {
		this.selfExpenditureAmount = selfExpenditureAmount;
	}

	/**
	 * @return the selfPayAmount
	 */
	public BigDecimal getSelfPayAmount() {
		return selfPayAmount;
	}

	/**
	 * @param selfPayAmount the selfPayAmount to set
	 */
	public void setSelfPayAmount(BigDecimal selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the sscAccountCost
	 */
	public BigDecimal getSscAccountCost() {
		return sscAccountCost;
	}

	/**
	 * @param sscAccountCost the sscAccountCost to set
	 */
	public void setSscAccountCost(BigDecimal sscAccountCost) {
		this.sscAccountCost = sscAccountCost;
	}

	/**
	 * @return the sscno
	 */
	public String getSscno() {
		return sscno;
	}

	/**
	 * @param sscno the sscno to set
	 */
	public void setSscno(String sscno) {
		this.sscno = sscno;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the stayLength
	 */
	public BigDecimal getStayLength() {
		return stayLength;
	}

	/**
	 * @param stayLength the stayLength to set
	 */
	public void setStayLength(BigDecimal stayLength) {
		this.stayLength = stayLength;
	}

	/**
	 * @return the sysAuditingDate
	 */
	public Date getSysAuditingDate() {
		return sysAuditingDate;
	}

	/**
	 * @param sysAuditingDate the sysAuditingDate to set
	 */
	public void setSysAuditingDate(Date sysAuditingDate) {
		this.sysAuditingDate = sysAuditingDate;
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

	/**
	 * @return the totalCost
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the treatmentType
	 */
	public String getTreatmentType() {
		return treatmentType;
	}

	/**
	 * @param treatmentType the treatmentType to set
	 */
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	/**
	 * @return the treatmentWay
	 */
	public String getTreatmentWay() {
		return treatmentWay;
	}

	/**
	 * @param treatmentWay the treatmentWay to set
	 */
	public void setTreatmentWay(String treatmentWay) {
		this.treatmentWay = treatmentWay;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

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
	 * @return the visitingCardNumber
	 */
	public String getVisitingCardNumber() {
		return visitingCardNumber;
	}

	/**
	 * @param visitingCardNumber the visitingCardNumber to set
	 */
	public void setVisitingCardNumber(String visitingCardNumber) {
		this.visitingCardNumber = visitingCardNumber;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the filterCondition
	 */
	public String getFilterCondition() {
		return filterCondition;
	}

	/**
	 * @param filterCondition the filterCondition to set
	 */
	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
	}

	public String getHanddingInsCode() {
		return handdingInsCode;
	}

	public void setHanddingInsCode(String handdingInsCode) {
		this.handdingInsCode = handdingInsCode;
	}

	public String getHanddingInsName() {
		return handdingInsName;
	}

	public void setHanddingInsName(String handdingInsName) {
		this.handdingInsName = handdingInsName;
	}

	public String getIlegalType() {
		return ilegalType;
	}

	public void setIlegalType(String ilegalType) {
		this.ilegalType = ilegalType;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
