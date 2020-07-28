package com.dhcc.piccbid.entity.flyDrug;

import java.io.Serializable;

public class FlyDrugDetailVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String itemCodeIns;
	private double cost;
	private int itemNum;
	private double itemPrice;
	private String itemNameIns;
	private String balanceDate;
	private String chargeType;
	private String inFlag;
	private String type;
	
	public String getItemCodeIns() {
		return itemCodeIns;
	}
	public void setItemCodeIns(String itemCodeIns) {
		this.itemCodeIns = itemCodeIns;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemNameIns() {
		return itemNameIns;
	}
	public void setItemNameIns(String itemNameIns) {
		this.itemNameIns = itemNameIns;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
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
