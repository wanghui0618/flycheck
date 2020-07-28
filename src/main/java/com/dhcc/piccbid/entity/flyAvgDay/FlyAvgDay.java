package com.dhcc.piccbid.entity.flyAvgDay;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_FLY_MEDICAL database table.
 * 
 */
@Entity
@Table(name="T_FLY_MEDICAL")
public class FlyAvgDay implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="ADDRESS")
	private String address;

	@Column(name="ADMISSION_NO")
	private String admissionNo;

	@Column(name="CROWD_TYPE")
	private String crowdType;

	@Column(name="FUND_COST")
	private BigDecimal fundCost;

	@Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
	
	@Column(name="IDCARD")
	private String idcard;

	@Temporal(TemporalType.DATE)
	@Column(name="INHOS_DATE")
	private Date inhosDate;

	@Column(name="INHOS_DIAG")
	private String inhosDiag;
	
	@Column(name="NAME")
	private String name;

	@Column(name="ORG_NAME")
	private String orgName;

	@Temporal(TemporalType.DATE)
	@Column(name="OUTHOS_DATE")
	private Date outhosDate;

	@Column(name="OUTHOS_DEPART")
	private String outhosDepart;

	@Column(name="OUTHOS_DIAG")
	private String outhosDiag;
	
	@Column(name="SEX")
	private String sex;

	@Column(name="STAY_LENGTH")
	private BigDecimal stayLength;

	@Column(name="TOTAL_COST")
	private BigDecimal totalCost;
	
	private String inFlag;
	private String yearTime;
	private String allDay;
	private String avgStayLength;
	private String mTime;
	private String maxStayLength;
	private String mNum;
	public FlyAvgDay() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdmissionNo() {
		return this.admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getCrowdType() {
		return this.crowdType;
	}

	public void setCrowdType(String crowdType) {
		this.crowdType = crowdType;
	}

	public BigDecimal getFundCost() {
		return this.fundCost;
	}

	public void setFundCost(BigDecimal fundCost) {
		this.fundCost = fundCost;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Date getInhosDate() {
		return this.inhosDate;
	}

	public void setInhosDate(Date inhosDate) {
		this.inhosDate = inhosDate;
	}

	public String getInhosDiag() {
		return this.inhosDiag;
	}

	public void setInhosDiag(String inhosDiag) {
		this.inhosDiag = inhosDiag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getOuthosDate() {
		return this.outhosDate;
	}

	public void setOuthosDate(Date outhosDate) {
		this.outhosDate = outhosDate;
	}

	public String getOuthosDepart() {
		return this.outhosDepart;
	}

	public void setOuthosDepart(String outhosDepart) {
		this.outhosDepart = outhosDepart;
	}

	public String getOuthosDiag() {
		return this.outhosDiag;
	}

	public void setOuthosDiag(String outhosDiag) {
		this.outhosDiag = outhosDiag;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BigDecimal getStayLength() {
		return this.stayLength;
	}

	public void setStayLength(BigDecimal stayLength) {
		this.stayLength = stayLength;
	}

	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	/**  
	* @return inFlag 
	*/
	public String getInFlag() {
		return inFlag;
	}

	/**  
	* @param inFlag inFlag 
	*/
	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	/**  
	* @return yearTime 
	*/
	public String getYearTime() {
		return yearTime;
	}

	/**  
	* @param yearTime yearTime 
	*/
	public void setYearTime(String yearTime) {
		this.yearTime = yearTime;
	}

	/**  
	* @return allDay 
	*/
	public String getAllDay() {
		return allDay;
	}

	/**  
	* @param allDay allDay 
	*/
	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}

	/**  
	* @return avgStayLength 
	*/
	public String getAvgStayLength() {
		return avgStayLength;
	}

	/**  
	* @param avgStayLength avgStayLength 
	*/
	public void setAvgStayLength(String avgStayLength) {
		this.avgStayLength = avgStayLength;
	}

	/**  
	* @return mTime 
	*/
	public String getmTime() {
		return mTime;
	}

	/**  
	* @param mTime mTime 
	*/
	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	/**  
	* @return maxStayLength 
	*/
	public String getMaxStayLength() {
		return maxStayLength;
	}

	/**  
	* @param maxStayLength maxStayLength 
	*/
	public void setMaxStayLength(String maxStayLength) {
		this.maxStayLength = maxStayLength;
	}

	/**  
	* @return mNum 
	*/
	public String getmNum() {
		return mNum;
	}

	/**  
	* @param mNum mNum 
	*/
	public void setmNum(String mNum) {
		this.mNum = mNum;
	}

}