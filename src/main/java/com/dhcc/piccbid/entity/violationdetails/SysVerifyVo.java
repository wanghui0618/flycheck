package com.dhcc.piccbid.entity.violationdetails;

import java.io.Serializable;
/*
 * 系统审核，
 * 违规统计Vo
*/
public class SysVerifyVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String typeNo;    //违规编码
	private String typeNames;  //违规名称
	private String countNum; //总数
	private String itemCost; //违规金额
	private String haveCountNum; //已审核总数
	private Integer sumNum;//总病例数
	private String sysStatus;
	private Double itemCost2;
	private Double countNum2;

	public Double getItemCost2() {
		return itemCost2;
	}

	public void setItemCost2(Double itemCost2) {
		this.itemCost2 = itemCost2;
	}

	public Double getCountNum2() {
		return countNum2;
	}

	public void setCountNum2(Double countNum2) {
		this.countNum2 = countNum2;
	}

	public String getSysStatus() {
		return sysStatus;
	}

	public void setSysStatus(String sysStatus) {
		this.sysStatus = sysStatus;
	}

	public String getItemCost() {
		return itemCost;
	}

	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}

	public Integer getSumNum() {
		return sumNum;
	}
	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}
	public String getHaveCountNum() {
		return haveCountNum;
	}
	public void setHaveCountNum(String haveCountNum) {
		this.haveCountNum = haveCountNum;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	
	public String getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(String typeNames) {
		this.typeNames = typeNames;
	}
	public String getCountNum() {
		return countNum;
	}
	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}
	
	
	
	
}
