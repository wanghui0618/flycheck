/**
 * 
 */
package com.dhcc.piccbid.entity.home;

import java.io.Serializable;

/**
 * @author xtl
 *
 */
public class CityHomeVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cityname;
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
}
