/**
 * 
 */
package com.dhcc.piccbid.entity.allAnalysis;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author 59545
 *
 */
public class AllAnalysis2 {
	
	@Id
	private String id;
	@Column(name="ORG_NAME")
	private String orgName;
	@Column(name="ALL_COUNT")
	private String allCount;
	@Column(name="ALL_PRICE")
	private String allPrice;
	@Column(name="AVG_PRICE")
	private String avgPrice;
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
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
