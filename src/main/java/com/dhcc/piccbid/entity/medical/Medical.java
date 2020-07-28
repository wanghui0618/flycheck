package com.dhcc.piccbid.entity.medical;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "T_PICCBID_MEDICAL")
public class Medical implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "ADMISSION_NO")
    private String admissionNo;

    @Column(name = "ADMISSION_TYPE")
    private String admissionType;

    @Column(name = "ADUIT_STATUS")
    private String aduitStatus;

    @Column(name = "AGE")
    private BigDecimal age;

    @Column(name = "ATTENDING_DOC_CODE")
    private String attendingDocCode;

    @Column(name = "ATTENDING_DOC_TITLE")
    private String attendingDocTitle;

    @Column(name = "AUDIT_METHODS")
    private String auditMethods;

    @Column(name = "AUDIT_STATUS")
    private String auditStatus;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "BALANCE_DATE")
    private Date balanceDate;

    @Column(name = "BASIC_COST_M")
    private BigDecimal basicCostM;

    @Column(name = "BASIC_COST_R")
    private BigDecimal basicCostR;

    @Column(name = "BILLING_NO")
    private String billingNo;

    @Column(name = "CASH_COST")
    private BigDecimal cashCost;

    @Column(name = "CHANGE_HOS")
    private String changeHos;

    @Column(name = "CHARGE_NURSE")
    private String chargeNurse;

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "HANDDING_INS_CODE")
    private String handdingInsCode;

    @Column(name = "CIVIL_AFFAIR_SUBSIDY")
    private BigDecimal civilAffairSubsidy;

    @Column(name = "CLAIM_COST")
    private BigDecimal claimCost;

    @Column(name = "CONDITION")
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

    @Column(name = "CROWD_TYPE")
    private String crowdType;

    @Column(name = "DEPART_CODE")
    private String departCode;

    @Column(name = "DEPART_NAME")
    private String departName;

    @Column(name = "DIAG_TYPE")
    private String diagType;

    @Column(name = "DIRECTOR_DOC")
    private String directorDoc;

    @Column(name = "DISCHARGE_STATE")
    private String dischargeState;

    @Column(name = "DOCTOR_NAME")
    private String doctorName;

    @Column(name = "FINA_STATUS")
    private String finaStatus;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "FINA_VERIFY_DATE")
    private Date finaVerifyDate;

    @Column(name = "FINA_VERIFY_MONEY")
    private String finaVerifyMoney;

    @Column(name = "FINA_VERIFY_PERSON")
    private String finaVerifyPerson;

    @Column(name = "FINA_VERIFY_REMARKS")
    private String finaVerifyRemarks;

    @Column(name = "FINANCE_SUBSIDY")
    private BigDecimal financeSubsidy;

    @Column(name = "FULL_ORDINATION")
    private BigDecimal fullOrdination;

    @Column(name = "FULL_PAYMENT")
    private BigDecimal fullPayment;

    @Column(name = "FUND_COST")
    private BigDecimal fundCost;

    @Column(name = "HANDDING_PERSON")
    private String handdingPerson;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "HANDING_TIME")
    private Date handingTime;

    @Column(name = "HOSP_COUNT")
    private String hospCount;

    @Column(name = "HREAD_DEPART")
    private String hreadDepart;

    @Column(name = "IDCARD")
    private String idcard;

    @Column(name = "IN_DIAGNOSIS_NAME")
    private String inDiagnosisName;

    @Column(name = "IN_DIAGNOSIS_NO")
    private String inDiagnosisNo;

    @Column(name = "IN_HOS_METHOD")
    private String inHosMethod;

    @Column(name = "IN_HOS_METHOD_CODE")
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

    @Column(name = "INSURANCE_MARK")
    private String insuranceMark;

    @Column(name = "INSURE_COST")
    private BigDecimal insureCost;

    @Column(name = "INSURE_PERSON_CODE")
    private String insurePersonCode;

    @Column(name = "INSURE_PERSON_TYPE")
    private String insurePersonType;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "JH_TIME")
    private Date jhTime;

    @Column(name = "LARGE_COST_M")
    private BigDecimal largeCostM;

    @Column(name = "LARGE_COST_R")
    private BigDecimal largeCostR;

    @Column(name = "MEDICAL_CLOSE_NUMBER")
    private String medicalCloseNumber;

    @Column(name = "MEDICAL_TREATMENT_STATE")
    private String medicalTreatmentState;

    @Column(name = "MEDICAL_TYPE")
    private String medicalType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NOT_INSURE_COST")
    private BigDecimal notInsureCost;

    @Column(name = "OFFICIAL_SUBSIDY")
    private BigDecimal officialSubsidy;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "OPERATION_TIME")
    private Date operationTime;

    @Column(name = "ORG_CODE")
    private String orgCode;

    @Column(name = "ORG_NAME")
    private String orgName;

    @Column(name = "OUT_DIAGNOSIS_NAME")
    private String outDiagnosisName;

    @Column(name = "OUT_DIAGNOSIS_NO")
    private String outDiagnosisNo;

    @Column(name = "OUT_HOS_DEPART")
    private String outHosDepart;

    @Column(name = "OUT_HOS_DEPART_CODE")
    private String outHosDepartCode;

    @Column(name = "OUT_HOS_METHOD")
    private String outHosMethod;

    @Column(name = "OUT_HOS_METHOD_CODE")
    private String outHosMethodCode;

    @Column(name = "OUT_HOS_SUMMARY")
    private String outHosSummary;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "OUTHOS_DATE")
    private Date outhosDate;

    @Column(name = "PARTIAL_ORDINATION")
    private BigDecimal partialOrdination;

    @Column(name = "PARTIAL_PAYMENT")
    private BigDecimal partialPayment;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "POVERTY_ALLEVIATION_SUBSIDY")
    private BigDecimal povertyAlleviationSubsidy;

    @Column(name = "RANGE_COST")
    private BigDecimal rangeCost;

    @Column(name = "RANGE_YEAR_COST")
    private BigDecimal rangeYearCost;

    @Column(name = "REIMBURSEMENT_TYPE")
    private String reimbursementType;

    @Column(name = "RESIDENT_DOC")
    private String residentDoc;

    @Column(name = "RESULT_APPEAL_STATU")
    private String resultAppealStatu;

    @Column(name = "REVERSAL_MARK")
    private String reversalMark;

    @Column(name = "SEE_DOC_DETAIL")
    private String seeDocDetail;

    @Column(name = "SEE_DOC_TYPE")
    private String seeDocType;

    @Column(name = "SELF_COST")
    private BigDecimal selfCost;

    @Column(name = "SELF_EXPENDITURE_AMOUNT")
    private BigDecimal selfExpenditureAmount;

    @Column(name = "SELF_PAY_AMOUNT")
    private BigDecimal selfPayAmount;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "SSC_ACCOUNT_COST")
    private BigDecimal sscAccountCost;

    @Column(name = "SSCNO")
    private String sscno;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STAY_LENGTH")
    private BigDecimal stayLength;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "SYS_AUDITING_DATE")
    private Date sysAuditingDate;

    @Column(name = "SYS_STATUS")
    private String sysStatus;

    @Column(name = "TOTAL_AMOUNT")
    private String totalAmount;

    @Column(name = "TOTAL_COST")
    private BigDecimal totalCost;

    @Column(name = "TREATMENT_TYPE")
    private String treatmentType;

    @Column(name = "TREATMENT_WAY")
    private String treatmentWay;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @Column(name = "USER_STATUS")
    private String userStatus;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "USER_VERIFY_DATE")
    private Date userVerifyDate;

    @Column(name = "USER_VERIFY_DIRECTION")
    private String userVerifyDirection;

    @Column(name = "USER_VERIFY_MONEY")
    private String userVerifyMoney;

    @Column(name = "USER_VERIFY_PERSON")
    private String userVerifyPerson;

    @Column(name = "USER_VERIFY_REMARKS")
    private String userVerifyRemarks;

    @Column(name = "VISITING_CARD_NUMBER")
    private String visitingCardNumber;
    @Column(name = "POOR_ALLAVIATION_FLAG")
    private String poorAllaviationFlag;

    @Column(name = "REMARK")
    private String remark;


    public String getPoorAllaviationFlag() {
        return poorAllaviationFlag;
    }

    public void setPoorAllaviationFlag(String poorAllaviationFlag) {
        this.poorAllaviationFlag = poorAllaviationFlag;
    }

    public Medical() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmissionNo() {
        return this.admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getAdmissionType() {
        return this.admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getAduitStatus() {
        return this.aduitStatus;
    }

    public void setAduitStatus(String aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public BigDecimal getAge() {
        return this.age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public String getAttendingDocCode() {
        return this.attendingDocCode;
    }

    public void setAttendingDocCode(String attendingDocCode) {
        this.attendingDocCode = attendingDocCode;
    }

    public String getAttendingDocTitle() {
        return this.attendingDocTitle;
    }

    public void setAttendingDocTitle(String attendingDocTitle) {
        this.attendingDocTitle = attendingDocTitle;
    }

    public String getAuditMethods() {
        return this.auditMethods;
    }

    public void setAuditMethods(String auditMethods) {
        this.auditMethods = auditMethods;
    }

    public String getAuditStatus() {
        return this.auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getBalanceDate() {
        return this.balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public BigDecimal getBasicCostM() {
        return this.basicCostM;
    }

    public void setBasicCostM(BigDecimal basicCostM) {
        this.basicCostM = basicCostM;
    }

    public BigDecimal getBasicCostR() {
        return this.basicCostR;
    }

    public void setBasicCostR(BigDecimal basicCostR) {
        this.basicCostR = basicCostR;
    }

    public String getBillingNo() {
        return this.billingNo;
    }

    public void setBillingNo(String billingNo) {
        this.billingNo = billingNo;
    }

    public BigDecimal getCashCost() {
        return this.cashCost;
    }

    public void setCashCost(BigDecimal cashCost) {
        this.cashCost = cashCost;
    }

    public String getChangeHos() {
        return this.changeHos;
    }

    public void setChangeHos(String changeHos) {
        this.changeHos = changeHos;
    }

    public String getChargeNurse() {
        return this.chargeNurse;
    }

    public void setChargeNurse(String chargeNurse) {
        this.chargeNurse = chargeNurse;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public BigDecimal getCivilAffairSubsidy() {
        return this.civilAffairSubsidy;
    }

    public void setCivilAffairSubsidy(BigDecimal civilAffairSubsidy) {
        this.civilAffairSubsidy = civilAffairSubsidy;
    }

    public BigDecimal getClaimCost() {
        return this.claimCost;
    }

    public void setClaimCost(BigDecimal claimCost) {
        this.claimCost = claimCost;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCrowdType() {
        return this.crowdType;
    }

    public void setCrowdType(String crowdType) {
        this.crowdType = crowdType;
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

    public String getDiagType() {
        return this.diagType;
    }

    public void setDiagType(String diagType) {
        this.diagType = diagType;
    }

    public String getDirectorDoc() {
        return this.directorDoc;
    }

    public void setDirectorDoc(String directorDoc) {
        this.directorDoc = directorDoc;
    }

    public String getDischargeState() {
        return this.dischargeState;
    }

    public void setDischargeState(String dischargeState) {
        this.dischargeState = dischargeState;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getFinaStatus() {
        return this.finaStatus;
    }

    public void setFinaStatus(String finaStatus) {
        this.finaStatus = finaStatus;
    }

    public Date getFinaVerifyDate() {
        return this.finaVerifyDate;
    }

    public void setFinaVerifyDate(Date finaVerifyDate) {
        this.finaVerifyDate = finaVerifyDate;
    }

    public String getFinaVerifyMoney() {
        return this.finaVerifyMoney;
    }

    public void setFinaVerifyMoney(String finaVerifyMoney) {
        this.finaVerifyMoney = finaVerifyMoney;
    }

    public String getFinaVerifyPerson() {
        return this.finaVerifyPerson;
    }

    public void setFinaVerifyPerson(String finaVerifyPerson) {
        this.finaVerifyPerson = finaVerifyPerson;
    }

    public String getFinaVerifyRemarks() {
        return this.finaVerifyRemarks;
    }

    public void setFinaVerifyRemarks(String finaVerifyRemarks) {
        this.finaVerifyRemarks = finaVerifyRemarks;
    }

    public BigDecimal getFinanceSubsidy() {
        return this.financeSubsidy;
    }

    public void setFinanceSubsidy(BigDecimal financeSubsidy) {
        this.financeSubsidy = financeSubsidy;
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

    public String getHanddingPerson() {
        return this.handdingPerson;
    }

    public void setHanddingPerson(String handdingPerson) {
        this.handdingPerson = handdingPerson;
    }

    public Date getHandingTime() {
        return this.handingTime;
    }

    public void setHandingTime(Date handingTime) {
        this.handingTime = handingTime;
    }

    public String getHospCount() {
        return this.hospCount;
    }

    public void setHospCount(String hospCount) {
        this.hospCount = hospCount;
    }

    public String getHreadDepart() {
        return this.hreadDepart;
    }

    public void setHreadDepart(String hreadDepart) {
        this.hreadDepart = hreadDepart;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getInDiagnosisName() {
        return this.inDiagnosisName;
    }

    public void setInDiagnosisName(String inDiagnosisName) {
        this.inDiagnosisName = inDiagnosisName;
    }

    public String getInDiagnosisNo() {
        return this.inDiagnosisNo;
    }

    public void setInDiagnosisNo(String inDiagnosisNo) {
        this.inDiagnosisNo = inDiagnosisNo;
    }

    public String getInHosMethod() {
        return this.inHosMethod;
    }

    public void setInHosMethod(String inHosMethod) {
        this.inHosMethod = inHosMethod;
    }

    public String getInHosMethodCode() {
        return this.inHosMethodCode;
    }

    public void setInHosMethodCode(String inHosMethodCode) {
        this.inHosMethodCode = inHosMethodCode;
    }

    public Date getInhosDate() {
        return this.inhosDate;
    }

    public void setInhosDate(Date inhosDate) {
        this.inhosDate = inhosDate;
    }

    public Date getInsuranceCloseDate() {
        return this.insuranceCloseDate;
    }

    public void setInsuranceCloseDate(Date insuranceCloseDate) {
        this.insuranceCloseDate = insuranceCloseDate;
    }

    public String getInsuranceMark() {
        return this.insuranceMark;
    }

    public void setInsuranceMark(String insuranceMark) {
        this.insuranceMark = insuranceMark;
    }

    public BigDecimal getInsureCost() {
        return this.insureCost;
    }

    public void setInsureCost(BigDecimal insureCost) {
        this.insureCost = insureCost;
    }

    public String getInsurePersonCode() {
        return this.insurePersonCode;
    }

    public void setInsurePersonCode(String insurePersonCode) {
        this.insurePersonCode = insurePersonCode;
    }

    public String getInsurePersonType() {
        return this.insurePersonType;
    }

    public void setInsurePersonType(String insurePersonType) {
        this.insurePersonType = insurePersonType;
    }

    public Date getJhTime() {
        return this.jhTime;
    }

    public void setJhTime(Date jhTime) {
        this.jhTime = jhTime;
    }

    public BigDecimal getLargeCostM() {
        return this.largeCostM;
    }

    public void setLargeCostM(BigDecimal largeCostM) {
        this.largeCostM = largeCostM;
    }

    public BigDecimal getLargeCostR() {
        return this.largeCostR;
    }

    public void setLargeCostR(BigDecimal largeCostR) {
        this.largeCostR = largeCostR;
    }

    public String getMedicalCloseNumber() {
        return this.medicalCloseNumber;
    }

    public void setMedicalCloseNumber(String medicalCloseNumber) {
        this.medicalCloseNumber = medicalCloseNumber;
    }

    public String getMedicalTreatmentState() {
        return this.medicalTreatmentState;
    }

    public void setMedicalTreatmentState(String medicalTreatmentState) {
        this.medicalTreatmentState = medicalTreatmentState;
    }

    public String getMedicalType() {
        return this.medicalType;
    }

    public void setMedicalType(String medicalType) {
        this.medicalType = medicalType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNotInsureCost() {
        return this.notInsureCost;
    }

    public void setNotInsureCost(BigDecimal notInsureCost) {
        this.notInsureCost = notInsureCost;
    }

    public BigDecimal getOfficialSubsidy() {
        return this.officialSubsidy;
    }

    public void setOfficialSubsidy(BigDecimal officialSubsidy) {
        this.officialSubsidy = officialSubsidy;
    }

    public Date getOperationTime() {
        return this.operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOutDiagnosisName() {
        return this.outDiagnosisName;
    }

    public void setOutDiagnosisName(String outDiagnosisName) {
        this.outDiagnosisName = outDiagnosisName;
    }

    public String getOutDiagnosisNo() {
        return this.outDiagnosisNo;
    }

    public void setOutDiagnosisNo(String outDiagnosisNo) {
        this.outDiagnosisNo = outDiagnosisNo;
    }

    public String getOutHosDepart() {
        return this.outHosDepart;
    }

    public void setOutHosDepart(String outHosDepart) {
        this.outHosDepart = outHosDepart;
    }

    public String getOutHosDepartCode() {
        return this.outHosDepartCode;
    }

    public void setOutHosDepartCode(String outHosDepartCode) {
        this.outHosDepartCode = outHosDepartCode;
    }

    public String getOutHosMethod() {
        return this.outHosMethod;
    }

    public void setOutHosMethod(String outHosMethod) {
        this.outHosMethod = outHosMethod;
    }

    public String getOutHosMethodCode() {
        return this.outHosMethodCode;
    }

    public void setOutHosMethodCode(String outHosMethodCode) {
        this.outHosMethodCode = outHosMethodCode;
    }

    public String getOutHosSummary() {
        return this.outHosSummary;
    }

    public void setOutHosSummary(String outHosSummary) {
        this.outHosSummary = outHosSummary;
    }

    public Date getOuthosDate() {
        return this.outhosDate;
    }

    public void setOuthosDate(Date outhosDate) {
        this.outhosDate = outhosDate;
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

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPovertyAlleviationSubsidy() {
        return this.povertyAlleviationSubsidy;
    }

    public void setPovertyAlleviationSubsidy(BigDecimal povertyAlleviationSubsidy) {
        this.povertyAlleviationSubsidy = povertyAlleviationSubsidy;
    }

    public BigDecimal getRangeCost() {
        return this.rangeCost;
    }

    public void setRangeCost(BigDecimal rangeCost) {
        this.rangeCost = rangeCost;
    }

    public BigDecimal getRangeYearCost() {
        return this.rangeYearCost;
    }

    public void setRangeYearCost(BigDecimal rangeYearCost) {
        this.rangeYearCost = rangeYearCost;
    }

    public String getReimbursementType() {
        return this.reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public String getResidentDoc() {
        return this.residentDoc;
    }

    public void setResidentDoc(String residentDoc) {
        this.residentDoc = residentDoc;
    }

    public String getResultAppealStatu() {
        return this.resultAppealStatu;
    }

    public void setResultAppealStatu(String resultAppealStatu) {
        this.resultAppealStatu = resultAppealStatu;
    }

    public String getReversalMark() {
        return this.reversalMark;
    }

    public void setReversalMark(String reversalMark) {
        this.reversalMark = reversalMark;
    }

    public String getSeeDocDetail() {
        return this.seeDocDetail;
    }

    public void setSeeDocDetail(String seeDocDetail) {
        this.seeDocDetail = seeDocDetail;
    }

    public String getSeeDocType() {
        return this.seeDocType;
    }

    public void setSeeDocType(String seeDocType) {
        this.seeDocType = seeDocType;
    }

    public BigDecimal getSelfCost() {
        return this.selfCost;
    }

    public void setSelfCost(BigDecimal selfCost) {
        this.selfCost = selfCost;
    }

    public BigDecimal getSelfExpenditureAmount() {
        return this.selfExpenditureAmount;
    }

    public void setSelfExpenditureAmount(BigDecimal selfExpenditureAmount) {
        this.selfExpenditureAmount = selfExpenditureAmount;
    }

    public BigDecimal getSelfPayAmount() {
        return this.selfPayAmount;
    }

    public void setSelfPayAmount(BigDecimal selfPayAmount) {
        this.selfPayAmount = selfPayAmount;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getSscAccountCost() {
        return this.sscAccountCost;
    }

    public void setSscAccountCost(BigDecimal sscAccountCost) {
        this.sscAccountCost = sscAccountCost;
    }

    public String getSscno() {
        return this.sscno;
    }

    public void setSscno(String sscno) {
        this.sscno = sscno;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getStayLength() {
        return this.stayLength;
    }

    public void setStayLength(BigDecimal stayLength) {
        this.stayLength = stayLength;
    }

    public Date getSysAuditingDate() {
        return this.sysAuditingDate;
    }

    public void setSysAuditingDate(Date sysAuditingDate) {
        this.sysAuditingDate = sysAuditingDate;
    }

    public String getSysStatus() {
        return this.sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    public String getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getTreatmentType() {
        return this.treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getTreatmentWay() {
        return this.treatmentWay;
    }

    public void setTreatmentWay(String treatmentWay) {
        this.treatmentWay = treatmentWay;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserVerifyDate() {
        return this.userVerifyDate;
    }

    public void setUserVerifyDate(Date userVerifyDate) {
        this.userVerifyDate = userVerifyDate;
    }

    public String getUserVerifyDirection() {
        return this.userVerifyDirection;
    }

    public void setUserVerifyDirection(String userVerifyDirection) {
        this.userVerifyDirection = userVerifyDirection;
    }

    public String getUserVerifyMoney() {
        return this.userVerifyMoney;
    }

    public void setUserVerifyMoney(String userVerifyMoney) {
        this.userVerifyMoney = userVerifyMoney;
    }

    public String getUserVerifyPerson() {
        return this.userVerifyPerson;
    }

    public void setUserVerifyPerson(String userVerifyPerson) {
        this.userVerifyPerson = userVerifyPerson;
    }

    public String getUserVerifyRemarks() {
        return this.userVerifyRemarks;
    }

    public void setUserVerifyRemarks(String userVerifyRemarks) {
        this.userVerifyRemarks = userVerifyRemarks;
    }

    public String getVisitingCardNumber() {
        return this.visitingCardNumber;
    }

    public void setVisitingCardNumber(String visitingCardNumber) {
        this.visitingCardNumber = visitingCardNumber;
    }

    public String getHanddingInsCode() {
        return handdingInsCode;
    }

    public void setHanddingInsCode(String handdingInsCode) {
        this.handdingInsCode = handdingInsCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

	





