package com.dhcc.piccbid.entity.notExists;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_FLYCHECK_MEDICAL_MZ database table.
 * 
 */
@Entity
public class NotExsitsmzzd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String hisid;

	@Column(name="A_TYPE_AMOUNT")
	private String aTypeAmount;

	@Column(name="ACCOMMODATION_FEE")
	private String accommodationFee;

	@Column(name="ADMISSION_DEPT_NAME")
	private String admissionDeptName;

	@Column(name="ADMISSION_DISEASE_ID")
	private String admissionDiseaseId;

	@Column(name="ADMISSION_DISEASE_NAME")
	private String admissionDiseaseName;

	@Column(name="B_TYPE_AMOUNT")
	private String bTypeAmount;

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

	@Column(name="C_TYPE_AMOUNT")
	private String cTypeAmount;

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

	@Column(name="DIAGNOSIS_FEE")
	private String diagnosisFee;

	@Column(name="DOCTOR_ID")
	private String doctorId;

	@Column(name="DOCTOR_NAME")
	private String doctorName;

	@Column(name="HOSPITAL_ID")
	private String hospitalId;

	@Column(name="HOSPITAL_NAME")
	private String hospitalName;

	@Column(name="ICD9_CODE")
	private String icd9Code;

	@Column(name="ICD9_NAME")
	private String icd9Name;

	@Column(name="IF_LOCAL_FLAG")
	private String ifLocalFlag;

	@Column(name="INSPECTION_FEE")
	private String inspectionFee;

	@Column(name="MATERIAL_FEE")
	private String materialFee;

	@Column(name="\"MONTH\"")
	private String month;

	@Column(name="NURSING_FEE")
	private String nursingFee;

	@Column(name="OTHER_FEE")
	private String otherFee;

	@Column(name="P_LEVEL")
	private String pLevel;

	@Column(name="PATIENT_AGE")
	private String patientAge;

	@Column(name="PATIENT_BIRTHDAY")
	private String patientBirthday;

	@Column(name="PATIENT_GENDER")
	private String patientGender;

	@Column(name="PATIENT_ID")
	private String patientId;

	@Column(name="PATIENT_NAME")
	private String patientName;

	@Column(name="REGISTRATION_FEE")
	private String registrationFee;

	@Column(name="SELF_PAY_AMOUNT")
	private String selfPayAmount;

	@Column(name="SOCIAL_CARD_ID")
	private String socialCardId;

	@Column(name="SYS_STATUS")
	private String sysStatus;

	@Column(name="TEST_FEE")
	private String testFee;

	@Column(name="TOTAL_AMOUNT")
	private String totalAmount;

	@Column(name="TREATMENT_FEE")
	private String treatmentFee;

	@Column(name="WESTERN_MEDICINE_FEE")
	private String westernMedicineFee;

	@Column(name="\"YEAR\"")
	private String year;
	
	@Column(name="ZNUM")
	private String znum;
	
	@Column(name="ZCOST")
	private String zcost;
	
	public NotExsitsmzzd() {
	}

	public String getHisid() {
		return this.hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	/**
	 * @return the aTypeAmount
	 */
	public String getaTypeAmount() {
		return aTypeAmount;
	}

	/**
	 * @param aTypeAmount the aTypeAmount to set
	 */
	public void setaTypeAmount(String aTypeAmount) {
		this.aTypeAmount = aTypeAmount;
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
	 * @return the bTypeAmount
	 */
	public String getbTypeAmount() {
		return bTypeAmount;
	}

	/**
	 * @param bTypeAmount the bTypeAmount to set
	 */
	public void setbTypeAmount(String bTypeAmount) {
		this.bTypeAmount = bTypeAmount;
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
	 * @return the cTypeAmount
	 */
	public String getcTypeAmount() {
		return cTypeAmount;
	}

	/**
	 * @param cTypeAmount the cTypeAmount to set
	 */
	public void setcTypeAmount(String cTypeAmount) {
		this.cTypeAmount = cTypeAmount;
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
	 * @return the icd9Code
	 */
	public String getIcd9Code() {
		return icd9Code;
	}

	/**
	 * @param icd9Code the icd9Code to set
	 */
	public void setIcd9Code(String icd9Code) {
		this.icd9Code = icd9Code;
	}

	/**
	 * @return the icd9Name
	 */
	public String getIcd9Name() {
		return icd9Name;
	}

	/**
	 * @param icd9Name the icd9Name to set
	 */
	public void setIcd9Name(String icd9Name) {
		this.icd9Name = icd9Name;
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
	 * @return the patientBirthday
	 */
	public String getPatientBirthday() {
		return patientBirthday;
	}

	/**
	 * @param patientBirthday the patientBirthday to set
	 */
	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
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