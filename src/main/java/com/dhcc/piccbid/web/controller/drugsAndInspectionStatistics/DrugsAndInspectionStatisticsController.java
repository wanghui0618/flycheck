package com.dhcc.piccbid.web.controller.drugsAndInspectionStatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-15 16:48:40
 * @version V1.0
 */
@Controller
public class DrugsAndInspectionStatisticsController {

	@RequestMapping("/drugsAndInspectionStatistics/drugsAndInspectionStatistics")
	public String drugsAndInspectionStatistics() {
		return "drugsAndInspectionStatistics/drugsAndInspectionStatistics";
	}
	@RequestMapping("/drugsAndInspectionStatistics/drugsAndInspectionStatisticsForInsection")
	public String drugsAndInspectionStatisticsForInsection() {
		return "drugsAndInspectionStatistics/drugsAndInspectionStatisticsForInsection";
	}
	@RequestMapping("/drugsAndInspectionStatistics/drugsAndInspectionStatisticsCostDetail")
	public String drugsAndInspectionStatisticsCostDetail() {
		return "drugsAndInspectionStatistics/drugsAndInspectionStatisticsCostDetail";
	}
	@RequestMapping("/drugsAndInspectionStatistics/drugsAndInspectionStatisticsForInselectionCostDetail")
	public String drugsAndInspectionStatisticsForInselectionCostDetail() {
		return "drugsAndInspectionStatistics/drugsAndInspectionStatisticsForInselectionCostDetail";
	}

}
