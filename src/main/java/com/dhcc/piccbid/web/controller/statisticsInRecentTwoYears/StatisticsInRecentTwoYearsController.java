package com.dhcc.piccbid.web.controller.statisticsInRecentTwoYears;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-08 12:08:41
 * @version V1.0
 */
@Controller
public class StatisticsInRecentTwoYearsController {

	@RequestMapping("/fly/statisticsInRecentTwoYears/statisticsInRecentTwoYears")
	public String twoYearsIsSortedByAmount() {
		return "/fly/statisticsInRecentTwoYears/statisticsInRecentTwoYears";
	}

	@RequestMapping("/fly/analysisOfOverAverageInpatients/analysisOfOverAverageInpatients")
	public String analysisOfOverAverageInpatients() {
		return "/fly/analysisOfOverAverageInpatients/analysisOfOverAverageInpatients";
	}

	@RequestMapping("/fly/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics")
	public String diseaseAndDiagnosticStatistics() {
		return "/fly/diseaseAndDiagnosticStatistics/diseaseAndDiagnosticStatistics";
	}

	@RequestMapping("/fly/statisticalAnalysisByInsuranceType/statisticalAnalysisByInsuranceType")
	public String statisticalAnalysisByInsuranceType() {
		return "/fly/statisticalAnalysisByInsuranceType/statisticalAnalysisByInsuranceType";
	}

}
