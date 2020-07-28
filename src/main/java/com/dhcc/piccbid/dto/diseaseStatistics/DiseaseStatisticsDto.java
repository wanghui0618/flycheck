package com.dhcc.piccbid.dto.diseaseStatistics;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;

public class DiseaseStatisticsDto extends BaseAbstractDto{

	 private static final long serialVersionUID = 1L;

	    int page;
	    int limit;
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		
	    
}
