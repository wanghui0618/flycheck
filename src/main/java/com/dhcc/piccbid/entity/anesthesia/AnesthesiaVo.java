/**
 * 
 */
package com.dhcc.piccbid.entity.anesthesia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author zwy15
 *
 */
public class AnesthesiaVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String hospitalId;
	
	//医院名称
	private String hospitalName;
	
	//就诊途径
    private String visitingRoute;
	
	//人员类型
	private String benefitGroupId;
	
	//年度
	private String year;
	
	//住院人数
	private String peopleNumber;
	
	//住院人次
	private String peopleNumber2;
	
	//总费用
	private String medicalTotal;
	
	//报销费用
	private String medicalBmi;
	
	//平均费用
	private String avgCost;
	
	//平均住院天数
	private String avgDay;
	
	//药占比
	private String drugShare;
	
	//月份
	//病种名字
	private String drgsName;
	
	//药品名字
	private String drugName;
	
	//药品数量
	private String drugNumber;
	
	//药品费用
	//飞检表detail字段
	private String detailId;
	private String hisid;
	private String patientId;
	private String zyh;
	private String dischargeDeptId;
	private String dischargeDeptName;
	private String doctorId;
	//private String doctorName;
	private String dischargeDiseaseNameMain;
	private String pCategory;
	private Date usageDate;
	private String usageDateFlag;
	private Date billDate;
	private BigDecimal month;
	private String itemIdHosp;
	private String itemNameHosp;
	private String itemId;
	private String itemType;
	//private String itemName;
	private String drugSpec;
	private String dosageForm;
	private String packageUnit;
	private BigDecimal unitPrice;
	private BigDecimal num;
	private String cost;
	private String bmiConveredAmount;
	private String bmiPayAmount;
	private String pType;
	private String pTypePct;
	
	
	


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
	public String getVisitingRoute() {
		return visitingRoute;
	}
	public void setVisitingRoute(String visitingRoute) {
		this.visitingRoute = visitingRoute;
	}
	public String getBenefitGroupId() {
		return benefitGroupId;
	}
	public void setBenefitGroupId(String benefitGroupId) {
		this.benefitGroupId = benefitGroupId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	public String getPeopleNumber2() {
		return peopleNumber2;
	}
	public void setPeopleNumber2(String peopleNumber2) {
		this.peopleNumber2 = peopleNumber2;
	}
	public String getMedicalTotal() {
		return medicalTotal;
	}
	public void setMedicalTotal(String medicalTotal) {
		this.medicalTotal = medicalTotal;
	}
	public String getMedicalBmi() {
		return medicalBmi;
	}
	public void setMedicalBmi(String medicalBmi) {
		this.medicalBmi = medicalBmi;
	}
	public String getAvgCost() {
		return avgCost;
	}
	public void setAvgCost(String avgCost) {
		this.avgCost = avgCost;
	}
	public String getAvgDay() {
		return avgDay;
	}
	public void setAvgDay(String avgDay) {
		this.avgDay = avgDay;
	}
	public String getDrugShare() {
		return drugShare;
	}
	public void setDrugShare(String drugShare) {
		this.drugShare = drugShare;
	}
	public String getDrgsName() {
		return drgsName;
	}
	public void setDrgsName(String drgsName) {
		this.drgsName = drgsName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugNumber() {
		return drugNumber;
	}
	public void setDrugNumber(String drugNumber) {
		this.drugNumber = drugNumber;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
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
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getBmiConveredAmount() {
		return bmiConveredAmount;
	}
	public void setBmiConveredAmount(String bmiConveredAmount) {
		this.bmiConveredAmount = bmiConveredAmount;
	}
	public String getBmiPayAmount() {
		return bmiPayAmount;
	}
	public void setBmiPayAmount(String bmiPayAmount) {
		this.bmiPayAmount = bmiPayAmount;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getpTypePct() {
		return pTypePct;
	}
	public void setpTypePct(String pTypePct) {
		this.pTypePct = pTypePct;
	}

}
