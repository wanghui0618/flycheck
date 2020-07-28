package com.dhcc.piccbid.entity.genderLimit;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 王明月
 * @create 2020-01-03 10:28
 */
@Entity
public class GenderLimit implements Serializable {

    @Id
    @Column(name="HISID", unique=true, nullable=false)
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String hisid;


    /**
     * 医疗机构编码
     */
    @Column(name="HOSPITAL_ID")
    private String hospitalId;

    /**
     * 医疗机构名称
     */
    @Column(name="HOSPITAL_NAME")
    private String hospitalName;

    /**
     * 住院号
     */
    @Column(name="ZYH")
    private String zyh;

    /**
     * 患者姓名
     */
    @Column(name="PATIENT_NAME")
    private String patientName;

    /**
     * 住院天数
     */
    @Column(name="ZYTS")
    private String zyts;

    /**
     * 医疗总费用
     */
    @Column(name="TOTAL_AMOUNT")
    private String totalAmount;
    /**
     * 险种类型
     */
    @Column(name="BENEFIT_TYPE")
    private String benefitType;

    /**
     * 西药费
     */
    @Column(name="WESTERN_MEDICINE_FEE")
    private String westernMedicineFee ;

    /**
     * 中药饮片费
     */
    @Column(name="CHINESE_MEDICINE_YINPIAN")
    private String chineseMedicineYinpian;

    /**
     * 中成药费
     */
    @Column(name="CHINESE_MEDICINE_FORM")
    private String chineseMedicineForm;

    /**
     * 入院日期
     */
    @Temporal(TemporalType.DATE)
    @Column(name="ADMISSION_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date admissionDate;

    /**
     * 出院日期
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name="DISCHARGE_DATE")
    private Date dischargeDate;


    /**
     * 就诊科室
     */
    @Column(name="ADMISSION_DEPT_NAME")
    private String admissionDeptName;

    /**
     * 医保项目编码
     */
    @Column(name="ITEM_ID")
    private String itemId;
    
    private String orgCode;
    /**
     * 医保项目名称
     */
    @Column(name="ITEM_NAME")
    private String itemName;
    
    private String orgName;
    
    /**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
     * 医院项目编码
     */
    @Column(name="ITEM_ID_HOSP")
    private String itemIdHosp;
    /**
     * 医院项目名称
     */
    @Column(name="ITEM_NAME_HOSP")
    private String itemNameHosp;


    /**
     * 年龄
     */
    @Column(name="PATIENT_AGE")
    private String patientAge;

    /**
     * 性别
     */
    @Column(name="PATIENT_GENDER")
    private String patientGender;

    /**
     * 结算日期
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name="BILL_DATE")
    private Date billDate;


    /**
     * 诊断编码
     */
    @Column(name="ADMISSION_DISEASE_ID")
    private String admissionDiseaseId;


    /**
     * 诊断名称
     */
    @Column(name="ADMISSION_DISEASE_NAME")
    private String admissionDiseaseName;


    /**
     * 总病例数
     */
    @Column(name="MEDICAL_COUNT")
    private String medicalCount;
    /**
     * 涉及病例总金额
     */
    @Column(name="MEDICAL_TOTAL")
    private String medicalTotal;
    /**
     * 涉及明细数量
     */
    @Column(name="DETAIL_COUNT")
    private String detailCount;
    /**
     * 涉及明细总金额
     */
    @Column(name="DETAIL_TOTAL")
    private String detailTotal;

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getMedicalCount() {
        return medicalCount;
    }

    public void setMedicalCount(String medicalCount) {
        this.medicalCount = medicalCount;
    }

    public String getMedicalTotal() {
        return medicalTotal;
    }

    public void setMedicalTotal(String medicalTotal) {
        this.medicalTotal = medicalTotal;
    }

    public String getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(String detailCount) {
        this.detailCount = detailCount;
    }

    public String getDetailTotal() {
        return detailTotal;
    }

    public void setDetailTotal(String detailTotal) {
        this.detailTotal = detailTotal;
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

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
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

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
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
