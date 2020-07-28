/**
 * 
 */
package com.dhcc.piccbid.entity.hosanalysis;

import java.io.Serializable;

/**
 * @author xtl
 *
 */
public class HosanalysisVo implements Serializable{
	private  static final   long serialVersionUID=1L;
	private String pnumber;
	private String prate;
	private String illperson ;
	private String illmoney;
	private String area;
	private String ptime;
	private String pname;
	private  String year;
	private String pcost;
	private String person;
	private String totalcost;
	private String pavgcost;
	private double cvnum;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCostmoney() {
		return costmoney;
	}
	public void setCostmoney(String costmoney) {
		this.costmoney = costmoney;
	}
	private String money;
	private String costmoney;
	private String total;
	
	public String getIllperson() {
		return illperson;
	}
	public void setIllperson(String illperson) {
		this.illperson = illperson;
	}
	public String getIllmoney() {
		return illmoney;
	}
	public void setIllmoney(String illmoney) {
		this.illmoney = illmoney;
	}
	public String getIllrate() {
		return illrate;
	}
	public void setIllrate(String illrate) {
		this.illrate = illrate;
	}
	private String illrate;
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the prate
	 */
	public String getPrate() {
		return prate;
	}
	/**
	 * @param prate the prate to set
	 */
	public void setPrate(String prate) {
		this.prate = prate;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the pcost
	 */
	public String getPcost() {
		return pcost;
	}
	/**
	 * @param pcost the pcost to set
	 */
	public void setPcost(String pcost) {
		this.pcost = pcost;
	}
	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}
	/**
	 * @return the totalcost
	 */
	public String getTotalcost() {
		return totalcost;
	}
	/**
	 * @param totalcost the totalcost to set
	 */
	public void setTotalcost(String totalcost) {
		this.totalcost = totalcost;
	}
	/**
	 * @return the pavgcost
	 */
	public String getPavgcost() {
		return pavgcost;
	}
	/**
	 * @param pavgcost the pavgcost to set
	 */
	public void setPavgcost(String pavgcost) {
		this.pavgcost = pavgcost;
	}
	/**  
	* @return cvnum 
	*/
	/**  
	* @return cvnum 
	*/
	public double getCvnum() {
		return cvnum;
	}
	/**  
	* @param cvnum cvnum 
	*/
	public void setCvnum(double cvnum) {
		this.cvnum = cvnum;
	}
}
