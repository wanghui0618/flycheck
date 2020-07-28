/**
 * 
 */
package com.dhcc.piccbid.entity.home;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.type.StringNVarcharType;

/**
 * @author xtl
 *
 */
public class HomeRateVo implements Serializable {
	private static final long serialVersionUID = 1L;	
	private List<HomeHosVo> homeHosVos;
	private List<HomePerVo> homePerVos;
	public List<HomeHosVo> getHomeHosVos() {
		return homeHosVos;
	}
	public void setHomeHosVos(List<HomeHosVo> homeHosVos) {
		this.homeHosVos = homeHosVos;
	}
	public List<HomePerVo> getHomePerVos() {
		return homePerVos;
	}
	public void setHomePerVos(List<HomePerVo> homePerVos) {
		this.homePerVos = homePerVos;
	}
	public List<CityHomeVo> getCityHomeVos() {
		return cityHomeVos;
	}
	public void setCityHomeVos(List<CityHomeVo> cityHomeVos) {
		this.cityHomeVos = cityHomeVos;
	}
	private List<CityHomeVo> cityHomeVos;

}