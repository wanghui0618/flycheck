package com.dhcc.piccbid.entity.dictdiag;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_PICCBID_DISEASES_CLASS database table.
 * 
 */
@Entity
@Table(name="T_PICCBID_DICT_DIAG")
public class Dictdiag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", unique=true, nullable=false, length=32)
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;

	@Column(name="DIAG_CODE")
	private String diagCode;

	@Column(name="DIAG_NAME")
	private String diagName;

	@Column(name="DIAG_DESC")
	private String diagDesc;

	@Column(name="PARENT_LEAF")
	private String parentLeaf;
	
	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="PARENT_INDEX")
	private String parentIndex;

	public Dictdiag() {
	}

	public String getDiagCode() {
		return diagCode;
	}

	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}

	public String getDiagName() {
		return diagName;
	}

	public void setDiagName(String diagName) {
		this.diagName = diagName;
	}

	public String getDiagDesc() {
		return diagDesc;
	}

	public void setDiagDesc(String diagDesc) {
		this.diagDesc = diagDesc;
	}

	public String getParentLeaf() {
		return parentLeaf;
	}

	public void setParentLeaf(String parentLeaf) {
		this.parentLeaf = parentLeaf;
	}

	

	/**  
	* @return parentId 
	*/
	public String getParentId() {
		return parentId;
	}

	/**  
	* @param parentId parentId 
	*/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(String parentIndex) {
		this.parentIndex = parentIndex;
	}
}