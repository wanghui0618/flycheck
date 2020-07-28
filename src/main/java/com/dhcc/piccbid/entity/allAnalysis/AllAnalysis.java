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
@Entity
public class AllAnalysis implements Serializable{

	
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="CON_DITION")
	private String conDition;
	@Column(name="ALL_COUNT")
	private String allCount;
	
	@Column(name="ALL_PRICE")
	private String allPrice;
	
	@Column(name="AVG_PRICE")
	private String avgPrice;
	
	@Column(name="YEARTIME")
	private String yearTime;
	

	/**
	 * @return the yearTime
	 */
	public String getYearTime() {
		return yearTime;
	}

	/**
	 * @param yearTime the yearTime to set
	 */
	public void setYearTime(String yearTime) {
		this.yearTime = yearTime;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the conDition
	 */
	public String getConDition() {
		return conDition;
	}

	/**
	 * @param conDition the conDition to set
	 */
	public void setConDition(String conDition) {
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
