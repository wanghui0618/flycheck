package com.dhcc.piccbid.entity.nursingViolation;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_FLY_CONDITION database table.
 * 
 */
@Entity
public class NursingViolation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ADMISSION_DISEASE_ID")
	private String admissionDiseaseId;
	
	@Column(name="PATIENT_ID")
	private String patientId;
	
	@Column(name="SOCIAL_CARD_ID")
	private String socialCardId;
	
	@Column(name="BENEFIT_TYPE")
	private String benefitType;
	
	@Column(name="PATIENT_BIRTHDAY")
	private String patientBirthday;
	
	@Column(name="ADMISSION_DATE")
	private String admissionDate;
	
	@Column(name="DISCHARGE_DATE")
	private String dischargeDate;
	
	@Column(name="ZYTS")
	private String zyts;
	
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

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public String getPatientBirthday() {
		return patientBirthday;
	}

	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getZyts() {
		return zyts;
	}

	public void setZyts(String zyts) {
		this.zyts = zyts;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name="ADMISSION_DISEASE_NAME")
	private String admissionDiseaseName;

	@Column(name="HIS_ID")
	private String hisid;
	
	@Column(name="MEDICAL_RECORD_ID")
	private String medicalRecordId;
	
	@Column(name="PATIENT_NAME")
	private String patientName;
	
	@Column(name="PATIENT_GENDER")
	private String patientGender;
	
	@Column(name="PATIENT_AGE")
	private String patientAge;
	
	@Column(name="HOSPITAL_ID")
	private String hospitalId;
	
	@Column(name="HOSPITAL_NAME")
	private String hospitalName;
	
	@Column(name="ITEM_NAME")
	private String itemName;
	
	@Column(name="ITEM_ID")
	private String itemId;
	
	public String getHisid() {
		return hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	public String getMedicalRecordId() {
		return medicalRecordId;
	}

	public void setMedicalRecordId(String medicalRecordId) {
		this.medicalRecordId = medicalRecordId;
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

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
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

	public NursingViolation() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

}