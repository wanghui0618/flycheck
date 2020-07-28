package com.dhcc.piccbid.dto.flyAvgDay;

import java.util.List;
import com.dhcc.piccbid.entity.flyAvgDay.FlyAvgDay;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wy
 * @date 2019-10-15 14:35:33
 * @version V1.0
 */
public class FlyAvgDayDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyAvgDay flyAvgDay;
	private List<FlyAvgDay> flyAvgDays;
	private String inFlag;
	private String orgName;
	public FlyAvgDay getFlyAvgDay() {
		return flyAvgDay;
	}

	public void setFlyAvgDay(FlyAvgDay flyAvgDay) {
		this.flyAvgDay = flyAvgDay;
	}

	public List<FlyAvgDay> getFlyAvgDays() {
		return flyAvgDays;
	}

	public void setFlyAvgDays(List<FlyAvgDay> flyAvgDays) {
		this.flyAvgDays = flyAvgDays;
	}

	/**  
	* @return inFlag 
	*/
	public String getInFlag() {
		return inFlag;
	}

	/**  
	* @param inFlag inFlag 
	*/
	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	/**  
	* @return orgName 
	*/
	public String getOrgName() {
		return orgName;
	}

	/**  
	* @param orgName orgName 
	*/
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
