package com.dhcc.piccbid.entity.flyMedicalInhos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_FLY_MEDICAL_INHOS")
public class FlyMedicalInhos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "BRIDGE_ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String bridgeId;
	
	@Column(name="HISID")
	private String hisid;
	
	@Column(name="HOSPITAL_ID")
	private String hospitalId;

	@Column(name="HOSPITAL_NAME")
	private String hospitalName;

	@Column(name="P_LEVEL")
	private String pLevel;
	
	@Column(name="BMI_AREA_ID")
	private String bmiAreaId;
	
	@Column(name="BMI_AREA_NAME")
	private String bmiAreaName;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATE")
	private Date billDate;

	@Column(name="YEAR")
	private BigDecimal year;

	@Column(name="MONTH")
	private BigDecimal month;

	@Column(name="ZYH")
	private String zyh;

	@Column(name="PATIENT_ID")
	private String patientId;
	
	@Column(name="SOCIAL_CARD_ID")
	private String socialCardId;
	
	@Column(name="MEDICAL_RECORD_ID")
	private String medicalRecordId;

	@Column(name="BENEFIT_TYPE")
	private String benefitType;

	@Column(name="BENEFIT_GROUP_ID")
	private String benefitGroupId;
	
	@Column(name="ADMISSION_DEPT_NAME")
	private String admissionDeptName;
	
	@Column(name="TRANSFER_DEPT_NAME")
	private String transferDeptName;
	
	@Column(name="DISCHARGE_DEPT_NAME")
	private String dischargeDeptName;
	
	@Column(name="DOCTOR_ID")
	private String doctorId;

	@Column(name="DOCTOR_NAME")
	private String doctorName;

	@Column(name="PATIENT_NAME")
	private String patientName;
	
	@Column(name="PATIENT_GENDER")
	private String patientGender;

	@Temporal(TemporalType.DATE)
	@Column(name="PATIENT_BIRTHDAY")
	private Date patientBirthday;

	@Column(name="PATIENT_AGE")
	private BigDecimal patientAge;
	
	@Column(name="PATIENT_COMPANY")
	private String patientCompany;
	
	@Column(name="PATIENT_ADDRESS")
	private String patientAddress;
	
	@Column(name="NB_TYPE")
	private String nbType;

	@Column(name="NB_BIRTH_WEIGHT")
	private BigDecimal nbBirthWeight;

	@Column(name="NB_INPATIENT_WEIGHT")
	private BigDecimal nbInpatientWeight;

	@Column(name="CLAIM_TYPE")
	private String claimType;

	@Column(name="IF_LOCAL_FLAG")
	private String ifLocalFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="ADMISSION_DATE")
	private Date admissionDate;

	@Temporal(TemporalType.DATE)
	@Column(name="DISCHARGE_DATE")
	private Date dischargeDate;

	@Column(name="ZYTS")
	private BigDecimal zyts;
	
	@Column(name="DISCHARGE_STATUS")
	private String dischargeStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="PRE_ADMISSION_DATE")
	private Date perAdmissionDate;

	@Column(name="DAYS_RE_ADMISSION31")
	private BigDecimal daysReAdmission31;

	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name="BMI_PAY_AMOUNT")
	private BigDecimal bmiPayAmount;

	@Column(name="DBBX")
	private BigDecimal dbbx;

	@Column(name="YLJZ")
	private BigDecimal yljz;

	@Column(name="GWYBZ")
	private BigDecimal gwybz;

	@Column(name="DEBC")
	private BigDecimal debc;

	@Column(name="QYBC")
	private BigDecimal qybc;

	@Column(name="CASH")
	private BigDecimal cash;

	@Column(name="SELF_PAY_AMOUNT")
	private BigDecimal selfPayAmount;

	@Column(name="SELF_PAY_IN")
	private BigDecimal selfPayIn;

	@Column(name="SELF_PAY_OUT")
	private BigDecimal selfPayOut;

	@Column(name="BMI_CONVERED_AMOUNT")
	private BigDecimal bmiConveredAmount;
	
	@Column(name="ADMISSION_DISEASE_ID")
	private String admissionDiseaseId;

	@Column(name="ADMISSION_DISEASE_NAME")
	private String admissionDiseaseName;

	@Column(name="DISCHARGE_DISEASE_ID_MAIN")
	private String dischargeDiseaseIdMain;
	
	@Column(name="DISCHARGE_DISEASE_NAME_MAIN")
	private String dischargeDiseaseNameMain;
	
	@Column(name="ACCOMMODATION_FEE")
	private String accommodationFee;

	@Column(name="DIAGNOSIS_FEE")
	private String diagnosisFee;

	@Column(name="INSPECTION_FEE")
	private String inspectionFee;
	
	@Column(name="TEST_FEE")
	private String testFee;
	
	@Column(name="TREATMENT_FEE")
	private String treatmentFee;
	
	@Column(name="NURSING_FEE")
	private String nursingFee;
	
	@Column(name="MATERIAL_FEE")
	private String materialFee;

	@Column(name="WESTERN_MEDICINE_FEE")
	private String westernMedicineFee;

	@Column(name="CHINESE_MEDICINE_YINPIAN")
	private String chineseMedicineYinpian;
	
	@Column(name="CHINESE_MEDICINE_FORM")
	private String chineseMedicineForm;
	
	@Column(name="CONSULTATION_FEE")
	private String consultationFee;
	
	@Column(name="REGISTRATION_FEE")
	private String registrationFee;

	@Column(name="OTHER_FEE")
	private String otherFee;

	@Column(name="YB_PAY_TYPE")
	private BigDecimal ybPayType;

	@Column(name="DRGS_CODE")
	private String drgsCode;
	
	@Column(name="DRGS_NAME")
	private String drgsName;

	public String getBridgeId() {
		return bridgeId;
	}

	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
	}

	public String getHisid() {
		return hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getpLevel() {
		return pLevel;
	}

	public void setpLevel(String pLevel) {
		this.pLevel = pLevel;
	}

	public String getBmiAreaId() {
		return bmiAreaId;
	}

	public void setBmiAreaId(String bmiAreaId) {
		this.bmiAreaId = bmiAreaId;
	}

	public String getBmiAreaName() {
		return bmiAreaName;
	}

	public void setBmiAreaName(String bmiAreaName) {
		this.bmiAreaName = bmiAreaName;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public BigDecimal getYear() {
		return year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public BigDecimal getMonth() {
		return month;
	}

	public void setMonth(BigDecimal month) {
		this.month = month;
	}

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(String socialCardId) {
		this.socialCardId = socialCardId;
	}

	public String getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
	}

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public String getBenefitGroupId() {
		return benefitGroupId;
	}

	public void setBenefitGroupId(String benefitGroupId) {
		this.benefitGroupId = benefitGroupId;
	}

	public String getAdmissionDeptName() {
		return admissionDeptName;
	}

	public void setAdmissionDeptName(String admissionDeptName) {
		this.admissionDeptName = admissionDeptName;
	}

	public String getTransferDeptName() {
		return transferDeptName;
	}

	public void setTransferDeptName(String transferDeptName) {
		this.transferDeptName = transferDeptName;
	}

	public String getDischargeDeptName() {
		return dischargeDeptName;
	}

	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public Date getPatientBirthday() {
		return patientBirthday;
	}

	public void setPatientBirthday(Date patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public BigDecimal getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(BigDecimal patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientCompany() {
		return patientCompany;
	}

	public void setPatientCompany(String patientCompany) {
		this.patientCompany = patientCompany;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getNbType() {
		return nbType;
	}

	public void setNbType(String nbType) {
		this.nbType = nbType;
	}

	public BigDecimal getNbBirthWeight() {
		return nbBirthWeight;
	}

	public void setNbBirthWeight(BigDecimal nbBirthWeight) {
		this.nbBirthWeight = nbBirthWeight;
	}

	public BigDecimal getNbInpatientWeight() {
		return nbInpatientWeight;
	}

	public void setNbInpatientWeight(BigDecimal nbInpatientWeight) {
		this.nbInpatientWeight = nbInpatientWeight;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getIfLocalFlag() {
		return ifLocalFlag;
	}

	public void setIfLocalFlag(String ifLocalFlag) {
		this.ifLocalFlag = ifLocalFlag;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public BigDecimal getZyts() {
		return zyts;
	}

	public void setZyts(BigDecimal zyts) {
		this.zyts = zyts;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public Date getPerAdmissionDate() {
		return perAdmissionDate;
	}

	public void setPerAdmissionDate(Date perAdmissionDate) {
		this.perAdmissionDate = perAdmissionDate;
	}

	public BigDecimal getDaysReAdmission31() {
		return daysReAdmission31;
	}

	public void setDaysReAdmission31(BigDecimal daysReAdmission31) {
		this.daysReAdmission31 = daysReAdmission31;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBmiPayAmount() {
		return bmiPayAmount;
	}

	public void setBmiPayAmount(BigDecimal bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}

	public BigDecimal getDbbx() {
		return dbbx;
	}

	public void setDbbx(BigDecimal dbbx) {
		this.dbbx = dbbx;
	}

	public BigDecimal getYljz() {
		return yljz;
	}

	public void setYljz(BigDecimal yljz) {
		this.yljz = yljz;
	}

	public BigDecimal getGwybz() {
		return gwybz;
	}

	public void setGwybz(BigDecimal gwybz) {
		this.gwybz = gwybz;
	}

	public BigDecimal getDebc() {
		return debc;
	}

	public void setDebc(BigDecimal debc) {
		this.debc = debc;
	}

	public BigDecimal getQybc() {
		return qybc;
	}

	public void setQybc(BigDecimal qybc) {
		this.qybc = qybc;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public BigDecimal getSelfPayAmount() {
		return selfPayAmount;
	}

	public void setSelfPayAmount(BigDecimal selfPayAmount) {
		this.selfPayAmount = selfPayAmount;
	}

	public BigDecimal getSelfPayIn() {
		return selfPayIn;
	}

	public void setSelfPayIn(BigDecimal selfPayIn) {
		this.selfPayIn = selfPayIn;
	}

	public BigDecimal getSelfPayOut() {
		return selfPayOut;
	}

	public void setSelfPayOut(BigDecimal selfPayOut) {
		this.selfPayOut = selfPayOut;
	}

	public BigDecimal getBmiConveredAmount() {
		return bmiConveredAmount;
	}

	public void setBmiConveredAmount(BigDecimal bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}

	public String getAdmissionDiseaseId() {
		return admissionDiseaseId;
	}

	public void setAdmissionDiseaseId(String admissionDiseaseId) {
		this.admissionDiseaseId = admissionDiseaseId;
	}

	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}

	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	public String getDischargeDiseaseIdMain() {
		return dischargeDiseaseIdMain;
	}

	public void setDischargeDiseaseIdMain(String dischargeDiseaseIdMain) {
		this.dischargeDiseaseIdMain = dischargeDiseaseIdMain;
	}

	public String getDischargeDiseaseNameMain() {
		return dischargeDiseaseNameMain;
	}

	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
	}

	public String getAccommodationFee() {
		return accommodationFee;
	}

	public void setAccommodationFee(String accommodationFee) {
		this.accommodationFee = accommodationFee;
	}

	public String getDiagnosisFee() {
		return diagnosisFee;
	}

	public void setDiagnosisFee(String diagnosisFee) {
		this.diagnosisFee = diagnosisFee;
	}

	public String getInspectionFee() {
		return inspectionFee;
	}

	public void setInspectionFee(String inspectionFee) {
		this.inspectionFee = inspectionFee;
	}

	public String getTestFee() {
		return testFee;
	}

	public void setTestFee(String testFee) {
		this.testFee = testFee;
	}

	public String getTreatmentFee() {
		return treatmentFee;
	}

	public void setTreatmentFee(String treatmentFee) {
		this.treatmentFee = treatmentFee;
	}

	public String getNursingFee() {
		return nursingFee;
	}

	public void setNursingFee(String nursingFee) {
		this.nursingFee = nursingFee;
	}

	public String getMaterialFee() {
		return materialFee;
	}

	public void setMaterialFee(String materialFee) {
		this.materialFee = materialFee;
	}

	public String getWesternMedicineFee() {
		return westernMedicineFee;
	}

	public void setWesternMedicineFee(String westernMedicineFee) {
		this.westernMedicineFee = westernMedicineFee;
	}

	public String getChineseMedicineYinpian() {
		return chineseMedicineYinpian;
	}

	public void setChineseMedicineYinpian(String chineseMedicineYinpian) {
		this.chineseMedicineYinpian = chineseMedicineYinpian;
	}

	public String getChineseMedicineForm() {
		return chineseMedicineForm;
	}

	public void setChineseMedicineForm(String chineseMedicineForm) {
		this.chineseMedicineForm = chineseMedicineForm;
	}

	public String getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}

	public String getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getYbPayType() {
		return ybPayType;
	}

	public void setYbPayType(BigDecimal ybPayType) {
		this.ybPayType = ybPayType;
	}

	public String getDrgsCode() {
		return drgsCode;
	}

	public void setDrgsCode(String drgsCode) {
		this.drgsCode = drgsCode;
	}

	public String getDrgsName() {
		return drgsName;
	}

	public void setDrgsName(String drgsName) {
		this.drgsName = drgsName;
	}
	
}
