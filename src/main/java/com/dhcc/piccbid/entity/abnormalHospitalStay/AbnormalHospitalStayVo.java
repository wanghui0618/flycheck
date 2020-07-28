package com.dhcc.piccbid.entity.abnormalHospitalStay;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//明细
public class AbnormalHospitalStayVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int code;
	private int total;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	private String detailId;
	private Date billDate;
	private BigDecimal bmiConveredAmount;
	private BigDecimal bmiPayAmount;
	private BigDecimal cost;
	private String dischargeDeptId;
	private String dischargeDeptName;
	private String dischargeDiseaseNameMain;
	private String doctorId;
	private String doctorName;
	private String dosageForm;
	private String drugSpec;
	private String hisid;
	private String hospitalId;
	private String hospitalName;
	private String itemId;
	private String itemIdHosp;
	private String itemName;
	private String itemNameHosp;
	private BigDecimal month;
	private BigDecimal num;
	private String pCategory;
	private String pType;
	private BigDecimal pTypePct;
	private String packageUnit;
	private String patientId;
	private BigDecimal unitPrice;
	private Date usageDate;
	private String usageDateFlag;
	private BigDecimal year;
	private String zyh;
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
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
	public String getDischargeDiseaseNameMain() {
		return dischargeDiseaseNameMain;
	}
	public void setDischargeDiseaseNameMain(String dischargeDiseaseNameMain) {
		this.dischargeDiseaseNameMain = dischargeDiseaseNameMain;
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
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getDrugSpec() {
		return drugSpec;
	}
	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemIdHosp() {
		return itemIdHosp;
	}
	public void setItemIdHosp(String itemIdHosp) {
		this.itemIdHosp = itemIdHosp;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemNameHosp() {
		return itemNameHosp;
	}
	public void setItemNameHosp(String itemNameHosp) {
		this.itemNameHosp = itemNameHosp;
	}
	public BigDecimal getMonth() {
		return month;
	}
	public void setMonth(BigDecimal month) {
		this.month = month;
	}
	public BigDecimal getNum() {
		return num;
	}
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	public String getpCategory() {
		return pCategory;
	}
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public BigDecimal getpTypePct() {
		return pTypePct;
	}
	public void setpTypePct(BigDecimal pTypePct) {
		this.pTypePct = pTypePct;
	}
	public String getPackageUnit() {
		return packageUnit;
	}
	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
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
	public BigDecimal getYear() {
		return year;
	}
	public void setYear(BigDecimal year) {
		this.year = year;
	}
	public String getZyh() {
		return zyh;
	}
	public void setZyh(String zyh) {
		this.zyh = zyh;
	}
	
	
	

}
