package com.dhcc.piccbid.entity.dict;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_CKUP_DICT database table.
 * 
 */
@Entity
@Table(name="T_PICCBID_DICT_CITY")
public class Dict implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", unique=true, nullable=false, length=32)
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;

	@Column(name="CITY_CODE", nullable=false, length=200)
	private String cityCode;

	@Column(name="CITY_NAME", nullable=false, length=200)
	private String cityName;
	
	@Column(name="\"VALUE\"", nullable=false, length=200)
	private String value;
	
	public Dict(){
		
	}

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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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