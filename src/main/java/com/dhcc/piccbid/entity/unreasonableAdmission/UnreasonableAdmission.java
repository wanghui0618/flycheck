package com.dhcc.piccbid.entity.unreasonableAdmission;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author :  zhouwei
 * @PackageNAme : com.dhcc.piccbid.entity.UnreasonableAdmission
 * @ClassName : UnreasonableAdmission
 * @Description:
 * @create : 2019-11-23 15:00
 */
@Entity
public class UnreasonableAdmission  implements Serializable {
    private String code1;
    private String code2;
    private String code3;
    private String billDateStr;
    private String admissionDateStr;
    private String dischargeDateStr;
    private String proportionOfMedicines;
    private String inspectionFeeRatio;
    private String costOfTreatment;
    @Column(name="INSPECTION_FEE")
    private String inspectionFee;
    //身份证号
    private String scno;
    @Column(name="HOSPITAL_NAME")
    private String hospitalName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="BILL_DATE")
    private Date billDate;
    @Column(name="HOSPITAL_ID")
    private String hospitalId;
    @Column(name="ZYH")
    private String zyh;
    @Column(name="ADMISSION_DEPT_NAME")
    private String admissionDeptName;
    @Column(name="PATIENT_NAME")
    private String patientName;
    @Column(name="PATIENT_ID")
    private String patientId;
    //入院时间
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="ADMISSION_DATE")
    private Date admissionDate;
    //出院时间
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="DISCHARGE_DATE")
    private Date dischargeDate;
    @Column(name="ZYTS")
    private String zyts;
    @Column(name="TOTSL_AMOUNT")
    private String totalAmount;
    @Column(name="BENEFIT_TYPE")
    private String benefitType;
    @Column(name="WESTERN_MEDICINE_FEE")
    private String westernMedicineFee;
    @Column(name="CHINESE_MEDICINE_YINPIAN")
    private String chineseMedicineYinpian;
    @Column(name="CHINESE_MEDICINE_FROM")
    private String chineseMedicineForm;
    @Column(name="CONSULTATION_FEE")
    private String consultationFee;
    @Id
    private String hisid;

    @Column(name = "ROWSUM")
    private String rowsum;
    @Column(name = "SUM_AMOUNT")
    private String sumAmount ;
    @Column(name = "ROWNUM")
    private String rownum;
    
    @Column(name = "YFZB")
    private Double yfzb;
    @Column(name = "JCFZB")
    private Double jcfzb;
    @Column(name = "ZLFZB")
    private Double zlfzb;

    public String getInspectionFee() {
        return inspectionFee;
    }

    public void setInspectionFee(String inspectionFee) {
        this.inspectionFee = inspectionFee;
    }

   

	/**
	 * @return the yfzb
	 */
	public Double getYfzb() {
		return yfzb;
	}

	/**
	 * @param yfzb the yfzb to set
	 */
	public void setYfzb(Double yfzb) {
		this.yfzb = yfzb;
	}

	/**
	 * @return the jcfzb
	 */
	public Double getJcfzb() {
		return jcfzb;
	}

	/**
	 * @param jcfzb the jcfzb to set
	 */
	public void setJcfzb(Double jcfzb) {
		this.jcfzb = jcfzb;
	}

	/**
	 * @return the zlfzb
	 */
	public Double getZlfzb() {
		return zlfzb;
	}

	/**
	 * @param zlfzb the zlfzb to set
	 */
	public void setZlfzb(Double zlfzb) {
		this.zlfzb = zlfzb;
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

	public String getScno() {
        return scno;
    }

    public void setScno(String scno) {
        this.scno = scno;
    }

    public String getBillDateStr() {
        return billDateStr;
    }

    public void setBillDateStr(String billDateStr) {
        this.billDateStr = billDateStr;
    }

    public String getAdmissionDateStr() {
        return admissionDateStr;
    }

    public void setAdmissionDateStr(String admissionDateStr) {
        this.admissionDateStr = admissionDateStr;
    }

    public String getDischargeDateStr() {
        return dischargeDateStr;
    }

    public void setDischargeDateStr(String dischargeDateStr) {
        this.dischargeDateStr = dischargeDateStr;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

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

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
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

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }


    public String getProportionOfMedicines() {
        return proportionOfMedicines;
    }

    public void setProportionOfMedicines(String proportionOfMedicines) {
        this.proportionOfMedicines = proportionOfMedicines;
    }

    public String getInspectionFeeRatio() {
        return inspectionFeeRatio;
    }

    public void setInspectionFeeRatio(String inspectionFeeRatio) {
        this.inspectionFeeRatio = inspectionFeeRatio;
    }

    public String getCostOfTreatment() {
        return costOfTreatment;
    }

    public void setCostOfTreatment(String costOfTreatment) {
        this.costOfTreatment = costOfTreatment;
    }

    public String getHisid() {
        return hisid;
    }

    public void setHisid(String hisid) {
        this.hisid = hisid;
    }
}
