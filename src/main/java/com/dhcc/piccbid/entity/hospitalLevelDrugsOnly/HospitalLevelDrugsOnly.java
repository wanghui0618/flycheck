package com.dhcc.piccbid.entity.hospitalLevelDrugsOnly;

import javax.persistence.Column;
import java.io.Serializable;

public class HospitalLevelDrugsOnly implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name="HOSPITAL_ID")
    private String hospitalId;

    @Column(name="HOSPITAL_NAME")
    private String hospitalName;

    @Column(name="ZYH")
    private String zyh;

    @Column(name="DISCHARGE_DEPT_NAME")
    private String dischargeDeptName;

    @Column(name="PATIENT_NAME")
    private String patientName;

    @Column(name="ZYTS")
    private String zyts;

    @Column(name="TOTAL_AMOUNT")
    private String totalAmount;

    @Column(name="BENEFIT_TYPE")
    private String benefitType;

    @Column(name="WESTERN_MEDICINE_FEE")
    private String westernMedicineFee;

    @Column(name="CHINESE_MEDICINE_YINPIAN")
    private String chineseMedicineYinpian;

    @Column(name="CHINESE_MEDICINE_FORM")
    private String chineseMedicineForm;

    @Column(name="ADMISSION_DATE")
    private String admissionDate;

    @Column(name="DISCHARGE_DATE")
    private String dischargeDate;

    @Column(name="ITEM_ID")
    private String itemId;

    @Column(name="ITEM_NAME")
    private String itemName;

    @Column(name="ITEM_ID_HOSP")
    private String itemIdHosp;

    @Column(name="ITEM_NAME_HOSP")
    private String itemNameHosp;

    private String dischargeDept;


    public String getDischargeDept() {
        return dischargeDept;
    }

    public void setDischargeDept(String dischargeDept) {
        this.dischargeDept = dischargeDept;
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

    public String getDischargeDeptName() {
        return dischargeDeptName;
    }

    public void setDischargeDeptName(String dischargeDeptName) {
        this.dischargeDeptName = dischargeDeptName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIdHosp() {
        return itemIdHosp;
    }

    public void setItemIdHosp(String itemIdHosp) {
        this.itemIdHosp = itemIdHosp;
    }

    public String getItemNameHosp() {
        return itemNameHosp;
    }

    public void setItemNameHosp(String itemNameHosp) {
        this.itemNameHosp = itemNameHosp;
    }
}
