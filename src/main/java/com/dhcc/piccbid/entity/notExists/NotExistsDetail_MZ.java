/**
 * 
 */
package com.dhcc.piccbid.entity.notExists;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 59545
 *
 */
@Entity
public class NotExistsDetail_MZ implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String mzDetailId;
	
	@Column(name="HISID")
	private String hisid;
	@Column(name="PATIENT_ID")
	private String patientId;

	@Column(name="HOSPITAL_ID")
	private String hospitalId;
	@Column(name="HOSPITAL_NAME")
	private String hospitalName;
	@Column(name="DISCHARGE_DEPT_ID")
	private String dichargeDeptId;
	@Column(name="DISCHARGE_DEPT_NAME")
	private String dischargeDeptName;
	@Column(name="DOCTOR_ID")
	private String doctorId;
	@Column(name="DOCTOR_NAME")
	private String doctorName;
	@Column(name="DISCHARGE_DISEASE_NAME_MAIN")
	private String dischargeDiseaseNameMain;
	@Column(name="P_CATEGORY")
	private String pCategory;

	@Column(name="BILL_DATE")
	private Date billDate;
	@Column(name="YEAR")
	private String year;
	@Column(name="MONTH")
	private String month;
	@Column(name="ITEM_ID_HOSP")
	private String itemIdHosp;
	@Column(name="ITEM_NAME_HOSP")
	private String itemNameHosp;
	@Column(name="ITEM_ID")
	private String itemId;
	@Column(name="ITEM_NAME")
	private String itemName;
	@Column(name="DRUG_SPEC")
	private String drugSpec;
	@Column(name="DOSAGE_FORM")
	private String dosageForm;
	@Column(name="PACKAGE_UNIT")
	private String packageUnit;
	@Column(name="UNIT_PRICE")
	private Double unitPrice;
	@Column(name="NUM")
	private Double num;
	@Column(name="COST")
	private Double cost;
	@Column(name="BMI_CONVERED_AMOUNT")
	private String bmiConveredAmount;
	@Column(name="BMI_PAY_AMOUNT")
	private String bmiPayAmount;
	@Column(name="P_TYPE")
	private String pType;
	@Column(name="ITEM_TYPE")
	private String itemType;


	/**
	 * @return the mzDetailId
	 */
	public String getMzDetailId() {
		return mzDetailId;
	}
	/**
	 * @param mzDetailId the mzDetailId to set
	 */
	public void setMzDetailId(String mzDetailId) {
		this.mzDetailId = mzDetailId;
	}
	/**
	 * @return the hisid
	 */
	public String getHisid() {
		return hisid;
	}
	/**
	 * @param hisid the hisid to set
	 */
	public void setHisid(String hisid) {
		this.hisid = hisid;
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

	/**
	 * @return the hospitalId
	 */
	public String getHospitalId() {
		return hospitalId;
	}
	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	/**
	 * @return the dichargeDeptId
	 */
	public String getDichargeDeptId() {
		return dichargeDeptId;
	}
	/**
	 * @param dichargeDeptId the dichargeDeptId to set
	 */
	public void setDichargeDeptId(String dichargeDeptId) {
		this.dichargeDeptId = dichargeDeptId;
	}
	/**
	 * @return the dischargeDeptName
	 */
	public String getDischargeDeptName() {
		return dischargeDeptName;
	}
	/**
	 * @param dischargeDeptName the dischargeDeptName to set
	 */
	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}
	/**
	 * @return the doctorId
	 */
	public String getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * @return the dischargeDiseaseNameMain
	 */
	public String getDischargeDiseaseNameMain() {
		return dischargeDiseaseNameMain;
	}
	/**
	 * @param dischargeDiseaseNameMain the dischargeDiseaseNameMain to set
	 */
	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
	}
	/**
	 * @return the pCategory
	 */
	public String getpCategory() {
		return pCategory;
	}
	/**
	 * @param pCategory the pCategory to set
	 */
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}


	/**
	 * @return the billDate
	 */
	public Date getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the itemIdHosp
	 */
	public String getItemIdHosp() {
		return itemIdHosp;
	}
	/**
	 * @param itemIdHosp the itemIdHosp to set
	 */
	public void setItemIdHosp(String itemIdHosp) {
		this.itemIdHosp = itemIdHosp;
	}
	/**
	 * @return the itemNameHosp
	 */
	public String getItemNameHosp() {
		return itemNameHosp;
	}
	/**
	 * @param itemNameHosp the itemNameHosp to set
	 */
	public void setItemNameHosp(String itemNameHosp) {
		this.itemNameHosp = itemNameHosp;
	}
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the drugSpec
	 */
	public String getDrugSpec() {
		return drugSpec;
	}
	/**
	 * @param drugSpec the drugSpec to set
	 */
	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	/**
	 * @return the dosageForm
	 */
	public String getDosageForm() {
		return dosageForm;
	}
	/**
	 * @param dosageForm the dosageForm to set
	 */
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	/**
	 * @return the packageUnit
	 */
	public String getPackageUnit() {
		return packageUnit;
	}
	/**
	 * @param packageUnit the packageUnit to set
	 */
	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}


	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the num
	 */
	public Double getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(Double num) {
		this.num = num;
	}
	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	/**
	 * @return the bmiConveredAmount
	 */
	public String getBmiConveredAmount() {
		return bmiConveredAmount;
	}
	/**
	 * @param bmiConveredAmount the bmiConveredAmount to set
	 */
	public void setBmiConveredAmount(String bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}
	/**
	 * @return the bmiPayAmount
	 */
	public String getBmiPayAmount() {
		return bmiPayAmount;
	}
	/**
	 * @param bmiPayAmount the bmiPayAmount to set
	 */
	public void setBmiPayAmount(String bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}
	/**
	 * @return the pType
	 */
	public String getpType() {
		return pType;
	}
	/**
	 * @param pType the pType to set
	 */
	public void setpType(String pType) {
		this.pType = pType;
	}


	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
