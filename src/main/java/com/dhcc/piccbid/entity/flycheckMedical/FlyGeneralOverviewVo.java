/**
 * 
 */
package com.dhcc.piccbid.entity.flycheckMedical;

import java.io.Serializable;

/**
 * @author lenovo
 *
 */
public class FlyGeneralOverviewVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//医院编码
	private String hospitalId;
	
	//医院名称
	private String hospitalName;
	
	//就诊途径
    private String visitingRoute;
	
	//人员类型
	private String benefitGroupId;
	//人员类型
	private String benefitType;
	
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
	private String month;
	
	//病种名字
	private String drgsName;
	
	//药品名字
	private String drugName;
	
	//药品数量
	private String drugNumber;
	
	//药品费用
	private String cost;
	//项目编码
	private String itemId;
	//项目编码集合
	private String itemIds;
	private String visitsItemName;
	private String visitsCount;
	private String medicalName;
	private String medicalCost;
	private String drgName;
	private String itemIdList;

	
	/**
	 * @return the benefitType
	 */
	public String getBenefitType() {
		return benefitType;
	}

	/**
	 * @param benefitType the benefitType to set
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public String getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(String itemIdList) {
		this.itemIdList = itemIdList;
	}

	public String getDrgName() {
		return drgName;
	}

	public void setDrgName(String drgName) {
		this.drgName = drgName;
	}

	public String getMedicalName() {
		return medicalName;
	}

	public void setMedicalName(String medicalName) {
		this.medicalName = medicalName;
	}

	public String getMedicalCost() {
		return medicalCost;
	}

	public void setMedicalCost(String medicalCost) {
		this.medicalCost = medicalCost;
	}

	public String getVisitsItemName() {
		return visitsItemName;
	}

	public void setVisitsItemName(String visitsItemName) {
		this.visitsItemName = visitsItemName;
	}

	public String getVisitsCount() {
		return visitsCount;
	}

	public void setVisitsCount(String visitsCount) {
		this.visitsCount = visitsCount;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
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
	 * @return the benefitGroupId
	 */
	public String getBenefitGroupId() {
		return benefitGroupId;
	}

	/**
	 * @param benefitGroupId the benefitGroupId to set
	 */
	public void setBenefitGroupId(String benefitGroupId) {
		this.benefitGroupId = benefitGroupId;
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
	 * @return the peopleNumber
	 */
	public String getPeopleNumber() {
		return peopleNumber;
	}

	/**
	 * @param peopleNumber the peopleNumber to set
	 */
	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	/**
	 * @return the peopleNumber2
	 */
	public String getPeopleNumber2() {
		return peopleNumber2;
	}

	/**
	 * @param peopleNumber2 the peopleNumber2 to set
	 */
	public void setPeopleNumber2(String peopleNumber2) {
		this.peopleNumber2 = peopleNumber2;
	}

	/**
	 * @return the medicalTotal
	 */
	public String getMedicalTotal() {
		return medicalTotal;
	}

	/**
	 * @param medicalTotal the medicalTotal to set
	 */
	public void setMedicalTotal(String medicalTotal) {
		this.medicalTotal = medicalTotal;
	}

	/**
	 * @return the medicalBmi
	 */
	public String getMedicalBmi() {
		return medicalBmi;
	}

	/**
	 * @param medicalBmi the medicalBmi to set
	 */
	public void setMedicalBmi(String medicalBmi) {
		this.medicalBmi = medicalBmi;
	}

	/**
	 * @return the avgCost
	 */
	public String getAvgCost() {
		return avgCost;
	}

	/**
	 * @param avgCost the avgCost to set
	 */
	public void setAvgCost(String avgCost) {
		this.avgCost = avgCost;
	}

	/**
	 * @return the avgDay
	 */
	public String getAvgDay() {
		return avgDay;
	}

	/**
	 * @param avgDay the avgDay to set
	 */
	public void setAvgDay(String avgDay) {
		this.avgDay = avgDay;
	}

	/**
	 * @return the drugShare
	 */
	public String getDrugShare() {
		return drugShare;
	}

	/**
	 * @param drugShare the drugShare to set
	 */
	public void setDrugShare(String drugShare) {
		this.drugShare = drugShare;
	}
	
	/**
	 * @return the visitingRoute
	 */
	public String getVisitingRoute() {
		return visitingRoute;
	}

	/**
	 * @param visitingRoute the visitingRoute to set
	 */
	public void setVisitingRoute(String visitingRoute) {
		this.visitingRoute = visitingRoute;
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
	 * @return the drgsName
	 */
	public String getDrgsName() {
		return drgsName;
	}

	/**
	 * @param drgsName the drgsName to set
	 */
	public void setDrgsName(String drgsName) {
		this.drgsName = drgsName;
	}

	/**
	 * @return the drugName
	 */
	public String getDrugName() {
		return drugName;
	}

	/**
	 * @param drugName the drugName to set
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	/**
	 * @return the drugNumber
	 */
	public String getDrugNumber() {
		return drugNumber;
	}

	/**
	 * @param drugNumber the drugNumber to set
	 */
	public void setDrugNumber(String drugNumber) {
		this.drugNumber = drugNumber;
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

	


}
