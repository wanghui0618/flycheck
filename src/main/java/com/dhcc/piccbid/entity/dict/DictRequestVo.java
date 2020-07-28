package com.dhcc.piccbid.entity.dict;

import java.io.Serializable;


/**
 * 字典响应vo类.
 * 
 */
public class DictRequestVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tableName;
	private String dictType;
	private String param;
	private String ttName;
	
	

	/**
	 * @return the ttName
	 */
	public String getTtName() {
		return ttName;
	}

	/**
	 * @param ttName the ttName to set
	 */
	public void setTtName(String ttName) {
		this.ttName = ttName;
	}

	public DictRequestVo() {
	}

	/**  
	 * @return tableName 
	 */
	public String getTableName() {
		return tableName;
	}

	/**  
	 * @param tableName tableName 
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**  
	 * @return dictType 
	 */
	public String getDictType() {
		return dictType;
	}

	/**  
	 * @param dictType dictType 
	 */
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	/**  
	 * @return param 
	 */
	public String getParam() {
		return param;
	}

	/**  
	 * @param param param 
	 */
	public void setParam(String param) {
		this.param = param;
	}


	
}