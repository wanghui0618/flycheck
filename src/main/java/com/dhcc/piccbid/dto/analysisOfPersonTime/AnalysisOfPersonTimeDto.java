package com.dhcc.piccbid.dto.analysisOfPersonTime;

import java.util.List;
import com.dhcc.piccbid.entity.analysisOfPersonTime.AnalysisOfPersonTime;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-23 15:00:27
 * @version V1.0
 */
public class AnalysisOfPersonTimeDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private AnalysisOfPersonTime analysisOfPersonTime;
	private List<AnalysisOfPersonTime> analysisOfPersonTimes;

	public AnalysisOfPersonTime getAnalysisOfPersonTime() {
		return analysisOfPersonTime;
	}

	public void setAnalysisOfPersonTime(AnalysisOfPersonTime analysisOfPersonTime) {
		this.analysisOfPersonTime = analysisOfPersonTime;
	}

	public List<AnalysisOfPersonTime> getAnalysisOfPersonTimes() {
		return analysisOfPersonTimes;
	}

	public void setAnalysisOfPersonTimes(List<AnalysisOfPersonTime> analysisOfPersonTimes) {
		this.analysisOfPersonTimes = analysisOfPersonTimes;
	}
}
