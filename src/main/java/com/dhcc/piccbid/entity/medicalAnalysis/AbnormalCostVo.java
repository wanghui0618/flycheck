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
public class AbnormalCostVo {
	

		
		/*
		 * 自主分析系统主页年度分析界面
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
        private String orgName;
        
        private String totalCost;
        
        private String yearData;

		private String monthTime; //月份
		
		private String totalCostAll;
		 
		private String tCsot;

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

		/**  
		* @return yearData 
		*/
		public String getYearData() {
			return yearData;
		}

		/**  
		* @param yearData yearData 
		*/
		public void setYearData(String yearData) {
			this.yearData = yearData;
		}

		/**  
		* @return monthTime 
		*/
		public String getMonthTime() {
			return monthTime;
		}

		/**  
		* @param monthTime monthTime 
		*/
		public void setMonthTime(String monthTime) {
			this.monthTime = monthTime;
		}

		/**  
		* @return totalCostAll 
		*/
		public String getTotalCostAll() {
			return totalCostAll;
		}

		/**  
		* @param totalCostAll totalCostAll 
		*/
		public void setTotalCostAll(String totalCostAll) {
			this.totalCostAll = totalCostAll;
		}

		/**  
		* @return tCsot 
		*/
		public String gettCsot() {
			return tCsot;
		}

		/**  
		* @param tCsot tCsot 
		*/
		public void settCsot(String tCsot) {
			this.tCsot = tCsot;
		}
		

	}

