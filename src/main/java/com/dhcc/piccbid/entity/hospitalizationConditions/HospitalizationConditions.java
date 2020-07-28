package com.dhcc.piccbid.entity.hospitalizationConditions;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_FLY_CONDITION database table.
 * 
 */
@Entity
@Table(name="T_FLY_CONDITION")
public class HospitalizationConditions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ADMISSION_DISEASE_ID")
	private String admissionDiseaseId;

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

	public HospitalizationConditions() {
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