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
public class DrugCostDataVo {
	

		
		/*
		 * 自主分析系统主页年度分析界面
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String orgName; //医院名
				
		private String orgCode;
		
		private String drugFee;
		
		private String balanceDate;
		
		private String totalRatio;

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
		* @return drugFee 
		*/
		public String getDrugFee() {
			return drugFee;
		}

		/**  
		* @param drugFee drugFee 
		*/
		public void setDrugFee(String drugFee) {
			this.drugFee = drugFee;
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

		/**  
		* @return totalRatio 
		*/
		public String getTotalRatio() {
			return totalRatio;
		}

		/**  
		* @param totalRatio totalRatio 
		*/
		public void setTotalRatio(String totalRatio) {
			this.totalRatio = totalRatio;
		}

	}

