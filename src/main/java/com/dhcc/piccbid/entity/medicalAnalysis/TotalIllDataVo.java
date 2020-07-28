package com.dhcc.piccbid.entity.medicalAnalysis;

/**
* <p>标题: YearDataVo.java</p>
* <p>业务描述:基层医疗卫生开发平台</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2013</p>
* @author 姚凯
* @date 2019年8月8日
* @version V1.0 
*/
public class TotalIllDataVo {
	

		
		/*
		 * 自主分析系统主页总病例数界面
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String orgName;  //医院名
		
		private String totalNumber; //病例数
		
		private String totalCost; //医疗费用
		
		private String expenseCost;  //医保报销金额

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
			orgName = orgName;
		}

		/**  
		* @return totalNumber 
		*/
		public String getTotalNumber() {
			return totalNumber;
		}

		/**  
		* @param totalNumber totalNumber 
		*/
		public void setTotalNumber(String totalNumber) {
			this.totalNumber = totalNumber;
		}

		/**  
		* @return medicalExpense 
		*/
		/**  
		* @return expenseCost 
		*/
		public String getExpenseCost() {
			return expenseCost;
		}

		/**  
		* @param expenseCost expenseCost 
		*/
		public void setExpenseCost(String expenseCost) {
			this.expenseCost = expenseCost;
		}

		/**  
		* @return totalCost 
		*/
		public String getTotalCost() {
			return totalCost;
		}

		/**  
		* @param totalCost totalCost 
		*/
		public void setTotalCost(String totalCost) {
			this.totalCost = totalCost;
		}

	}

