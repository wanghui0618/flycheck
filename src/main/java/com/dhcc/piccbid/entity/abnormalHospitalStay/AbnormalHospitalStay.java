package com.dhcc.piccbid.entity.abnormalHospitalStay;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the T_FLY_CONDITION database table.
 * 
 */
@Entity

public class AbnormalHospitalStay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "HOSPITAL_ID")
	private String hospitalId;

	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;

	public String getHisid() {
		return hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	@Column(name = "ZYH")
	private String zyh;

	@Column(name = "PATIENT_ID")
	private String patientId;

	@Column(name = "ADMISSION_DEPT_NAME")
	private String admissionDeptName;

	@Column(name = "PATIENT_NAME")
	private String patientName;

	@Temporal(TemporalType.DATE)
	@Column(name = "ADMISSION_DATE")
	private Date admissionDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DISCHARGE_DATE")
	private Date dischargeDate;

	@Column(name = "ZYTS")
	private String zyts;
	
	@Column(name = "HISID")
	private String hisid;

	@Column(name = "TOTAL_AMOUNT")
	private String totalAmount;

	@Column(name = "BENEFIT_TYPE")
	private String benefitType;

	@Column(name = "WESTERN_MEDICINE_FEE")
	private String westernMedicineFee;

	@Column(name = "CHINESE_MEDICINE_YINPIAN")
	private String chineseMedicineYinpian;

	@Column(name = "CHINESE_MEDICINE_FORM")
	private String chineseMedicineForm;

	@Column(name = "ACCOMMODATION_FEE")
	private String accommodationFee;

	@Column(name = "DIAGNOSIS_FEE")
	private String diagnosisFee;

	@Column(name = "INSPECTION_FEE")
	private String inspectionFee;

	@Column(name = "TEST_FEE")
	private String testFee;

	@Column(name = "TREATMENT_FEE")
	private String treatmentFee;

	@Column(name = "NURSING_FEE")
	private String nursingFee;

	@Column(name = "MATERIAL_FEE")
	private String materialFee;

	@Column(name = "CONSULTATION_FEE")
	private String consultationFee;

	@Column(name = "REGISTRATION_FEE")
	private String registrationFee;

	@Column(name = "OTHER_FEE")
	private String otherFee;

	@Column(name = "ROWSUM")
	private String rowsum;

	@Column(name = "SUM_AMOUNT")
	private String sumAmount;

	public String getRowsum() {
		return rowsum;
	}

	public void setRowsum(String rowsum) {
		this.rowsum = rowsum;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAdmissionDeptName() {
		return admissionDeptName;
	}

	public void setAdmissionDeptName(String admissionDeptName) {
		this.admissionDeptName = admissionDeptName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getZyts() {
		return zyts;
	}

	public void setZyts(String zyts) {
		this.zyts = zyts;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
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

}