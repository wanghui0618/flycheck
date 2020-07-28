/**
 * 
 */
package com.dhcc.piccbid.entity.home;

import java.io.Serializable;

/**
 * @author xtl
 *
 */
public class HomePerVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String city;
	private String citypersonrate;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCitypersonrate() {
		return citypersonrate;
	}
	public void setCitypersonrate(String citypersonrate) {
		this.citypersonrate = citypersonrate;
	}
	
	
}
