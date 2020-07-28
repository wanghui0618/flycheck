/**
 * 
 */
package com.dhcc.piccbid.entity.home;

import java.io.Serializable;
import java.sql.Date;

import org.hibernate.type.StringNVarcharType;

/**
 * @author xtl
 *
 */
public class HomeVo implements Serializable {
	private static final long serialVersionUID = 1L;	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String digit;
	private String name;
	private String city;
	public String getDigit() {
		return digit;
	}
	public void setDigit(String digit) {
		this.digit = digit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}