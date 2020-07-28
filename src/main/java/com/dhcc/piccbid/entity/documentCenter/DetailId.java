package com.dhcc.piccbid.entity.documentCenter;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


public class DetailId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String medicalDetailId;
	
	private String medicalId;
	
	private String typeName;
	
	private String typeNo;
	
	private String returnCode;
	
	private String returnName;
	
	private String returnDesc;
	
	private String itemCode;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="SYS_AUDITING_DATE")
	private Date sysAuditingDate;
	
	
	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedicalDetailId() {
		return medicalDetailId;
	}

	public void setMedicalDetailId(String medicalDetailId) {
		this.medicalDetailId = medicalDetailId;
	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnName() {
		return returnName;
	}

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	public String getReturnDesc() {
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Date getSysAuditingDate() {
		return sysAuditingDate;
	}

	public void setSysAuditingDate(Date sysAuditingDate) {
		this.sysAuditingDate = sysAuditingDate;
	}
	
	


	
	
	
	
	
}
