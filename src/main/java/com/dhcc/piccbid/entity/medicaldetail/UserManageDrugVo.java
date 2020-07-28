package com.dhcc.piccbid.entity.medicaldetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



public class UserManageDrugVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
		private String chargeType;
		
		
		private int chargeNum;
		
		
		private float totalMoney;


		


		public String getChargeType() {
			return chargeType;
		}


		public void setChargeType(String chargeType) {
			this.chargeType = chargeType;
		}


		public int getChargeNum() {
			return chargeNum;
		}


		public void setChargeNum(int chargeNum) {
			this.chargeNum = chargeNum;
		}


		public float getTotalMoney() {
			return totalMoney;
		}


		public void setTotalMoney(float totalMoney) {
			this.totalMoney = totalMoney;
		}


		@Override
		public String toString() {
			return "UserManageDrugVo [chargeType=" + chargeType + ", chargeNum=" + chargeNum + ", totalMoney="
					+ totalMoney + "]";
		}


		

		

		
		
	
	
}
