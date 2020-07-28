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
public class MonthlyTrendsDataVo {
	

		
		/*
		 * 自主分析系统主页年度分析界面
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String inHospital;   //住院费用
		
		private String outCost; //门诊费用
		
		private String inCost; //住院比率
		
		private String costRatio; //门诊比率

		private String monthTime; //月份
		
		private String inRatio;
		/**  
		* @return inHospital 
		*/
		public String getInHospital() {
			return inHospital;
		}

		/**  
		* @param inHospital inHospital 
		*/
		public void setInHospital(String inHospital) {
			this.inHospital = inHospital;
		}

		/**  
		* @return outCost 
		*/
		public String getOutCost() {
			return outCost;
		}

		/**  
		* @param outCost outCost 
		*/
		public void setOutCost(String outCost) {
			this.outCost = outCost;
		}

		/**  
		* @return costRatio 
		*/
		public String getCostRatio() {
			return costRatio;
		}

		/**  
		* @param costRatio costRatio 
		*/
		public void setCostRatio(String costRatio) {
			this.costRatio = costRatio;
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
		* @return inCost 
		*/
		public String getInCost() {
			return inCost;
		}

		/**  
		* @param inCost inCost 
		*/
		public void setInCost(String inCost) {
			this.inCost = inCost;
		}

		/**  
		* @return inRatio 
		*/
		public String getInRatio() {
			return inRatio;
		}

		/**  
		* @param inRatio inRatio 
		*/
		public void setInRatio(String inRatio) {
			this.inRatio = inRatio;
		}
		

	}

