package com.dhcc.piccbid.entity.drugcheck;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//医保三大目录-药品结果集
@Entity
@Table(name="T_DICT_TMD_DRUG")
public class Drugcheck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_CODE")
    private String itemCode;

	/**  
	* @return itemCode 
	*/
	public String getItemCode() {
		return itemCode;
	}

	/**  
	* @param itemCode itemCode 
	*/
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

		
}