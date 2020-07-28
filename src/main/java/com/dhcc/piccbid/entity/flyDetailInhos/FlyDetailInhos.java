package com.dhcc.piccbid.entity.flyDetailInhos;

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
@Table(name = "T_FLY_DETAIL_INHOS")
public class FlyDetailInhos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "HISID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
	private String hisid;
	
	@Column(name = "PATIENT_ID")
	private String patientId;
	
	@Column(name = "ZYH")
	private String zyh;
	
	@Column(name = "HOSPITAL_ID")
	private String hospitalId;
	
	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;
	
	@Column(name = "DISCHARGE_DEPT_ID")
	private String dischargeDeptId;
	
	@Column(name = "DISCHARGE_DEPT_NAME")
	private String dischargeDeptName;
	
	@Column(name = "DOCTOR_ID")
	private String doctorId;
	
	@Column(name = "DOCTOR_NAME")
	private String doctorName;
	
	@Column(name = "DISCHARGE_DISEASE_NAME_MAIN")
	private String dischargeDiseaseNameMain;
	
	@Column(name = "P_CATEGORY")
	private String pCategory;

	@Temporal(TemporalType.DATE)
	@Column(name="USAGE_DATE")
	private Date usageDate;
	
	@Column(name = "USAGE_DATE_FLAG")
	private String usageDateFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATE")
	private Date billDate;

	@Column(name="YEAR")
	private BigDecimal year;

	@Column(name="MONTH")
	private BigDecimal month;
	
	@Column(name = "ITEM_ID_HOSP")
	private String itemIdHosp;
	
	@Column(name = "ITEM_NAME_HOSP")
	private String itemNameHosp;
	
	@Column(name = "ITEM_ID")
	private String itemId;
	
	@Column(name = "ITEM_NAME")
	private String itemName;
	
	@Column(name = "DRUG_SPEC")
	private String drugSpec;
	
	@Column(name = "DOSAGE_FORM")
	private String dosageForm;
	
	@Column(name = "PACKAGE_UNIT")
	private String packageUnit;

	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name="NUM")
	private BigDecimal num;

	@Column(name="COST")
	private BigDecimal cost;

	@Column(name="BMI_CONVERED_AMOUNT")
	private BigDecimal bmiConveredAmount;

	@Column(name="BMI_PAY_AMOUNT")
	private BigDecimal bmiPayAmount;

	@Column(name="P_TYPE")
	private BigDecimal pType;

	@Column(name="P_TYPE_PCT")
	private BigDecimal pTypePct;

	public String getHisid() {
		return hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
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

	public String getDischargeDeptId() {
		return dischargeDeptId;
	}

	public void setDischargeDeptId(String dischargeDeptId) {
		this.dischargeDeptId = dischargeDeptId;
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

	public String getDischargeDiseaseNameMain() {
		return dischargeDiseaseNameMain;
	}

	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public Date getUsageDate() {
		return usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	public String getUsageDateFlag() {
		return usageDateFlag;
	}

	public void setUsageDateFlag(String usageDateFlag) {
		this.usageDateFlag = usageDateFlag;
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

	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}

	public String getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getBmiConveredAmount() {
		return bmiConveredAmount;
	}

	public void setBmiConveredAmount(BigDecimal bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}

	public BigDecimal getBmiPayAmount() {
		return bmiPayAmount;
	}

	public void setBmiPayAmount(BigDecimal bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}

	public BigDecimal getpType() {
		return pType;
	}

	public void setpType(BigDecimal pType) {
		this.pType = pType;
	}

	public BigDecimal getpTypePct() {
		return pTypePct;
	}

	public void setpTypePct(BigDecimal pTypePct) {
		this.pTypePct = pTypePct;
	}

}
