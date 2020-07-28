package com.dhcc.piccbid.web.controller.diseaseAndDiagnosticStatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
public class DiseaseAndDiagnosticStatisticsController {

	@RequestMapping("/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics")
	public String diseaseAndDiagnosticStatistics() {
		return "diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics";
	}
	@RequestMapping("/statisticalAnalysisByInsuranceType/statisticalAnalysisByInsuranceType")
	public String statisticalAnalysisByInsuranceType() {
		return "statisticalAnalysisByInsuranceType/statisticalAnalysisByInsuranceType";
	}

}
