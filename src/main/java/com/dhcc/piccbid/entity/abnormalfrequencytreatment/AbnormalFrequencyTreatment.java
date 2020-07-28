package com.dhcc.piccbid.entity.abnormalfrequencytreatment;

import java.io.Serializable;

import javax.persistence.Column;

public class AbnormalFrequencyTreatment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * HISID,HOSPITAL_NAME,BILL_DATE,PATIENT_ID,ADMISSION_DEPT_NAME,CLAIM_TYPE,
	 * ADMISSION_DISEASE_ID, ADMISSION_DISEASE_NAME,TOTAL_AMOUNT,BMI_PAY_AMOUNT
	 */
	
	@Column(name="HISID")
    private String hisid;
    @Column(name="HOSPITAL_NAME")
    private String hospitalName;
    @Column(name="BILL_DATE")
    private String billDate;
    @Column(name="PATIENT_ID")
    private String patientId;
    @Column(name="ADMISSION_DEPT_NAME")
    private String admissionDeptName;
    @Column(name="CLAIM_TYPE")
    private String claimType;
    @Column(name="ADMISSION_DISEASE_ID")
    private String admissionDiseaseId;
    @Column(name="ADMISSION_DISEASE_NAME")
    private String admissionDiseaseName;
    @Column(name="TOTAL_AMOUNT")
    private String totalAmount;
    @Column(name="BMI_PAY_AMOUNT")
    private String bmiPayAmount;
    
    
    
    
    
	@Override
	public String toString() {
		return "AbnormalFrequencyTreatment [hisid=" + hisid + ", hospitalName=" + hospitalName + ", billDate="
				+ billDate + ", patientId=" + patientId + ", admissionDeptName=" + admissionDeptName + ", claimType="
				+ claimType + ", admissionDiseaseId=" + admissionDiseaseId + ", admissionDiseaseName="
				+ admissionDiseaseName + ", totalAmount=" + totalAmount + ", bmiPayAmount=" + bmiPayAmount + "]";
	}
	public String getHisid() {
		return hisid;
	}
	public void setHisid(String hisid) {
		this.hisid = hisid;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
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
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
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
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getBmiPayAmount() {
		return bmiPayAmount;
	}
	public void setBmiPayAmount(String bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

	
	
}