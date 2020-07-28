package com.dhcc.piccbid.entity.admin;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String integrityName;
	
	private String orgName;
	
	private  String cityName;
	
	private String hospitalName;
	
	private int updateNumber;
	
	private String allNumber;
	
	private int totalNumber;
	
	private int monthDay;
	
	private String integrityNumber;
	
	private int unintegrityNumber;
	
	private int effectNumber;
	
	private int uneffectNumber;
	
	private int effective;
	
	private int zynumber;
	
	private int mznumber;
	
	private int mtnumber;
	
	private String pnumber;
	
	private String year;
	
	private String area;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(int monthDay) {
		this.monthDay = monthDay;
	}

	public int getUneffectNumber() {
		return uneffectNumber;
	}

	public void setUneffectNumber(int uneffectNumber) {
		this.uneffectNumber = uneffectNumber;
	}

	public int getEffective() {
		return effective;
	}

	public void setEffective(int effective) {
		this.effective = effective;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getIntegrityName() {
		return integrityName;
	}

	public void setIntegrityName(String integrityName) {
		this.integrityName = integrityName;
	}
	

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(String allNumber) {
		this.allNumber = allNumber;
	}

	public String getIntegrityNumber() {
		return integrityNumber;
	}

	public void setIntegrityNumber(String integrityNumber) {
		this.integrityNumber = integrityNumber;
	}

	public int getZynumber() {
		return zynumber;
	}

	public void setZynumber(int zynumber) {
		this.zynumber = zynumber;
	}

	public int getMznumber() {
		return mznumber;
	}

	public void setMznumber(int mznumber) {
		this.mznumber = mznumber;
	}

	public int getMtnumber() {
		return mtnumber;
	}

	public void setMtnumber(int mtnumber) {
		this.mtnumber = mtnumber;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public int getUpdateNumber() {
		return updateNumber;
	}

	public void setUpdateNumber(int updateNumber) {
		this.updateNumber = updateNumber;
	}

	public int getUnintegrityNumber() {
		return unintegrityNumber;
	}

	public void setUnintegrityNumber(int unintegrityNumber) {
		this.unintegrityNumber = unintegrityNumber;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getEffectNumber() {
		return effectNumber;
	}

	public void setEffectNumber(int effectNumber) {
		this.effectNumber = effectNumber;
	}
	
	
	
	
	
    
    

}
