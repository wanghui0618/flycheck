package com.dhcc.piccbid.dto.flyGeneralOverview;

import java.util.List;

import com.dhcc.piccbid.entity.flycheckMedical.FlyGeneralOverviewVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-11-24 12:40:05
 * @version V1.0
 */
public class FlyGeneralOverviewDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlycheckMedical flyGeneralOverview;
	
	private FlyGeneralOverviewVo flyGeneralOverviewVo;
	
	private List<FlycheckMedical> flyGeneralOverviews;
	
	private List<FlyGeneralOverviewVo> flyGeneralOverviewVos;

	private String param;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public FlycheckMedical getFlyGeneralOverview() {
		return flyGeneralOverview;
	}

	public void setFlyGeneralOverview(FlycheckMedical flyGeneralOverview) {
		this.flyGeneralOverview = flyGeneralOverview;
	}

	public List<FlycheckMedical> getFlyGeneralOverviews() {
		return flyGeneralOverviews;
	}

	public void setFlyGeneralOverviews(List<FlycheckMedical> flyGeneralOverviews) {
		this.flyGeneralOverviews = flyGeneralOverviews;
	}

	/**
	 * @return the flyGeneralOverviewVo
	 */
	public FlyGeneralOverviewVo getFlyGeneralOverviewVo() {
		return flyGeneralOverviewVo;
	}

	/**
	 * @param flyGeneralOverviewVo the flyGeneralOverviewVo to set
	 */
	public void setFlyGeneralOverviewVo(FlyGeneralOverviewVo flyGeneralOverviewVo) {
		this.flyGeneralOverviewVo = flyGeneralOverviewVo;
	}

	/**
	 * @return the flyGeneralOverviewVos
	 */
	public List<FlyGeneralOverviewVo> getFlyGeneralOverviewVos() {
		return flyGeneralOverviewVos;
	}

	/**
	 * @param flyGeneralOverviewVos the flyGeneralOverviewVos to set
	 */
	public void setFlyGeneralOverviewVos(List<FlyGeneralOverviewVo> flyGeneralOverviewVos) {
		this.flyGeneralOverviewVos = flyGeneralOverviewVos;
	}
	
	
}
