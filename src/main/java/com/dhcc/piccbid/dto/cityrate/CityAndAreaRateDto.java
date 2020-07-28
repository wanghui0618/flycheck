package com.dhcc.piccbid.dto.cityrate;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
//import com.dhcc.piccbid.entity.city.City;
import com.dhcc.piccbid.entity.home.HomeHPRateVo;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-04-11 15:54:57
 * @version V1.0
 */
public class CityAndAreaRateDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private List<HomeHPRateVo> homeHPRateVos;
	private HomeHPRateVo homeHPRateVo;
	public List<HomeHPRateVo> getHomeHPRateVos() {
		return homeHPRateVos;
	}

	public void setHomeHPRateVos(List<HomeHPRateVo> homeHPRateVos) {
		this.homeHPRateVos = homeHPRateVos;
	}

	public HomeHPRateVo getHomeHPRateVo() {
		return homeHPRateVo;
	}

	public void setHomeHPRateVo(HomeHPRateVo homeHPRateVo) {
		this.homeHPRateVo = homeHPRateVo;
	}

	
}
