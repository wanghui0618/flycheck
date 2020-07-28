/**
 * 
 */
package com.dhcc.piccbid.entity.allAnalysis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 59545
 *
 */

public class AllAnalysisVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String conDition;
	
	private String allCount;
	
	private String allPrice;
	
	private String avgPrice;

	/**
	 * @return the condition
	 */
	public String getConDition() {
		return conDition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String conDition) {
		this.conDition = conDition;
	}

	/**
	 * @return the allCount
	 */
	public String getAllCount() {
		return allCount;
	}

	/**
	 * @param allCount the allCount to set
	 */
	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}

	/**
	 * @return the allPrice
	 */
	public String getAllPrice() {
		return allPrice;
	}

	/**
	 * @param allPrice the allPrice to set
	 */
	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	/**
	 * @return the avgPrice
	 */
	public String getAvgPrice() {
		return avgPrice;
	}

	/**
	 * @param avgPrice the avgPrice to set
	 */
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	
}
