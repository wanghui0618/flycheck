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
public class YearDataVo {
	

		
		/*
		 * 自主分析系统主页年度分析界面
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String orgName; //医院名
		
		private String orgCode;
		
		private String totalNumber; //病例数
		
		private String totalPnum;  //住院人数
		
		private String avgCost;//均次费用
		
		private String avgDay;//平均住院天数
		
		private String totalRatio;//药占比

		/**  
		* @return totalPnum 
		*/
		public String getTotalPnum() {
			return totalPnum;
		}

		/**  
		* @param totalPnum totalPnum 
		*/
		public void setTotalPnum(String totalPnum) {
			this.totalPnum = totalPnum;
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
		* @return avgCost 
		*/
		public String getAvgCost() {
			return avgCost;
		}

		/**  
		* @param avgCost avgCost 
		*/
		public void setAvgCost(String avgCost) {
			this.avgCost = avgCost;
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
		
		

	}

