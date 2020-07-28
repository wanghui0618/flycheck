package com.dhcc.piccbid.service.diseaseAndDiagnosticStatistics;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatisticsDto;
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
import com.dhcc.piccbid.entity.diseaseAndDiagnosticStatistics.DiseaseAndDiagnosticStatistics;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author scy
 * @date 2019-10-16 17:08:09
 * @version V1.0
 */
public interface DiseaseAndDiagnosticStatisticsService extends BaseService<DiseaseAndDiagnosticStatistics, String> {

	PageModel list(DiseaseAndDiagnosticStatisticsDto dto);

	PageModel getlist(DiseaseAndDiagnosticStatisticsDto dto);

	PageModel getlistByinhosDiag(DiseaseAndDiagnosticStatisticsDto dto);

	PageModel getlistByinhosDate(DiseaseAndDiagnosticStatisticsDto dto);

	PageModel statisticalAnalysisByInsuranceType(DiseaseAndDiagnosticStatisticsDto dto);

}
