package com.dhcc.piccbid.entity.analysisOfOverAverageInpatients;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the T_FLY_DETAIL_INHOS database table.
 * 
 */
@Entity
@Table(name = "T_FLY_DETAIL_INHOS")
public class AnalysisOfOverAverageInpatients implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "BILL_DATE")
	private Date billDate;

	@Column(name = "BMI_CONVERED_AMOUNT", precision = 22)
	private BigDecimal bmiConveredAmount;

	@Column(name = "BMI_PAY_AMOUNT", precision = 22)
	private BigDecimal bmiPayAmount;

	@Column(precision = 22)
	private BigDecimal cost;

	@Column(name = "DISCHARGE_DEPT_ID", length = 100)
	private String dischargeDeptId;

	@Column(name = "DISCHARGE_DEPT_NAME", length = 100)
	private String dischargeDeptName;

	@Column(name = "DISCHARGE_DISEASE_NAME_MAIN", length = 100)
	private String dischargeDiseaseNameMain;

	@Column(name = "DOCTOR_ID", length = 100)
	private String doctorId;

	@Column(name = "DOCTOR_NAME", length = 100)
	private String doctorName;

	@Column(name = "DOSAGE_FORM", length = 100)
	private String dosageForm;

	@Column(name = "DRUG_SPEC", length = 100)
	private String drugSpec;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 100)
	private String hisid;

	@Column(name = "HOSPITAL_ID", length = 100)
	private String hospitalId;

	@Column(name = "HOSPITAL_NAME", length = 100)
	private String hospitalName;

	@Column(name = "ITEM_ID", length = 100)
	private String itemId;

	@Column(name = "ITEM_ID_HOSP", length = 100)
	private String itemIdHosp;

	@Column(name = "ITEM_NAME", length = 100)
	private String itemName;

	@Column(name = "ITEM_NAME_HOSP", length = 100)
	private String itemNameHosp;

	@Column(name = "MONTH", precision = 22)
	private BigDecimal month;

	@Column(precision = 22)
	private BigDecimal num;

	@Column(name = "P_CATEGORY", length = 100)
	private String pCategory;

	@Column(name = "P_TYPE", precision = 22)
	private BigDecimal pType;

	@Column(name = "P_TYPE_PCT", precision = 22)
	private BigDecimal pTypePct;

	@Column(name = "PACKAGE_UNIT", length = 100)
	private String packageUnit;

	@Column(name = "PATIENT_ID", length = 100)
	private String patientId;

	@Column(name = "UNIT_PRICE", precision = 22)
	private BigDecimal unitPrice;

	@Temporal(TemporalType.DATE)
	@Column(name = "USAGE_DATE")
	private Date usageDate;

	@Column(name = "USAGE_DATE_FLAG", length = 100)
	private String usageDateFlag;

	@Column(name = "YEAR", precision = 22)
	private BigDecimal year;

	public BigDecimal getYear() {
		return year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	@Column(length = 100)
	private String zyh;

	public AnalysisOfOverAverageInpatients() {
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public BigDecimal getBmiConveredAmount() {
		return this.bmiConveredAmount;
	}

	public void setBmiConveredAmount(BigDecimal bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}

	public BigDecimal getBmiPayAmount() {
		return this.bmiPayAmount;
	}

	public void setBmiPayAmount(BigDecimal bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getDischargeDeptId() {
		return this.dischargeDeptId;
	}

	public void setDischargeDeptId(String dischargeDeptId) {
		this.dischargeDeptId = dischargeDeptId;
	}

	public String getDischargeDeptName() {
		return this.dischargeDeptName;
	}

	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}

	public String getDischargeDiseaseNameMain() {
		return this.dischargeDiseaseNameMain;
	}

	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDosageForm() {
		return this.dosageForm;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getDrugSpec() {
		return this.drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}

	public String getHisid() {
		return this.hisid;
	}

	public void setHisid(String hisid) {
		this.hisid = hisid;
	}

	public String getHospitalId() {
		return this.hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemIdHosp() {
		return this.itemIdHosp;
	}

	public void setItemIdHosp(String itemIdHosp) {
		this.itemIdHosp = itemIdHosp;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNameHosp() {
		return this.itemNameHosp;
	}

	public void setItemNameHosp(String itemNameHosp) {
		this.itemNameHosp = itemNameHosp;
	}

	public BigDecimal getMonth() {
		return this.month;
	}

	public void setMonth(BigDecimal month) {
		this.month = month;
	}

	public BigDecimal getNum() {
		return this.num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public String getPCategory() {
		return this.pCategory;
	}

	public void setPCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public BigDecimal getPType() {
		return this.pType;
	}

	public void setPType(BigDecimal pType) {
		this.pType = pType;
	}

	public BigDecimal getPTypePct() {
		return this.pTypePct;
	}

	public void setPTypePct(BigDecimal pTypePct) {
		this.pTypePct = pTypePct;
	}

	public String getPackageUnit() {
		return this.packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getUsageDate() {
		return this.usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	public String getUsageDateFlag() {
		return this.usageDateFlag;
	}

	public void setUsageDateFlag(String usageDateFlag) {
		this.usageDateFlag = usageDateFlag;
	}

	public String getZyh() {
		return this.zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

}