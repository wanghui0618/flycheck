package com.dhcc.piccbid.entity.notExists;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_FLYCHECK_MEDICAL database table.
 * 
 */
@Entity
public class NotExsitszyzd implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String bridgeId;

	@Column(name="\"31DAYS_RE_ADMISSION\"")
	private String _1daysReAdmission;

	@Column(name="ACCOMMODATION_FEE")
	private String accommodationFee;

	@Column(name="ADMISSION_DATE")
	private String admissionDate;

	@Column(name="ADMISSION_DEPT_NAME")
	private String admissionDeptName;

	@Column(name="ADMISSION_DISEASE_ID")
	private String admissionDiseaseId;

	@Column(name="ADMISSION_DISEASE_NAME")
	private String admissionDiseaseName;

	@Column(name="BEDID")
	private String bedid;

	@Column(name="BENEFIT_GROUP_ID")
	private String benefitGroupId;

	@Column(name="BENEFIT_TYPE")
	private String benefitType;

	
	@Column(name="BILL_DATE")
	private String billDate;

	@Column(name="BMI_AREA_ID")
	private String bmiAreaId;

	@Column(name="BMI_AREA_NAME")
	private String bmiAreaName;

	@Column(name="BMI_CONVERED_AMOUNT")
	private String bmiConveredAmount;

	@Column(name="BMI_PAY_AMOUNT")
	private String bmiPayAmount;

	@Column(name="CASH")
	private String cash;

	@Column(name="CHINESE_MEDICINE_FORM")
	private String chineseMedicineForm;

	@Column(name="CHINESE_MEDICINE_YINPIAN")
	private String chineseMedicineYinpian;

	@Column(name="CLAIM_TYPE")
	private String claimType;

	@Column(name="CONSULTATION_FEE")
	private String consultationFee;

	@Column(name="dbbx")
	private String dbbx;

	@Column(name="DEBC")
	private String debc;

	@Column(name="DIAGNOSIS_FEE")
	private String diagnosisFee;

	@Column(name="DISCHARGE_DATE")
	private String dischargeDate;

	@Column(name="DISCHARGE_DEPT_NAME")
	private String dischargeDeptName;

	@Column(name="DISCHARGE_DISEASE_ID_MAIN")
	private String dischargeDiseaseIdMain;

	@Column(name="DISCHARGE_DISEASE_NAME_MAIN")
	private String dischargeDiseaseNameMain;

	@Column(name="DISCHARGE_STATUS")
	private String dischargeStatus;

	@Column(name="DOCTOR_ID")
	private String doctorId;

	@Column(name="DOCTOR_NAME")
	private String doctorName;

	@Column(name="DRGS_CODE")
	private String drgsCode;

	@Column(name="DRGS_NAME")
	private String drgsName;

	@Column(name="GWYBZ")
	private String gwybz;

	@Column(name="HISID")
	private String hisid;

	@Column(name="HOSPITAL_ID")
	private String hospitalId;

	@Column(name="HOSPITAL_NAME")
	private String hospitalName;

	@Column(name="IF_LOCAL_FLAG")
	private String ifLocalFlag;

	@Column(name="INSPECTION_FEE")
	private String inspectionFee;

	@Column(name="MATERIAL_FEE")
	private String materialFee;

	@Column(name="MEDICAL_RECORD_ID")
	private String medicalRecordId;

	@Column(name="\"MONTH\"")
	private String month;

	@Column(name="NB_BIRTH_WEIGHT")
	private String nbBirthWeight;

	@Column(name="NB_INPATIENT_WEIGHT")
	private String nbInpatientWeight;

	@Column(name="NB_TYPE")
	private String nbType;

	@Column(name="NURSING_FEE")
	private String nursingFee;

	@Column(name="OTHER_FEE")
	private String otherFee;

	@Column(name="P_LEVEL")
	private String pLevel;

	@Column(name="PATIENT_ADDRESS")
	private String patientAddress;

	@Column(name="PATIENT_AGE")
	private String patientAge;

	
	@Column(name="PATIENT_BIRTHDAY")
	private String patientBirthday;

	

	@Column(name="PATIENT_COMPANY")
	private String patientCompany;

	@Column(name="PATIENT_GENDER")
	private String patientGender;

	@Column(name="PATIENT_ID")
	private String patientId;

	@Column(name="PATIENT_NAME")
	private String patientName;

	
	@Column(name="PRE_ADMISSION_DATE")
	private String preAdmissionDate;

	@Column
	private String qybc;

	@Column(name="REGISTRATION_FEE")
	private String registrationFee;

	@Column(name="SELF_PAY_AMOUNT")
	private String selfPayAmount;

	@Column(name="SELF_PAY_IN")
	private String selfPayIn;

	@Column(name="SELF_PAY_OUT")
	private String selfPayOut;

	@Column(name="SOCIAL_CARD_ID")
	private String socialCardId;

	@Column(name="STAY_TIMES")
	private String stayTimes;

	@Column(name="SYS_STATUS")
	private String sysStatus;

	@Column(name="TEST_FEE")
	private String testFee;

	@Column(name="TOTAL_AMOUNT")
	private String totalAmount;

	@Column(name="TRANSFER_DEPT_NAME")
	private String transferDeptName;

	@Column(name="TREATMENT_FEE")
	private String treatmentFee;

	@Column(name="WESTERN_MEDICINE_FEE")
	private String westernMedicineFee;

	@Column(name="YB_PAY_TYPE")
	private String ybPayType;

	@Column(name="\"YEAR\"")
	private String year;

	@Column(name="YLJZ")
	private String yljz;

	@Column(name="ZYH")
	private String zyh;

	@Column(name="ZYTS")
	private String zyts;
	
	@Column(name="ZNUM")
	private String znum;
	
	@Column(name="ZCOST")
	private String zcost;

	/**
	 * @return the _1daysReAdmission
	 */
	public String get_1daysReAdmission() {
		return _1daysReAdmission;
	}

	/**
	 * @param _1daysReAdmission the _1daysReAdmission to set
	 */
	public void set_1daysReAdmission(String _1daysReAdmission) {
		this._1daysReAdmission = _1daysReAdmission;
	}

	/**
	 * @return the accommodationFee
	 */
	public String getAccommodationFee() {
		return accommodationFee;
	}

	/**
	 * @param accommodationFee the accommodationFee to set
	 */
	public void setAccommodationFee(String accommodationFee) {
		this.accommodationFee = accommodationFee;
	}

	/**
	 * @return the admissionDate
	 */
	public String getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @return the admissionDeptName
	 */
	public String getAdmissionDeptName() {
		return admissionDeptName;
	}

	/**
	 * @param admissionDeptName the admissionDeptName to set
	 */
	public void setAdmissionDeptName(String admissionDeptName) {
		this.admissionDeptName = admissionDeptName;
	}

	/**
	 * @return the admissionDiseaseId
	 */
	public String getAdmissionDiseaseId() {
		return admissionDiseaseId;
	}

	/**
	 * @param admissionDiseaseId the admissionDiseaseId to set
	 */
	public void setAdmissionDiseaseId(String admissionDiseaseId) {
		this.admissionDiseaseId = admissionDiseaseId;
	}

	/**
	 * @return the admissionDiseaseName
	 */
	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}

	/**
	 * @param admissionDiseaseName the admissionDiseaseName to set
	 */
	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	/**
	 * @return the bedid
	 */
	public String getBedid() {
		return bedid;
	}

	/**
	 * @param bedid the bedid to set
	 */
	public void setBedid(String bedid) {
		this.bedid = bedid;
	}

	/**
	 * @return the benefitGroupId
	 */
	public String getBenefitGroupId() {
		return benefitGroupId;
	}

	/**
	 * @param benefitGroupId the benefitGroupId to set
	 */
	public void setBenefitGroupId(String benefitGroupId) {
		this.benefitGroupId = benefitGroupId;
	}

	/**
	 * @return the benefitType
	 */
	public String getBenefitType() {
		return benefitType;
	}

	/**
	 * @param benefitType the benefitType to set
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return the bmiAreaId
	 */
	public String getBmiAreaId() {
		return bmiAreaId;
	}

	/**
	 * @param bmiAreaId the bmiAreaId to set
	 */
	public void setBmiAreaId(String bmiAreaId) {
		this.bmiAreaId = bmiAreaId;
	}

	/**
	 * @return the bmiAreaName
	 */
	public String getBmiAreaName() {
		return bmiAreaName;
	}

	/**
	 * @param bmiAreaName the bmiAreaName to set
	 */
	public void setBmiAreaName(String bmiAreaName) {
		this.bmiAreaName = bmiAreaName;
	}

	/**
	 * @return the bmiConveredAmount
	 */
	public String getBmiConveredAmount() {
		return bmiConveredAmount;
	}

	/**
	 * @param bmiConveredAmount the bmiConveredAmount to set
	 */
	public void setBmiConveredAmount(String bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}

	/**
	 * @return the bmiPayAmount
	 */
	public String getBmiPayAmount() {
		return bmiPayAmount;
	}

	/**
	 * @param bmiPayAmount the bmiPayAmount to set
	 */
	public void setBmiPayAmount(String bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}

	/**
	 * @return the bridgeId
	 */
	public String getBridgeId() {
		return bridgeId;
	}

	/**
	 * @param bridgeId the bridgeId to set
	 */
	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
	}

	/**
	 * @return the cash
	 */
	public String getCash() {
		return cash;
	}

	/**
	 * @param cash the cash to set
	 */
	public void setCash(String cash) {
		this.cash = cash;
	}

	/**
	 * @return the chineseMedicineForm
	 */
	public String getChineseMedicineForm() {
		return chineseMedicineForm;
	}

	/**
	 * @param chineseMedicineForm the chineseMedicineForm to set
	 */
	public void setChineseMedicineForm(String chineseMedicineForm) {
		this.chineseMedicineForm = chineseMedicineForm;
	}

	/**
	 * @return the chineseMedicineYinpian
	 */
	public String getChineseMedicineYinpian() {
		return chineseMedicineYinpian;
	}

	/**
	 * @param chineseMedicineYinpian the chineseMedicineYinpian to set
	 */
	public void setChineseMedicineYinpian(String chineseMedicineYinpian) {
		this.chineseMedicineYinpian = chineseMedicineYinpian;
	}

	/**
	 * @return the claimType
	 */
	public String getClaimType() {
		return claimType;
	}

	/**
	 * @param claimType the claimType to set
	 */
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	/**
	 * @return the consultationFee
	 */
	public String getConsultationFee() {
		return consultationFee;
	}

	/**
	 * @param consultationFee the consultationFee to set
	 */
	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}

	/**
	 * @return the dbbx
	 */
	public String getDbbx() {
		return dbbx;
	}

	/**
	 * @param dbbx the dbbx to set
	 */
	public void setDbbx(String dbbx) {
		this.dbbx = dbbx;
	}

	/**
	 * @return the debc
	 */
	public String getDebc() {
		return debc;
	}

	/**
	 * @param debc the debc to set
	 */
	public void setDebc(String debc) {
		this.debc = debc;
	}

	/**
	 * @return the diagnosisFee
	 */
	public String getDiagnosisFee() {
		return diagnosisFee;
	}

	/**
	 * @param diagnosisFee the diagnosisFee to set
	 */
	public void setDiagnosisFee(String diagnosisFee) {
		this.diagnosisFee = diagnosisFee;
	}

	/**
	 * @return the dischargeDate
	 */
	public String getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	/**
	 * @return the dischargeDeptName
	 */
	public String getDischargeDeptName() {
		return dischargeDeptName;
	}

	/**
	 * @param dischargeDeptName the dischargeDeptName to set
	 */
	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}

	/**
	 * @return the dischargeDiseaseIdMain
	 */
	public String getDischargeDiseaseIdMain() {
		return dischargeDiseaseIdMain;
	}

	/**
	 * @param dischargeDiseaseIdMain the dischargeDiseaseIdMain to set
	 */
	public void setDischargeDiseaseIdMain(String dischargeDiseaseIdMain) {
		this.dischargeDiseaseIdMain = dischargeDiseaseIdMain;
	}

	/**
	 * @return the dischargeDiseaseNameMain
	 */
	public String getDischargeDiseaseNameMain() {
		return dischargeDiseaseNameMain;
	}

	/**
	 * @param dischargeDiseaseNameMain the dischargeDiseaseNameMain to set
	 */
	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
	}

	/**
	 * @return the dischargeStatus
	 */
	public String getDischargeStatus() {
		return dischargeStatus;
	}

	/**
	 * @param dischargeStatus the dischargeStatus to set
	 */
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	/**
	 * @return the doctorId
	 */
	public String getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	 * @return the drgsCode
	 */
	public String getDrgsCode() {
		return drgsCode;
	}

	/**
	 * @param drgsCode the drgsCode to set
	 */
	public void setDrgsCode(String drgsCode) {
		this.drgsCode = drgsCode;
	}

	/**
	 * @return the drgsName
	 */
	public String getDrgsName() {
		return drgsName;
	}

	/**
	 * @param drgsName the drgsName to set
	 */
	public void setDrgsName(String drgsName) {
		this.drgsName = drgsName;
	}

	/**
	 * @return the gwybz
	 */
	public String getGwybz() {
		return gwybz;
	}

	/**
	 * @param gwybz the gwybz to set
	 */
	public void setGwybz(String gwybz) {
		this.gwybz = gwybz;
	}

	/**
	 * @return the hisid
	 */
	public String getHisid() {
		return hisid;
	}

	/**
	 * @param hisid the hisid to set
	 */
	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	/**
	 * @return the hospitalId
	 */
	public String getHospitalId() {
		return hospitalId;
	}

	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @return the ifLocalFlag
	 */
	public String getIfLocalFlag() {
		return ifLocalFlag;
	}

	/**
	 * @param ifLocalFlag the ifLocalFlag to set
	 */
	public void setIfLocalFlag(String ifLocalFlag) {
		this.ifLocalFlag = ifLocalFlag;
	}

	/**
	 * @return the inspectionFee
	 */
	public String getInspectionFee() {
		return inspectionFee;
	}

	/**
	 * @param inspectionFee the inspectionFee to set
	 */
	public void setInspectionFee(String inspectionFee) {
		this.inspectionFee = inspectionFee;
	}

	/**
	 * @return the materialFee
	 */
	public String getMaterialFee() {
		return materialFee;
	}

	/**
	 * @param materialFee the materialFee to set
	 */
	public void setMaterialFee(String materialFee) {
		this.materialFee = materialFee;
	}

	/**
	 * @return the medicalRecordId
	 */
	public String getMedicalRecordId() {
		return medicalRecordId;
	}

	/**
	 * @param medicalRecordId the medicalRecordId to set
	 */
	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the nbBirthWeight
	 */
	public String getNbBirthWeight() {
		return nbBirthWeight;
	}

	/**
	 * @param nbBirthWeight the nbBirthWeight to set
	 */
	public void setNbBirthWeight(String nbBirthWeight) {
		this.nbBirthWeight = nbBirthWeight;
	}

	/**
	 * @return the nbInpatientWeight
	 */
	public String getNbInpatientWeight() {
		return nbInpatientWeight;
	}

	/**
	 * @param nbInpatientWeight the nbInpatientWeight to set
	 */
	public void setNbInpatientWeight(String nbInpatientWeight) {
		this.nbInpatientWeight = nbInpatientWeight;
	}

	/**
	 * @return the nbType
	 */
	public String getNbType() {
		return nbType;
	}

	/**
	 * @param nbType the nbType to set
	 */
	public void setNbType(String nbType) {
		this.nbType = nbType;
	}

	/**
	 * @return the nursingFee
	 */
	public String getNursingFee() {
		return nursingFee;
	}

	/**
	 * @param nursingFee the nursingFee to set
	 */
	public void setNursingFee(String nursingFee) {
		this.nursingFee = nursingFee;
	}

	/**
	 * @return the otherFee
	 */
	public String getOtherFee() {
		return otherFee;
	}

	/**
	 * @param otherFee the otherFee to set
	 */
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	/**
	 * @return the pLevel
	 */
	public String getpLevel() {
		return pLevel;
	}

	/**
	 * @param pLevel the pLevel to set
	 */
	public void setpLevel(String pLevel) {
		this.pLevel = pLevel;
	}

	/**
	 * @return the patientAddress
	 */
	public String getPatientAddress() {
		return patientAddress;
	}

	/**
	 * @param patientAddress the patientAddress to set
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * @return the patientAge
	 */
	public String getPatientAge() {
		return patientAge;
	}

	/**
	 * @param patientAge the patientAge to set
	 */
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}



	/**
	 * @return the patientCompany
	 */
	public String getPatientCompany() {
		return patientCompany;
	}

	/**
	 * @param patientCompany the patientCompany to set
	 */
	public void setPatientCompany(String patientCompany) {
		this.patientCompany = patientCompany;
	}

	/**
	 * @return the patientGender
	 */
	public String getPatientGender() {
		return patientGender;
	}

	/**
	 * @param patientGender the patientGender to set
	 */
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
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
	 * @return the preAdmissionDate
	 */
	public String getPreAdmissionDate() {
		return preAdmissionDate;
	}

	/**
	 * @param preAdmissionDate the preAdmissionDate to set
	 */
	public void setPreAdmissionDate(String preAdmissionDate) {
		this.preAdmissionDate = preAdmissionDate;
	}

	/**
	 * @param patientBirthday the patientBirthday to set
	 */
	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
	}
	/**
	 * @return the patientBirthday
	 */
	public String getPatientBirthday() {
		return patientBirthday;
	}
	/**
	 * @return the qybc
	 */
	public String getQybc() {
		return qybc;
	}

	/**
	 * @param qybc the qybc to set
	 */
	public void setQybc(String qybc) {
		this.qybc = qybc;
	}

	/**
	 * @return the registrationFee
	 */
	public String getRegistrationFee() {
		return registrationFee;
	}

	/**
	 * @param registrationFee the registrationFee to set
	 */
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	/**
	 * @return the selfPayAmount
	 */
	public String getSelfPayAmount() {
		return selfPayAmount;
	}

	/**
	 * @param selfPayAmount the selfPayAmount to set
	 */
	public void setSelfPayAmount(String selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	/**
	 * @return the selfPayIn
	 */
	public String getSelfPayIn() {
		return selfPayIn;
	}

	/**
	 * @param selfPayIn the selfPayIn to set
	 */
	public void setSelfPayIn(String selfPayIn) {
		this.selfPayIn = selfPayIn;
	}

	/**
	 * @return the selfPayOut
	 */
	public String getSelfPayOut() {
		return selfPayOut;
	}

	/**
	 * @param selfPayOut the selfPayOut to set
	 */
	public void setSelfPayOut(String selfPayOut) {
		this.selfPayOut = selfPayOut;
	}

	/**
	 * @return the socialCardId
	 */
	public String getSocialCardId() {
		return socialCardId;
	}

	/**
	 * @param socialCardId the socialCardId to set
	 */
	public void setSocialCardId(String socialCardId) {
		this.socialCardId = socialCardId;
	}

	/**
	 * @return the stayTimes
	 */
	public String getStayTimes() {
		return stayTimes;
	}

	/**
	 * @param stayTimes the stayTimes to set
	 */
	public void setStayTimes(String stayTimes) {
		this.stayTimes = stayTimes;
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
	 * @return the testFee
	 */
	public String getTestFee() {
		return testFee;
	}

	/**
	 * @param testFee the testFee to set
	 */
	public void setTestFee(String testFee) {
		this.testFee = testFee;
	}

	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the transferDeptName
	 */
	public String getTransferDeptName() {
		return transferDeptName;
	}

	/**
	 * @param transferDeptName the transferDeptName to set
	 */
	public void setTransferDeptName(String transferDeptName) {
		this.transferDeptName = transferDeptName;
	}

	/**
	 * @return the treatmentFee
	 */
	public String getTreatmentFee() {
		return treatmentFee;
	}

	/**
	 * @param treatmentFee the treatmentFee to set
	 */
	public void setTreatmentFee(String treatmentFee) {
		this.treatmentFee = treatmentFee;
	}

	/**
	 * @return the westernMedicineFee
	 */
	public String getWesternMedicineFee() {
		return westernMedicineFee;
	}

	/**
	 * @param westernMedicineFee the westernMedicineFee to set
	 */
	public void setWesternMedicineFee(String westernMedicineFee) {
		this.westernMedicineFee = westernMedicineFee;
	}

	/**
	 * @return the ybPayType
	 */
	public String getYbPayType() {
		return ybPayType;
	}

	/**
	 * @param ybPayType the ybPayType to set
	 */
	public void setYbPayType(String ybPayType) {
		this.ybPayType = ybPayType;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the yljz
	 */
	public String getYljz() {
		return yljz;
	}

	/**
	 * @param yljz the yljz to set
	 */
	public void setYljz(String yljz) {
		this.yljz = yljz;
	}

	/**
	 * @return the zyh
	 */
	public String getZyh() {
		return zyh;
	}

	/**
	 * @param zyh the zyh to set
	 */
	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	/**
	 * @return the zyts
	 */
	public String getZyts() {
		return zyts;
	}

	/**
	 * @param zyts the zyts to set
	 */
	public void setZyts(String zyts) {
		this.zyts = zyts;
	}

	/**
	 * @return the znum
	 */
	public String getZnum() {
		return znum;
	}

	/**
	 * @param znum the znum to set
	 */
	public void setZnum(String znum) {
		this.znum = znum;
	}

	/**
	 * @return the zcost
	 */
	public String getZcost() {
		return zcost;
	}

	/**
	 * @param zcost the zcost to set
	 */
	public void setZcost(String zcost) {
		this.zcost = zcost;
	}
	
	
	

}