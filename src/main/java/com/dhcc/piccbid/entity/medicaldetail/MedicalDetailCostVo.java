/**
 * 
 */
package com.dhcc.piccbid.entity.medicaldetail;

import java.io.Serializable;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @version V1.0
 * @date 2019-07-31 02:37:37
 */
public class MedicalDetailCostVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//总病例数
	private Integer totalCase;
	//医保金额
	private double insureCost;
	//总费用
	private double totalCost;
	private String orgCode;
	private String orgName;
	private String cityCode;
	//项目金额
	private double itemCost;
	//项目类别
	private String projectType;
	//药品总费用
	private double medicalCost;
	//药品费用占比
	private String medicalCostMix;
	//诊疗总费用
	private double treatmentCost;
	//诊疗总费用占比
	private String treatmentCostMix;
	//耗材总费用
	private double consumablesCost;
	//耗材总费用占比
	private String consumablesCostMix;
	
	private double qita;
	
	private String qitaMix;
	
	private String cityName;
	
	private String selfCost;
	public Integer getTotalCase() {
		return totalCase;
	}
	public void setTotalCase(Integer totalCase) {
		this.totalCase = totalCase;
	}
	public double getInsureCost() {
		return insureCost;
	}
	public void setInsureCost(double insureCost) {
		this.insureCost = insureCost;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public double getItemCost() {
		return itemCost;
	}
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public double getMedicalCost() {
		return medicalCost;
	}
	public void setMedicalCost(double medicalCost) {
		this.medicalCost = medicalCost;
	}
	public String getMedicalCostMix() {
		return medicalCostMix;
	}
	public void setMedicalCostMix(String medicalCostMix) {
		this.medicalCostMix = medicalCostMix;
	}
	public double getTreatmentCost() {
		return treatmentCost;
	}
	public void setTreatmentCost(double treatmentCost) {
		this.treatmentCost = treatmentCost;
	}
	public String getTreatmentCostMix() {
		return treatmentCostMix;
	}
	public void setTreatmentCostMix(String treatmentCostMix) {
		this.treatmentCostMix = treatmentCostMix;
	}
	public double getConsumablesCost() {
		return consumablesCost;
	}
	public void setConsumablesCost(double consumablesCost) {
		this.consumablesCost = consumablesCost;
	}
	public String getConsumablesCostMix() {
		return consumablesCostMix;
	}
	public void setConsumablesCostMix(String consumablesCostMix) {
		this.consumablesCostMix = consumablesCostMix;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getSelfCost() {
		return selfCost;
	}
	public void setSelfCost(String selfCost) {
		this.selfCost = selfCost;
	}
	public double getQita() {
		return qita;
	}
	public void setQita(double qita) {
		this.qita = qita;
	}
	public String getQitaMix() {
		return qitaMix;
	}
	public void setQitaMix(String qitaMix) {
		this.qitaMix = qitaMix;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

}
