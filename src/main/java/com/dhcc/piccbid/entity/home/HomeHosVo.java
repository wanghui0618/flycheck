/**
 * 
 */
package com.dhcc.piccbid.entity.home;

import java.io.Serializable;

/**
 * @author xtl
 *
 */
public class HomeHosVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String city;
	private String cityhosrate;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityhosrate() {
		return cityhosrate;
	}
	public void setCityhosrate(String cityhosrate) {
		this.cityhosrate = cityhosrate;
	}
	
	
}
