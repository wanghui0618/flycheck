package com.dhcc.piccbid.entity.flycheckMedicalMz;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_FLYCHECK_MEDICAL_MZ database table.
 * 
 */
@Entity
@Table(name="T_FLYCHECK_MEDICAL_MZ")
public class FlycheckMedicalMz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="A_TYPE_AMOUNT", precision=22)
	private BigDecimal aTypeAmount;

	@Column(name="ACCOMMODATION_FEE", length=320)
	private String accommodationFee;

	@Column(name="ADMISSION_DEPT_NAME", length=320)
	private String admissionDeptName;

	@Column(name="ADMISSION_DISEASE_ID", length=320)
	private String admissionDiseaseId;

	@Column(name="ADMISSION_DISEASE_NAME", length=320)
	private String admissionDiseaseName;

	@Column(name="B_TYPE_AMOUNT", precision=22)
	private BigDecimal bTypeAmount;

	@Column(name="BENEFIT_GROUP_ID", length=320)
	private String benefitGroupId;

	@Column(name="BENEFIT_TYPE", length=320)
	private String benefitType;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATE")
	private Date billDate;

	@Column(name="BMI_AREA_ID", length=320)
	private String bmiAreaId;

	@Column(name="BMI_AREA_NAME", length=320)
	private String bmiAreaName;

	@Column(name="BMI_CONVERED_AMOUNT", precision=22)
	private BigDecimal bmiConveredAmount;

	@Column(name="BMI_PAY_AMOUNT", precision=22)
	private BigDecimal bmiPayAmount;

	@Column(name="C_TYPE_AMOUNT", precision=22)
	private BigDecimal cTypeAmount;

	@Column(precision=22)
	private BigDecimal cash;

	@Column(name="CHINESE_MEDICINE_FORM", length=320)
	private String chineseMedicineForm;

	@Column(name="CHINESE_MEDICINE_YINPIAN", length=320)
	private String chineseMedicineYinpian;

	@Column(name="CLAIM_TYPE", length=320)
	private String claimType;

	@Column(name="CONSULTATION_FEE", length=320)
	private String consultationFee;

	@Column(name="DIAGNOSIS_FEE", length=320)
	private String diagnosisFee;

	@Column(name="DOCTOR_ID", length=320)
	private String doctorId;

	@Column(name="DOCTOR_NAME", length=320)
	private String doctorName;

	@Id
	@Column(name = "HISID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
	private String hisid;

	@Column(name="HOSPITAL_ID", length=320)
	private String hospitalId;

	@Column(name="HOSPITAL_NAME", length=320)
	private String hospitalName;

	@Column(name="ICD9_CODE", length=320)
	private String icd9Code;

	@Column(name="ICD9_NAME", length=320)
	private String icd9Name;

	@Column(name="IF_LOCAL_FLAG", length=320)
	private String ifLocalFlag;

	@Column(name="INSPECTION_FEE", length=320)
	private String inspectionFee;

	@Column(name="MATERIAL_FEE", length=320)
	private String materialFee;

	@Column(name="\"MONTH\"", precision=22)
	private BigDecimal month;

	@Column(name="NURSING_FEE", length=320)
	private String nursingFee;

	@Column(name="OTHER_FEE", length=320)
	private String otherFee;

	@Column(name="P_LEVEL", length=320)
	private String pLevel;

	@Column(name="PATIENT_AGE", precision=22)
	private BigDecimal patientAge;

	@Temporal(TemporalType.DATE)
	@Column(name="PATIENT_BIRTHDAY")
	private Date patientBirthday;

	@Column(name="PATIENT_GENDER", length=320)
	private String patientGender;

	@Column(name="PATIENT_ID", length=320)
	private String patientId;

	@Column(name="PATIENT_NAME", length=320)
	private String patientName;

	@Column(name="REGISTRATION_FEE", length=320)
	private String registrationFee;

	@Column(name="SELF_PAY_AMOUNT", precision=22)
	private BigDecimal selfPayAmount;

	@Column(name="SOCIAL_CARD_ID", length=320)
	private String socialCardId;

	@Column(name="SYS_STATUS", length=3)
	private String sysStatus;

	@Column(name="TEST_FEE", length=320)
	private String testFee;

	@Column(name="TOTAL_AMOUNT", precision=22)
	private BigDecimal totalAmount;

	@Column(name="TREATMENT_FEE", length=320)
	private String treatmentFee;

	@Column(name="WESTERN_MEDICINE_FEE", length=320)
	private String westernMedicineFee;

	@Column(name="\"YEAR\"", precision=22)
	private BigDecimal year;

	public FlycheckMedicalMz() {
	}

	public BigDecimal getATypeAmount() {
		return this.aTypeAmount;
	}

	public void setATypeAmount(BigDecimal aTypeAmount) {
		this.aTypeAmount = aTypeAmount;
	}

	public String getAccommodationFee() {
		return this.accommodationFee;
	}

	public void setAccommodationFee(String accommodationFee) {
		this.accommodationFee = accommodationFee;
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

	public BigDecimal getBTypeAmount() {
		return this.bTypeAmount;
	}

	public void setBTypeAmount(BigDecimal bTypeAmount) {
		this.bTypeAmount = bTypeAmount;
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

	public BigDecimal getCTypeAmount() {
		return this.cTypeAmount;
	}

	public void setCTypeAmount(BigDecimal cTypeAmount) {
		this.cTypeAmount = cTypeAmount;
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

	public String getDiagnosisFee() {
		return this.diagnosisFee;
	}

	public void setDiagnosisFee(String diagnosisFee) {
		this.diagnosisFee = diagnosisFee;
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

	public String getHisid() {
		return this.hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
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

	public String getIcd9Code() {
		return this.icd9Code;
	}

	public void setIcd9Code(String icd9Code) {
		this.icd9Code = icd9Code;
	}

	public String getIcd9Name() {
		return this.icd9Name;
	}

	public void setIcd9Name(String icd9Name) {
		this.icd9Name = icd9Name;
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

	public BigDecimal getMonth() {
		return this.month;
	}

	public void setMonth(BigDecimal month) {
		this.month = month;
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

	public String getSocialCardId() {
		return this.socialCardId;
	}

	public void setSocialCardId(String socialCardId) {
		this.socialCardId = socialCardId;
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

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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

	public BigDecimal getYear() {
		return this.year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

}