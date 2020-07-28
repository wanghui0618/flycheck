package com.dhcc.piccbid.entity.flyMedicalrecord;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_FLY_MEDICAL database table.
 * 
 */
@Entity
@Table(name="T_FLY_MEDICALRECORD")
public class FlyMedicalrecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "BRIDGE_ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String bridgeId;
	
	@Column(name="HOSPITAL_ID")
	private String hospitalId;

	@Column(name="HOSPITAL_NAME")
	private String hospitalName;

	@Column(name="PATIENT_NAME")
	private String patientName;

	@Column(name="PATIENT_ID")
	private BigDecimal patientId;

	@Column(name="ZYH")
	private String zyh;
	
	@Column(name="SOCIAL_CARD_ID")
	private String socialCardId;

	@Column(name="BENEFIT_TYPE")
	private String benefitType;

	@Column(name="PATIENT_GENDER")
	private String patientGender;

	@Column(name="PATIENT_ADDRESS")
	private String patientAddress;

	@Column(name="PATIENT_COMPANY")
	private String patientCompany;

	@Temporal(TemporalType.DATE)
	@Column(name="PATIENT_BIRTHDAY")
	private Date patientBirthday;

	@Temporal(TemporalType.DATE)
	@Column(name="ADMISSION_DATE")
	private Date admissionDate;

	@Temporal(TemporalType.DATE)
	@Column(name="DISCHARGE_DATE")
	private Date dischargeDate;
	
	@Column(name="ADMISSION_DEPT_NAME")
	private String admissionDeptName;

	@Column(name="TRANS_DEPT1")
	private String transDept1;

	@Column(name="TRANS_DEPT2")
	private String transDept2;

	@Column(name="TRANS_DEPT3")
	private String transDept3;

	@Column(name="DISCHARGE_DEPT_NAME")
	private String dischargeDeptName;

	@Column(name="ICD10_CODE_BASY")
	private String icd10CodeBasy;

	@Column(name="ICD10_NAME_BASY")
	private String icd10NameBasy;

	@Column(name="DISEASE_ID1")
	private String diseaseId1;

	@Column(name="DISEASE_NAME1")
	private String diseaseName1;

	@Column(name="DISEASE_ID2")
	private String diseaseId2;

	@Column(name="DISEASE_NAME2")
	private String diseaseName2;

	@Column(name="DISEASE_ID3")
	private String diseaseId3;

	@Column(name="DISEASE_NAME3")
	private String diseaseName3;

	@Column(name="DISEASE_ID4")
	private String diseaseId4;

	@Column(name="DISEASE_NAME4")
	private String diseaseName4;

	@Column(name="DISEASE_ID5")
	private String diseaseId5;

	@Column(name="DISEASE_NAME5")
	private String diseaseName5;

	@Column(name="DISEASE_ID6")
	private String diseaseId6;

	@Column(name="DISEASE_NAME6")
	private String diseaseName6;

	@Column(name="DISEASE_ID7")
	private String diseaseId7;

	@Column(name="DISEASE_NAME7")
	private String diseaseName7;

	@Column(name="DISEASE_ID8")
	private String diseaseId8;

	@Column(name="DISEASE_NAME8")
	private String diseaseName8;

	@Column(name="DISEASE_ID9")
	private String diseaseId9;

	@Column(name="DISEASE_NAME9")
	private String diseaseName9;

	@Column(name="DISEASE_ID10")
	private String diseaseId10;

	@Column(name="DISEASE_NAME10")
	private String diseaseName10;

	@Column(name="DISEASE_ID11")
	private String diseaseId11;

	@Column(name="DISEASE_NAME11")
	private String diseaseName11;

	@Column(name="DISEASE_ID12")
	private String diseaseId12;

	@Column(name="DISEASE_NAME12")
	private String diseaseName12;

	@Column(name="DISEASE_ID13")
	private String diseaseId13;

	@Column(name="DISEASE_NAME13")
	private String diseaseName13;

	@Column(name="DISEASE_ID14")
	private String diseaseId14;

	@Column(name="DISEASE_NAME14")
	private String diseaseName14;

	@Column(name="DISEASE_ID15")
	private String diseaseId15;

	@Column(name="DISEASE_NAME15")
	private String diseaseName15;

	@Column(name="ICD9_CODE_BASY")
	private String icd9CodeBasy;

	@Column(name="ICD9_NAME_BASY")
	private String icd9NameBasy;

	@Column(name="ICD9_CODE1")
	private String icd9Code1;

	@Column(name="ICD9_NAME1")
	private String icd9Name1;

	@Column(name="ICD9_CODE2")
	private String icd9Code2;

	@Column(name="ICD9_NAME2")
	private String icd9Name2;

	@Column(name="ICD9_CODE3")
	private String icd9Code3;

	@Column(name="ICD9_NAME3")
	private String icd9Name3;

	@Column(name="ICD9_CODE4")
	private String icd9Code4;

	@Column(name="ICD9_NAME4")
	private String icd9Name4;

	@Column(name="ICD9_CODE5")
	private String icd9Code5;

	@Column(name="ICD9_NAME5")
	private String icd9Name5;

	@Column(name="ICD9_CODE6")
	private String icd9Code6;

	@Column(name="ICD9_NAME6")
	private String icd9Name6;

	@Column(name="ICD9_CODE7")
	private String icd9Code7;

	@Column(name="ICD9_NAME7")
	private String icd9Name7;

	@Column(name="ICD9_CODE8")
	private String icd9Code8;

	@Column(name="ICD9_NAME8")
	private String icd9Name8;

	@Column(name="ICD9_CODE9")
	private String icd9Code9;

	@Column(name="ICD9_NAME9")
	private String icd9Name9;

	@Column(name="ICD9_CODE10")
	private String icd9Code10;

	@Column(name="ICD9_NAME10")
	private String icd9Name10;

	@Column(name="DISCHARGE_STATUS")
	private String dischargeStatus;

	@Column(name="NB_WEIGHT")
	private BigDecimal nbWeight;

	@Column(name="NB_ADMISSION_WEIGHT")
	private BigDecimal nbAdmissionWeight;

	@Column(name="TOTAL_AMOUNT_BASY")
	private BigDecimal totalAmountBasy;

	@Column(name="BM_HOURS")
	private BigDecimal bmHours;
	
	@Column(name="ICU_FLAG")
	private String icuFlag;

	@Column(name="ICU_HOURS")
	private BigDecimal icuHours;

	public String getBridgeId() {
		return bridgeId;
	}

	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public BigDecimal getPatientId() {
		return patientId;
	}

	public void setPatientId(BigDecimal patientId) {
		this.patientId = patientId;
	}

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
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

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientCompany() {
		return patientCompany;
	}

	public void setPatientCompany(String patientCompany) {
		this.patientCompany = patientCompany;
	}

	public Date getPatientBirthday() {
		return patientBirthday;
	}

	public void setPatientBirthday(Date patientBirthday) {
		this.patientBirthday = patientBirthday;
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

	public String getAdmissionDeptName() {
		return admissionDeptName;
	}

	public void setAdmissionDeptName(String admissionDeptName) {
		this.admissionDeptName = admissionDeptName;
	}

	public String getTransDept1() {
		return transDept1;
	}

	public void setTransDept1(String transDept1) {
		this.transDept1 = transDept1;
	}

	public String getTransDept2() {
		return transDept2;
	}

	public void setTransDept2(String transDept2) {
		this.transDept2 = transDept2;
	}

	public String getTransDept3() {
		return transDept3;
	}

	public void setTransDept3(String transDept3) {
		this.transDept3 = transDept3;
	}

	public String getDischargeDeptName() {
		return dischargeDeptName;
	}

	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}

	public String getIcd10CodeBasy() {
		return icd10CodeBasy;
	}

	public void setIcd10CodeBasy(String icd10CodeBasy) {
		this.icd10CodeBasy = icd10CodeBasy;
	}

	public String getIcd10NameBasy() {
		return icd10NameBasy;
	}

	public void setIcd10NameBasy(String icd10NameBasy) {
		this.icd10NameBasy = icd10NameBasy;
	}

	public String getDiseaseId1() {
		return diseaseId1;
	}

	public void setDiseaseId1(String diseaseId1) {
		this.diseaseId1 = diseaseId1;
	}

	public String getDiseaseName1() {
		return diseaseName1;
	}

	public void setDiseaseName1(String diseaseName1) {
		this.diseaseName1 = diseaseName1;
	}

	public String getDiseaseId2() {
		return diseaseId2;
	}

	public void setDiseaseId2(String diseaseId2) {
		this.diseaseId2 = diseaseId2;
	}

	public String getDiseaseName2() {
		return diseaseName2;
	}

	public void setDiseaseName2(String diseaseName2) {
		this.diseaseName2 = diseaseName2;
	}

	public String getDiseaseId3() {
		return diseaseId3;
	}

	public void setDiseaseId3(String diseaseId3) {
		this.diseaseId3 = diseaseId3;
	}

	public String getDiseaseName3() {
		return diseaseName3;
	}

	public void setDiseaseName3(String diseaseName3) {
		this.diseaseName3 = diseaseName3;
	}

	public String getDiseaseId4() {
		return diseaseId4;
	}

	public void setDiseaseId4(String diseaseId4) {
		this.diseaseId4 = diseaseId4;
	}

	public String getDiseaseName4() {
		return diseaseName4;
	}

	public void setDiseaseName4(String diseaseName4) {
		this.diseaseName4 = diseaseName4;
	}

	public String getDiseaseId5() {
		return diseaseId5;
	}

	public void setDiseaseId5(String diseaseId5) {
		this.diseaseId5 = diseaseId5;
	}

	public String getDiseaseName5() {
		return diseaseName5;
	}

	public void setDiseaseName5(String diseaseName5) {
		this.diseaseName5 = diseaseName5;
	}

	public String getDiseaseId6() {
		return diseaseId6;
	}

	public void setDiseaseId6(String diseaseId6) {
		this.diseaseId6 = diseaseId6;
	}

	public String getDiseaseName6() {
		return diseaseName6;
	}

	public void setDiseaseName6(String diseaseName6) {
		this.diseaseName6 = diseaseName6;
	}

	public String getDiseaseId7() {
		return diseaseId7;
	}

	public void setDiseaseId7(String diseaseId7) {
		this.diseaseId7 = diseaseId7;
	}

	public String getDiseaseName7() {
		return diseaseName7;
	}

	public void setDiseaseName7(String diseaseName7) {
		this.diseaseName7 = diseaseName7;
	}

	public String getDiseaseId8() {
		return diseaseId8;
	}

	public void setDiseaseId8(String diseaseId8) {
		this.diseaseId8 = diseaseId8;
	}

	public String getDiseaseName8() {
		return diseaseName8;
	}

	public void setDiseaseName8(String diseaseName8) {
		this.diseaseName8 = diseaseName8;
	}

	public String getDiseaseId9() {
		return diseaseId9;
	}

	public void setDiseaseId9(String diseaseId9) {
		this.diseaseId9 = diseaseId9;
	}

	public String getDiseaseName9() {
		return diseaseName9;
	}

	public void setDiseaseName9(String diseaseName9) {
		this.diseaseName9 = diseaseName9;
	}

	public String getDiseaseId10() {
		return diseaseId10;
	}

	public void setDiseaseId10(String diseaseId10) {
		this.diseaseId10 = diseaseId10;
	}

	public String getDiseaseName10() {
		return diseaseName10;
	}

	public void setDiseaseName10(String diseaseName10) {
		this.diseaseName10 = diseaseName10;
	}

	public String getDiseaseId11() {
		return diseaseId11;
	}

	public void setDiseaseId11(String diseaseId11) {
		this.diseaseId11 = diseaseId11;
	}

	public String getDiseaseName11() {
		return diseaseName11;
	}

	public void setDiseaseName11(String diseaseName11) {
		this.diseaseName11 = diseaseName11;
	}

	public String getDiseaseId12() {
		return diseaseId12;
	}

	public void setDiseaseId12(String diseaseId12) {
		this.diseaseId12 = diseaseId12;
	}

	public String getDiseaseName12() {
		return diseaseName12;
	}

	public void setDiseaseName12(String diseaseName12) {
		this.diseaseName12 = diseaseName12;
	}

	public String getDiseaseId13() {
		return diseaseId13;
	}

	public void setDiseaseId13(String diseaseId13) {
		this.diseaseId13 = diseaseId13;
	}

	public String getDiseaseName13() {
		return diseaseName13;
	}

	public void setDiseaseName13(String diseaseName13) {
		this.diseaseName13 = diseaseName13;
	}

	public String getDiseaseId14() {
		return diseaseId14;
	}

	public void setDiseaseId14(String diseaseId14) {
		this.diseaseId14 = diseaseId14;
	}

	public String getDiseaseName14() {
		return diseaseName14;
	}

	public void setDiseaseName14(String diseaseName14) {
		this.diseaseName14 = diseaseName14;
	}

	public String getDiseaseId15() {
		return diseaseId15;
	}

	public void setDiseaseId15(String diseaseId15) {
		this.diseaseId15 = diseaseId15;
	}

	public String getDiseaseName15() {
		return diseaseName15;
	}

	public void setDiseaseName15(String diseaseName15) {
		this.diseaseName15 = diseaseName15;
	}

	public String getIcd9CodeBasy() {
		return icd9CodeBasy;
	}

	public void setIcd9CodeBasy(String icd9CodeBasy) {
		this.icd9CodeBasy = icd9CodeBasy;
	}

	public String getIcd9NameBasy() {
		return icd9NameBasy;
	}

	public void setIcd9NameBasy(String icd9NameBasy) {
		this.icd9NameBasy = icd9NameBasy;
	}

	public String getIcd9Code1() {
		return icd9Code1;
	}

	public void setIcd9Code1(String icd9Code1) {
		this.icd9Code1 = icd9Code1;
	}

	public String getIcd9Name1() {
		return icd9Name1;
	}

	public void setIcd9Name1(String icd9Name1) {
		this.icd9Name1 = icd9Name1;
	}

	public String getIcd9Code2() {
		return icd9Code2;
	}

	public void setIcd9Code2(String icd9Code2) {
		this.icd9Code2 = icd9Code2;
	}

	public String getIcd9Name2() {
		return icd9Name2;
	}

	public void setIcd9Name2(String icd9Name2) {
		this.icd9Name2 = icd9Name2;
	}

	public String getIcd9Code3() {
		return icd9Code3;
	}

	public void setIcd9Code3(String icd9Code3) {
		this.icd9Code3 = icd9Code3;
	}

	public String getIcd9Name3() {
		return icd9Name3;
	}

	public void setIcd9Name3(String icd9Name3) {
		this.icd9Name3 = icd9Name3;
	}

	public String getIcd9Code4() {
		return icd9Code4;
	}

	public void setIcd9Code4(String icd9Code4) {
		this.icd9Code4 = icd9Code4;
	}

	public String getIcd9Name4() {
		return icd9Name4;
	}

	public void setIcd9Name4(String icd9Name4) {
		this.icd9Name4 = icd9Name4;
	}

	public String getIcd9Code5() {
		return icd9Code5;
	}

	public void setIcd9Code5(String icd9Code5) {
		this.icd9Code5 = icd9Code5;
	}

	public String getIcd9Name5() {
		return icd9Name5;
	}

	public void setIcd9Name5(String icd9Name5) {
		this.icd9Name5 = icd9Name5;
	}

	public String getIcd9Code6() {
		return icd9Code6;
	}

	public void setIcd9Code6(String icd9Code6) {
		this.icd9Code6 = icd9Code6;
	}

	public String getIcd9Name6() {
		return icd9Name6;
	}

	public void setIcd9Name6(String icd9Name6) {
		this.icd9Name6 = icd9Name6;
	}

	public String getIcd9Code7() {
		return icd9Code7;
	}

	public void setIcd9Code7(String icd9Code7) {
		this.icd9Code7 = icd9Code7;
	}

	public String getIcd9Name7() {
		return icd9Name7;
	}

	public void setIcd9Name7(String icd9Name7) {
		this.icd9Name7 = icd9Name7;
	}

	public String getIcd9Code8() {
		return icd9Code8;
	}

	public void setIcd9Code8(String icd9Code8) {
		this.icd9Code8 = icd9Code8;
	}

	public String getIcd9Name8() {
		return icd9Name8;
	}

	public void setIcd9Name8(String icd9Name8) {
		this.icd9Name8 = icd9Name8;
	}

	public String getIcd9Code9() {
		return icd9Code9;
	}

	public void setIcd9Code9(String icd9Code9) {
		this.icd9Code9 = icd9Code9;
	}

	public String getIcd9Name9() {
		return icd9Name9;
	}

	public void setIcd9Name9(String icd9Name9) {
		this.icd9Name9 = icd9Name9;
	}

	public String getIcd9Code10() {
		return icd9Code10;
	}

	public void setIcd9Code10(String icd9Code10) {
		this.icd9Code10 = icd9Code10;
	}

	public String getIcd9Name10() {
		return icd9Name10;
	}

	public void setIcd9Name10(String icd9Name10) {
		this.icd9Name10 = icd9Name10;
	}

	public String getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}

	public BigDecimal getNbWeight() {
		return nbWeight;
	}

	public void setNbWeight(BigDecimal nbWeight) {
		this.nbWeight = nbWeight;
	}

	public BigDecimal getNbAdmissionWeight() {
		return nbAdmissionWeight;
	}

	public void setNbAdmissionWeight(BigDecimal nbAdmissionWeight) {
		this.nbAdmissionWeight = nbAdmissionWeight;
	}

	public BigDecimal getTotalAmountBasy() {
		return totalAmountBasy;
	}

	public void setTotalAmountBasy(BigDecimal totalAmountBasy) {
		this.totalAmountBasy = totalAmountBasy;
	}

	public BigDecimal getBmHours() {
		return bmHours;
	}

	public void setBmHours(BigDecimal bmHours) {
		this.bmHours = bmHours;
	}

	public String getIcuFlag() {
		return icuFlag;
	}

	public void setIcuFlag(String icuFlag) {
		this.icuFlag = icuFlag;
	}

	public BigDecimal getIcuHours() {
		return icuHours;
	}

	public void setIcuHours(BigDecimal icuHours) {
		this.icuHours = icuHours;
	}

}