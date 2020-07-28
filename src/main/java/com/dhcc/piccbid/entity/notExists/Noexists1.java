package com.dhcc.piccbid.entity.notExists;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_FLYCHECK_MEDICAL_DETAIL database table.
 * 
 */
@Entity
public class Noexists1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String detailId;

	 private  String hisId1;
	    private  String itemId1;
	    private  String itemName1;
	

	@Column(name="BILL_DATE")
	private String billDate;

	@Column(name="BMI_CONVERED_AMOUNT", precision=22)
	private String bmiConveredAmount;

	@Column(name="BMI_PAY_AMOUNT", precision=22)
	private String bmiPayAmount;

	@Column(name="COST")
	private String cost;

	@Column(name="DISCHARGE_DEPT_ID", length=320)
	private String dischargeDeptId;

	@Column(name="DISCHARGE_DEPT_NAME", length=320)
	private String dischargeDeptName;

	@Column(name="DISCHARGE_DISEASE_NAME_MAIN", length=500)
	private String dischargeDiseaseNameMain;

	@Column(name="DOCTOR_ID", length=320)
	private String doctorId;

	@Column(name="DOCTOR_NAME", length=320)
	private String doctorName;

	@Column(name="DOSAGE_FORM", length=320)
	private String dosageForm;

	@Column(name="DRUG_SPEC", length=320)
	private String drugSpec;

	@Column(name="HISID")
	private String hisid;

	@Column(name="HOSPITAL_ID", length=320)
	private String hospitalId;

	@Column(name="HOSPITAL_NAME", length=320)
	private String hospitalName;

	@Column(name="ITEM_ID", length=320)
	private String itemId;

	@Column(name="ITEM_ID_HOSP", length=320)
	private String itemIdHosp;

	@Column(name="ITEM_NAME", length=320)
	private String itemName;

	@Column(name="ITEM_NAME_HOSP", length=320)
	private String itemNameHosp;

	@Column(name="\"MONTH\"", precision=22)
	private String month;

	@Column(name="num",precision=22)
	private String num;

	@Column(name="P_CATEGORY", length=320)
	private String pCategory;

	@Column(name="P_TYPE", length=320)
	private String pType;

	@Column(name="P_TYPE_PCT", precision=22)
	private String pTypePct;

	@Column(name="PACKAGE_UNIT", length=320)
	private String packageUnit;

	@Column(name="PATIENT_ID", length=320)
	private String patientId;

	@Column(name="UNIT_PRICE", precision=22)
	private String unitPrice;

	@Column(name="USAGE_DATE")
	private String usageDate;

	@Column(name="USAGE_DATE_FLAG", length=320)
	private String usageDateFlag;

	@Column(name="\"YEAR\"", precision=22)
	private String year;

	@Column(name="zyh",length=320)
	private String zyh;

	public Noexists1() {
	}


    
	/**
	 * @return the detailId
	 */
	public String getDetailId() {
		return detailId;
	}



	/**
		 * @return the hisId1
		 */
		public String getHisId1() {
			return hisId1;
		}

		/**
		 * @param hisId1 the hisId1 to set
		 */
		public void setHisId1(String hisId1) {
			this.hisId1 = hisId1;
		}

		/**
		 * @return the itemId1
		 */
		public String getItemId1() {
			return itemId1;
		}

		/**
		 * @param itemId1 the itemId1 to set
		 */
		public void setItemId1(String itemId1) {
			this.itemId1 = itemId1;
		}

		/**
		 * @return the itemName1
		 */
		public String getItemName1() {
			return itemName1;
		}

		/**
		 * @param itemName1 the itemName1 to set
		 */
		public void setItemName1(String itemName1) {
			this.itemName1 = itemName1;
		}
	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
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
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}

	/**
	 * @return the dischargeDeptId
	 */
	public String getDischargeDeptId() {
		return dischargeDeptId;
	}

	/**
	 * @param dischargeDeptId the dischargeDeptId to set
	 */
	public void setDischargeDeptId(String dischargeDeptId) {
		this.dischargeDeptId = dischargeDeptId;
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
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
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
	 * @return the pTypePct
	 */
	public String getpTypePct() {
		return pTypePct;
	}

	/**
	 * @param pTypePct the pTypePct to set
	 */
	public void setpTypePct(String pTypePct) {
		this.pTypePct = pTypePct;
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
	 * @return the unitPrice
	 */
	public String getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the usageDate
	 */
	public String getUsageDate() {
		return usageDate;
	}

	/**
	 * @param usageDate the usageDate to set
	 */
	public void setUsageDate(String usageDate) {
		this.usageDate = usageDate;
	}

	/**
	 * @return the usageDateFlag
	 */
	public String getUsageDateFlag() {
		return usageDateFlag;
	}

	/**
	 * @param usageDateFlag the usageDateFlag to set
	 */
	public void setUsageDateFlag(String usageDateFlag) {
		this.usageDateFlag = usageDateFlag;
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
	 * @return the zyh
	 */
	public String getZyh() {
		return zyh;
	}

	/**
	 * @param zyh the zyh to set
	 */
	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}



}