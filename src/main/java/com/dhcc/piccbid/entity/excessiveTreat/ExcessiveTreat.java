package com.dhcc.piccbid.entity.excessiveTreat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_PICCBID_EXCESSIVE_TREATMENT")
public class ExcessiveTreat implements Serializable{
	@Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
	
	@Column(name="CITY_CODE")
	private String cityCode;
	
	@Column(name="TYPE_NO")
	private String typeNo;
	
	@Column(name="TYPE_NAME")
	private String typeName;
	
	@Column(name="ITEM_CODE")
	private String itemCode;

	@Column(name="ITEM_NAME")
	private String itemName;
	
	@Column(name="LOGIC_TYPE")
	private String logicType;
	
	@Column(name="LOGIC_TYPE_DETAIL")
	private String logicTypeDetail;
	
	@Column(name="LOGIC")
	private String logic;
	
	@Column(name="LOGIC_SQL1")
	private String logicSql1;
	
	@Column(name="LOGIC_SQL2")
	private String logicSql2;
	
	@Column(name="LOGIC_SQL_PARA")
	private String logicSqlPara;
	
	@Column(name="COMMENTS")
	private String comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLogicType() {
		return logicType;
	}

	public void setLogicType(String logicType) {
		this.logicType = logicType;
	}

	public String getLogicTypeDetail() {
		return logicTypeDetail;
	}

	public void setLogicTypeDetail(String logicTypeDetail) {
		this.logicTypeDetail = logicTypeDetail;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getLogicSql1() {
		return logicSql1;
	}

	public void setLogicSql1(String logicSql1) {
		this.logicSql1 = logicSql1;
	}

	public String getLogicSql2() {
		return logicSql2;
	}

	public void setLogicSql2(String logicSql2) {
		this.logicSql2 = logicSql2;
	}

	public String getLogicSqlPara() {
		return logicSqlPara;
	}

	public void setLogicSqlPara(String logicSqlPara) {
		this.logicSqlPara = logicSqlPara;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
