package com.dhcc.piccbid.entity.medicaldetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



public class UserManageDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
		private String itemName;
		
		
		private int itemNumber;
		
		
		private float totalMoney;


		public String getItemName() {
			return itemName;
		}


		public void setItemName(String itemName) {
			this.itemName = itemName;
		}


		public int getItemNumber() {
			return itemNumber;
		}


		public void setItemNumber(int itemNumber) {
			this.itemNumber = itemNumber;
		}


		public float getTotalMoney() {
			return totalMoney;
		}


		public void setTotalMoney(float totalMoney) {
			this.totalMoney = totalMoney;
		}


		@Override
		public String toString() {
			return "UserManageDetail [itemName=" + itemName + ", itemNumber=" + itemNumber + ", totalMoney="
					+ totalMoney + "]";
		}


		
		
	
	
}
