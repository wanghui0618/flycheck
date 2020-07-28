package com.dhcc.piccbid.entity.dict;

import java.io.Serializable;


/**
 * 字典响应vo类.
 * 
 */
public class DictResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String value;
	private String text;

	public DictResponseVo() {
	}

	/**  
	 * @return id 
	 */
	public String getId() {
		return id;
	}

	/**  
	 * @param id id 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**  
	 * @return text 
	 */
	public String getText() {
		return text;
	}

	/**  
	 * @param text text 
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**  
	 * @return value 
	 */
	public String getValue() {
		return value;
	}

	/**  
	 * @param value value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	

}