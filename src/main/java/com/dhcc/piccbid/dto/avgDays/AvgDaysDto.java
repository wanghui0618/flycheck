package com.dhcc.piccbid.dto.avgDays;

import java.util.List;
import com.dhcc.piccbid.entity.avgDays.AvgDays;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-07-31 10:28:01
 * @version V1.0
 */
public class AvgDaysDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private AvgDays avgDays;
	private List<AvgDays> avgDayss;

	public AvgDays getAvgDays() {
		return avgDays;
	}

	public void setAvgDays(AvgDays avgDays) {
		this.avgDays = avgDays;
	}

	public List<AvgDays> getAvgDayss() {
		return avgDayss;
	}

	public void setAvgDayss(List<AvgDays> avgDayss) {
		this.avgDayss = avgDayss;
	}
}
