package com.dhcc.piccbid.entity.medicalAnalysis;

import java.io.Serializable;

/**
* <p>标题: YearDataVo.java</p>
* <p>业务描述:基层医疗卫生开发平台</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2013</p>
* @author 姚凯
* @date 2019年8月8日
* @version V1.0 
*/
public class HospitalVo implements Serializable{
	

	private static final long serialVersionUID = 1L;	
	private String orgCode;
	private String orgName;
	private String avgDay;
	private String balanceDate;
	/**  
	* @return orgCode 
	*/
	public String getOrgCode() {
		return orgCode;
	}
	/**  
	* @param orgCode orgCode 
	*/
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**  
	* @return orgName 
	*/
	public String getOrgName() {
		return orgName;
	}
	/**  
	* @param orgName orgName 
	*/
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**  
	* @return avgDay 
	*/
	public String getAvgDay() {
		return avgDay;
	}
	/**  
	* @param avgDay avgDay 
	*/
	public void setAvgDay(String avgDay) {
		this.avgDay = avgDay;
	}
	/**  
	* @return balanceDate 
	*/
	public String getBalanceDate() {
		return balanceDate;
	}
	/**  
	* @param balanceDate balanceDate 
	*/
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
		

	}

