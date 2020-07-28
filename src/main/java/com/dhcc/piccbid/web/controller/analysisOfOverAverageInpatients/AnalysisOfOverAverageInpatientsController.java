package com.dhcc.piccbid.web.controller.analysisOfOverAverageInpatients;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
public class AnalysisOfOverAverageInpatientsController {

	@RequestMapping("/analysisOfOverAverageInpatients/analysisOfOverAverageInpatients")
	public String emptyHangingBedAnalysis() {
		return "analysisOfOverAverageInpatients/analysisOfOverAverageInpatients";
	}
	@RequestMapping("/twoYearsIsSortedByAmount/twoYearsIsSortedByAmount")
	public String twoYearsIsSortedByAmount() {
		return "twoYearsIsSortedByAmount/twoYearsIsSortedByAmount";
	}
	@RequestMapping("/statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems")
	public String statisticsOfDiagnosisAndTreatmentItems() {
		return "statisticsOfDiagnosisAndTreatmentItems/statisticsOfDiagnosisAndTreatmentItems";
	}
}
