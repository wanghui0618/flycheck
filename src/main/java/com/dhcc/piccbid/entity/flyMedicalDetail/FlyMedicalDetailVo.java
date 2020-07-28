package com.dhcc.piccbid.entity.flyMedicalDetail;

import java.io.Serializable;

public class FlyMedicalDetailVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double money;//收费金额
	private int itemNum;//收费次数
	private String balanceDate;//收费日期
	private String itemNameHosp;//项目名称
	private String hospitalName;//医院名称
	private String inFlag;//查询时间
	private String type;//排序字段 1.根据收费金额降序2.根据收费次数降序
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public String getItemNameHosp() {
		return itemNameHosp;
	}
	public void setItemNameHosp(String itemNameHosp) {
		this.itemNameHosp = itemNameHosp;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getInFlag() {
		return inFlag;
	}
	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	

}
