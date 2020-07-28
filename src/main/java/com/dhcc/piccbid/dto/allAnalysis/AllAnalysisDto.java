package com.dhcc.piccbid.dto.allAnalysis;

import java.util.List;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis2;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-08-25 16:54:00
 * @version V1.0
 */
public class AllAnalysisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private AllAnalysis allAnalysis;
	private AllAnalysis2 allAnalysis2;
	private List<AllAnalysis> allAnalysiss;
	private List<AllAnalysis2> allAnalysiss2;

	/**
	 * @return the allAnalysis2
	 */
	public AllAnalysis2 getAllAnalysis2() {
		return allAnalysis2;
	}

	/**
	 * @param allAnalysis2 the allAnalysis2 to set
	 */
	public void setAllAnalysis2(AllAnalysis2 allAnalysis2) {
		this.allAnalysis2 = allAnalysis2;
	}

	/**
	 * @return the allAnalysiss2
	 */
	public List<AllAnalysis2> getAllAnalysiss2() {
		return allAnalysiss2;
	}

	/**
	 * @param allAnalysiss2 the allAnalysiss2 to set
	 */
	public void setAllAnalysiss2(List<AllAnalysis2> allAnalysiss2) {
		this.allAnalysiss2 = allAnalysiss2;
	}

	public AllAnalysis getAllAnalysis() {
		return allAnalysis;
	}

	public void setAllAnalysis(AllAnalysis allAnalysis) {
		this.allAnalysis = allAnalysis;
	}

	public List<AllAnalysis> getAllAnalysiss() {
		return allAnalysiss;
	}

	public void setAllAnalysiss(List<AllAnalysis> allAnalysiss) {
		this.allAnalysiss = allAnalysiss;
	}
}
