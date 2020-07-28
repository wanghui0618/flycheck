package com.dhcc.piccbid.entity.decompositionHospital;

import javax.persistence.Column;
import java.io.Serializable;

public class DecompositionHospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="HISID")
    private String hisid;
    @Column(name="ZYH")
    private String zyh;
    @Column(name="admission_date")
    private String admissionDate;
    @Column(name="DISCHARGE_DATE")
    private String dischargeDate;
    @Column(name="ZYTS")
    private String zyts;
    @Column(name="BILL_DATE")
    private String billDate;
    @Column(name="DISCHARGE_DISEASE_ID_MAIN")
    private String dischargeDiseaseIdMain;
    @Column(name="DISCHARGE_DISEASE_NAME_MAIN")
    private String dischargeDiseaseNameMain;
    @Column(name="TOTAL_AMOUNT")
    private String totalAmount;
    @Column(name="BMI_PAY_AMOUNT")
    private String bmiPayAmount;


    @Override
    public String toString() {
        return "DecompositionHospital{" +
                "hisid='" + hisid + '\'' +
                ", zyh='" + zyh + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", dischargeDate='" + dischargeDate + '\'' +
                ", zyts='" + zyts + '\'' +
                ", billDate='" + billDate + '\'' +
                ", dischargeDiseaseIdMain='" + dischargeDiseaseIdMain + '\'' +
                ", dischargeDiseaseNameMain='" + dischargeDiseaseNameMain + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", bmiPayAmount='" + bmiPayAmount + '\'' +
                '}';
    }

    public String getHisid() {
        return hisid;
    }

    public void setHisid(String hisid) {
        this.hisid = hisid;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
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
}
