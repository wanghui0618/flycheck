package com.dhcc.piccbid.service.analysisOfOverAverageInpatients;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatientsDto;
import com.dhcc.piccbid.entity.analysisOfOverAverageInpatients.AnalysisOfOverAverageInpatients;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-24 15:16:38
 * @version V1.0
 */
public interface AnalysisOfOverAverageInpatientsService extends BaseService<AnalysisOfOverAverageInpatients, String> {

	PageModel list(AnalysisOfOverAverageInpatientsDto dto);
	PageModel getCount(AnalysisOfOverAverageInpatientsDto dto);
	PageModel towYears(AnalysisOfOverAverageInpatientsDto dto);
	PageModel countDiagnosisAndTreatmentItems(AnalysisOfOverAverageInpatientsDto dto);

}
