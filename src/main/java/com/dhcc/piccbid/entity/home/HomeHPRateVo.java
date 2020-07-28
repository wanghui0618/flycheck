/**
 * 
 */
package com.dhcc.piccbid.entity.home;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author xtl
 *
 */
public class HomeHPRateVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String city;
	private String area;
	private Date time;
	private String hnumber;
	private String pnumber;
	private String hrate;
	private String prate;
	private String digit;
	private String name;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getHnumber() {
		return hnumber;
	}
	public void setHnumber(String hnumber) {
		this.hnumber = hnumber;
	}
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getHrate() {
		return hrate;
	}
	public void setHrate(String hrate) {
		this.hrate = hrate;
	}
	public String getPrate() {
		return prate;
	}
	public void setPrate(String prate) {
		this.prate = prate;
	}
	/**
	 * @return the digit
	 */
	public String getDigit() {
		return digit;
	}
	/**
	 * @param digit the digit to set
	 */
	public void setDigit(String digit) {
		this.digit = digit;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
