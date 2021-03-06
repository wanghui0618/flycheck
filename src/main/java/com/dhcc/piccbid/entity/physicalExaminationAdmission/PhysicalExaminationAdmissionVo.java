package com.dhcc.piccbid.entity.physicalExaminationAdmission;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the T_FLYCHECK_MEDICAL database table.
 * 
 */
@Entity
public class PhysicalExaminationAdmissionVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String num;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Id
	private String hisid;

	@Column(name = "\"31DAYS_RE_ADMISSION\"")
	private BigDecimal _1daysReAdmission;

	@Column(name = "ACCOMMODATION_FEE")
	private String accommodationFee;

	@Temporal(TemporalType.DATE)
	@Column(name = "ADMISSION_DATE")
	private Date admissionDate;

	@Column(name = "ADMISSION_DEPT_NAME")
	private String admissionDeptName;

	@Column(name = "ADMISSION_DISEASE_ID")
	private String admissionDiseaseId;

	@Column(name = "ADMISSION_DISEASE_NAME")
	private String admissionDiseaseName;

	private String bedid;

	@Column(name = "BENEFIT_GROUP_ID")
	private String benefitGroupId;

	@Column(name = "BENEFIT_TYPE")
	private String benefitType;

	@Temporal(TemporalType.DATE)
	@Column(name = "BILL_DATE")
	private Date billDate;

	@Column(name = "BMI_AREA_ID")
	private String bmiAreaId;

	@Column(name = "BMI_AREA_NAME")
	private String bmiAreaName;

	@Column(name = "BMI_CONVERED_AMOUNT")
	private BigDecimal bmiConveredAmount;

	@Column(name = "BMI_PAY_AMOUNT")
	private BigDecimal bmiPayAmount;

	@Column(name = "BRIDGE_ID")
	private String bridgeId;

	private BigDecimal cash;

	@Column(name = "CHINESE_MEDICINE_FORM")
	private String chineseMedicineForm;

	@Column(name = "CHINESE_MEDICINE_YINPIAN")
	private String chineseMedicineYinpian;

	@Column(name = "CLAIM_TYPE")
	private String claimType;

	@Column(name = "CONSULTATION_FEE")
	private String consultationFee;

	private BigDecimal dbbx;

	private BigDecimal debc;

	@Column(name = "DIAGNOSIS_FEE")
	private String diagnosisFee;

	@Temporal(TemporalType.DATE)
	@Column(name = "DISCHARGE_DATE")
	private Date dischargeDate;

	@Column(name = "DISCHARGE_DEPT_NAME")
	private String dischargeDeptName;

	@Column(name = "DISCHARGE_DISEASE_ID_MAIN")
	private String dischargeDiseaseIdMain;

	@Column(name = "DISCHARGE_DISEASE_NAME_MAIN")
	private String dischargeDiseaseNameMain;

	@Column(name = "DISCHARGE_STATUS")
	private String dischargeStatus;

	@Column(name = "DOCTOR_ID")
	private String doctorId;

	@Column(name = "DOCTOR_NAME")
	private String doctorName;

	@Column(name = "DRGS_CODE")
	private String drgsCode;

	@Column(name = "DRGS_NAME")
	private String drgsName;

	private BigDecimal gwybz;

	@Column(name = "HOSPITAL_ID")
	private String hospitalId;

	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;

	@Column(name = "IF_LOCAL_FLAG")
	private String ifLocalFlag;

	@Column(name = "INSPECTION_FEE")
	private String inspectionFee;

	@Column(name = "MATERIAL_FEE")
	private String materialFee;

	@Column(name = "MEDICAL_RECORD_ID")
	private String medicalRecordId;

	@Column(name = "\"MONTH\"")
	private BigDecimal month;

	@Column(name = "NB_BIRTH_WEIGHT")
	private BigDecimal nbBirthWeight;

	@Column(name = "NB_INPATIENT_WEIGHT")
	private BigDecimal nbInpatientWeight;

	@Column(name = "NB_TYPE")
	private String nbType;

	@Column(name = "NURSING_FEE")
	private String nursingFee;

	@Column(name = "OTHER_FEE")
	private String otherFee;

	@Column(name = "P_LEVEL")
	private String pLevel;

	@Column(name = "PATIENT_ADDRESS")
	private String patientAddress;

	@Column(name = "PATIENT_AGE")
	private BigDecimal patientAge;

	@Temporal(TemporalType.DATE)
	@Column(name = "PATIENT_BIRTHDAY")
	private Date patientBirthday;

	@Column(name = "PATIENT_COMPANY")
	private String patientCompany;

	@Column(name = "PATIENT_GENDER")
	private String patientGender;

	@Column(name = "PATIENT_ID")
	private String patientId;

	@Column(name = "PATIENT_NAME")
	private String patientName;

	@Temporal(TemporalType.DATE)
	@Column(name = "PRE_ADMISSION_DATE")
	private Date preAdmissionDate;

	private BigDecimal qybc;

	@Column(name = "REGISTRATION_FEE")
	private String registrationFee;

	@Column(name = "SELF_PAY_AMOUNT")
	private BigDecimal selfPayAmount;

	@Column(name = "SELF_PAY_IN")
	private BigDecimal selfPayIn;

	@Column(name = "SELF_PAY_OUT")
	private BigDecimal selfPayOut;

	@Column(name = "SOCIAL_CARD_ID")
	private String socialCardId;

	@Column(name = "STAY_TIMES")
	private BigDecimal stayTimes;

	@Column(name = "SYS_STATUS")
	private String sysStatus;

	@Column(name = "TEST_FEE")
	private String testFee;

	@Column(name = "TOTAL_AMOUNT")
	private String totalAmount;

	@Column(name = "TRANSFER_DEPT_NAME")
	private String transferDeptName;

	@Column(name = "TREATMENT_FEE")
	private String treatmentFee;

	@Column(name = "WESTERN_MEDICINE_FEE")
	private String westernMedicineFee;

	@Column(name = "YB_PAY_TYPE")
	private BigDecimal ybPayType;

	@Column(name = "\"YEAR\"")
	private BigDecimal year;

	private BigDecimal yljz;

	private String zyh;

	private String zyts;
	
	private String jcfzb;
	
	private String drugfee;
	
	

	/**
	 * @return the jcfzb
	 */
	public String getJcfzb() {
		return jcfzb;
	}

	/**
	 * @param jcfzb the jcfzb to set
	 */
	public void setJcfzb(String jcfzb) {
		this.jcfzb = jcfzb;
	}

	/**
	 * @return the drugFee
	 */
	public String getDrugfee() {
		return drugfee;
	}

	/**
	 * @param drugFee the drugFee to set
	 */
	public void setDrugfee(String drugfee) {
		this.drugfee = drugfee;
	}

	public PhysicalExaminationAdmissionVo() {
	}

	public String getHisid() {
		return this.hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	public BigDecimal get_1daysReAdmission() {
		return this._1daysReAdmission;
	}

	public void set_1daysReAdmission(BigDecimal _1daysReAdmission) {
		this._1daysReAdmission = _1daysReAdmission;
	}

	public String getAccommodationFee() {
		return this.accommodationFee;
	}

	public void setAccommodationFee(String accommodationFee) {
		this.accommodationFee = accommodationFee;
	}

	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getAdmissionDeptName() {
		return this.admissionDeptName;
	}

	public void setAdmissionDeptName(String admissionDeptName) {
		this.admissionDeptName = admissionDeptName;
	}

	public String getAdmissionDiseaseId() {
		return this.admissionDiseaseId;
	}

	public void setAdmissionDiseaseId(String admissionDiseaseId) {
		this.admissionDiseaseId = admissionDiseaseId;
	}

	public String getAdmissionDiseaseName() {
		return this.admissionDiseaseName;
	}

	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	public String getBedid() {
		return this.bedid;
	}

	public void setBedid(String bedid) {
		this.bedid = bedid;
	}

	public String getBenefitGroupId() {
		return this.benefitGroupId;
	}

	public void setBenefitGroupId(String benefitGroupId) {
		this.benefitGroupId = benefitGroupId;
	}

	public String getBenefitType() {
		return this.benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBmiAreaId() {
		return this.bmiAreaId;
	}

	public void setBmiAreaId(String bmiAreaId) {
		this.bmiAreaId = bmiAreaId;
	}

	public String getBmiAreaName() {
		return this.bmiAreaName;
	}

	public void setBmiAreaName(String bmiAreaName) {
		this.bmiAreaName = bmiAreaName;
	}

	public BigDecimal getBmiConveredAmount() {
		return this.bmiConveredAmount;
	}

	public void setBmiConveredAmount(BigDecimal bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}

	public BigDecimal getBmiPayAmount() {
		return this.bmiPayAmount;
	}

	public void setBmiPayAmount(BigDecimal bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}

	public String getBridgeId() {
		return this.bridgeId;
	}

	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
	}

	public BigDecimal getCash() {
		return this.cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public String getChineseMedicineForm() {
		return this.chineseMedicineForm;
	}

	public void setChineseMedicineForm(String chineseMedicineForm) {
		this.chineseMedicineForm = chineseMedicineForm;
	}

	public String getChineseMedicineYinpian() {
		return this.chineseMedicineYinpian;
	}

	public void setChineseMedicineYinpian(String chineseMedicineYinpian) {
		this.chineseMedicineYinpian = chineseMedicineYinpian;
	}

	public String getClaimType() {
		return this.claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getConsultationFee() {
		return this.consultationFee;
	}

	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}

	public BigDecimal getDbbx() {
		return this.dbbx;
	}

	public void setDbbx(BigDecimal dbbx) {
		this.dbbx = dbbx;
	}

	public BigDecimal getDebc() {
		return this.debc;
	}

	public void setDebc(BigDecimal debc) {
		this.debc = debc;
	}

	public String getDiagnosisFee() {
		return this.diagnosisFee;
	}

	public void setDiagnosisFee(String diagnosisFee) {
		this.diagnosisFee = diagnosisFee;
	}

	public Date getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getDischargeDeptName() {
		return this.dischargeDeptName;
	}

	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}

	public String getDischargeDiseaseIdMain() {
		return this.dischargeDiseaseIdMain;
	}

	public void setDischargeDiseaseIdMain(String dischargeDiseaseIdMain) {
		this.dischargeDiseaseIdMain = dischargeDiseaseIdMain;
	}

	public String getDischargeDiseaseNameMain() {
		return this.dischargeDiseaseNameMain;
	}

	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
	}

	public String getDischargeStatus() {
		return this.dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDrgsCode() {
		return this.drgsCode;
	}

	public void setDrgsCode(String drgsCode) {
		this.drgsCode = drgsCode;
	}

	public String getDrgsName() {
		return this.drgsName;
	}

	public void setDrgsName(String drgsName) {
		this.drgsName = drgsName;
	}

	public BigDecimal getGwybz() {
		return this.gwybz;
	}

	public void setGwybz(BigDecimal gwybz) {
		this.gwybz = gwybz;
	}

	public String getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getIfLocalFlag() {
		return this.ifLocalFlag;
	}

	public void setIfLocalFlag(String ifLocalFlag) {
		this.ifLocalFlag = ifLocalFlag;
	}

	public String getInspectionFee() {
		return this.inspectionFee;
	}

	public void setInspectionFee(String inspectionFee) {
		this.inspectionFee = inspectionFee;
	}

	public String getMaterialFee() {
		return this.materialFee;
	}

	public void setMaterialFee(String materialFee) {
		this.materialFee = materialFee;
	}

	public String getMedicalRecordId() {
		return this.medicalRecordId;
	}

	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public BigDecimal getMonth() {
		return this.month;
	}

	public void setMonth(BigDecimal month) {
		this.month = month;
	}

	public BigDecimal getNbBirthWeight() {
		return this.nbBirthWeight;
	}

	public void setNbBirthWeight(BigDecimal nbBirthWeight) {
		this.nbBirthWeight = nbBirthWeight;
	}

	public BigDecimal getNbInpatientWeight() {
		return this.nbInpatientWeight;
	}

	public void setNbInpatientWeight(BigDecimal nbInpatientWeight) {
		this.nbInpatientWeight = nbInpatientWeight;
	}

	public String getNbType() {
		return this.nbType;
	}

	public void setNbType(String nbType) {
		this.nbType = nbType;
	}

	public String getNursingFee() {
		return this.nursingFee;
	}

	public void setNursingFee(String nursingFee) {
		this.nursingFee = nursingFee;
	}

	public String getOtherFee() {
		return this.otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	public String getPLevel() {
		return this.pLevel;
	}

	public void setPLevel(String pLevel) {
		this.pLevel = pLevel;
	}

	public String getPatientAddress() {
		return this.patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public BigDecimal getPatientAge() {
		return this.patientAge;
	}

	public void setPatientAge(BigDecimal patientAge) {
		this.patientAge = patientAge;
	}

	public Date getPatientBirthday() {
		return this.patientBirthday;
	}

	public void setPatientBirthday(Date patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public String getPatientCompany() {
		return this.patientCompany;
	}

	public void setPatientCompany(String patientCompany) {
		this.patientCompany = patientCompany;
	}

	public String getPatientGender() {
		return this.patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getPreAdmissionDate() {
		return this.preAdmissionDate;
	}

	public void setPreAdmissionDate(Date preAdmissionDate) {
		this.preAdmissionDate = preAdmissionDate;
	}

	public BigDecimal getQybc() {
		return this.qybc;
	}

	public void setQybc(BigDecimal qybc) {
		this.qybc = qybc;
	}

	public String getRegistrationFee() {
		return this.registrationFee;
	}

	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	public BigDecimal getSelfPayAmount() {
		return this.selfPayAmount;
	}

	public void setSelfPayAmount(BigDecimal selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	public BigDecimal getSelfPayIn() {
		return this.selfPayIn;
	}

	public void setSelfPayIn(BigDecimal selfPayIn) {
		this.selfPayIn = selfPayIn;
	}

	public BigDecimal getSelfPayOut() {
		return this.selfPayOut;
	}

	public void setSelfPayOut(BigDecimal selfPayOut) {
		this.selfPayOut = selfPayOut;
	}

	public String getSocialCardId() {
		return this.socialCardId;
	}

	public void setSocialCardId(String socialCardId) {
		this.socialCardId = socialCardId;
	}

	public BigDecimal getStayTimes() {
		return this.stayTimes;
	}

	public void setStayTimes(BigDecimal stayTimes) {
		this.stayTimes = stayTimes;
	}

	public String getSysStatus() {
		return this.sysStatus;
	}

	public void setSysStatus(String sysStatus) {
		this.sysStatus = sysStatus;
	}

	public String getTestFee() {
		return this.testFee;
	}

	public void setTestFee(String testFee) {
		this.testFee = testFee;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getZyts() {
		return zyts;
	}

	public void setZyts(String zyts) {
		this.zyts = zyts;
	}

	public String getTransferDeptName() {
		return this.transferDeptName;
	}

	public void setTransferDeptName(String transferDeptName) {
		this.transferDeptName = transferDeptName;
	}

	public String getTreatmentFee() {
		return this.treatmentFee;
	}

	public void setTreatmentFee(String treatmentFee) {
		this.treatmentFee = treatmentFee;
	}

	public String getWesternMedicineFee() {
		return this.westernMedicineFee;
	}

	public void setWesternMedicineFee(String westernMedicineFee) {
		this.westernMedicineFee = westernMedicineFee;
	}

	public BigDecimal getYbPayType() {
		return this.ybPayType;
	}

	public void setYbPayType(BigDecimal ybPayType) {
		this.ybPayType = ybPayType;
	}

	public BigDecimal getYear() {
		return this.year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public BigDecimal getYljz() {
		return this.yljz;
	}

	public void setYljz(BigDecimal yljz) {
		this.yljz = yljz;
	}

	public String getZyh() {
		return this.zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}
}