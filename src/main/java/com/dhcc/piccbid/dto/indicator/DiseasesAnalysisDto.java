package com.dhcc.piccbid.dto.indicator;

import java.util.List;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-22 12:22:11
 * @version V1.0
 */
public class DiseasesAnalysisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private DiseasesAnalysis diseasesAnalysis;
	private List<DiseasesAnalysis> diseasesAnalysiss;

	public DiseasesAnalysis getDiseasesAnalysis() {
		return diseasesAnalysis;
	}

	public void setDiseasesAnalysis(DiseasesAnalysis diseasesAnalysis) {
		this.diseasesAnalysis = diseasesAnalysis;
	}

	public List<DiseasesAnalysis> getDiseasesAnalysiss() {
		return diseasesAnalysiss;
	}

	public void setDiseasesAnalysiss(List<DiseasesAnalysis> diseasesAnalysiss) {
		this.diseasesAnalysiss = diseasesAnalysiss;
	}
}
