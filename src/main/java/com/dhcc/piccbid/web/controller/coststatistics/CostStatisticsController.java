package com.dhcc.piccbid.web.controller.coststatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ZWY
 * @date 2019-05-27 17:23:12
 * @version V1.0
 */
@Controller
public class CostStatisticsController {

	@RequestMapping("/costStatistics/userCostStatistics")
	public String userCostStatistics() {
		return "costStatistics/userCostStatistics";
	}
	
	@RequestMapping("/costStatistics/hospitalCostStatistics")
	public String hospitalCostStatistics() {
		return "costStatistics/hospitalCostStatistics";
	}
	
	@RequestMapping("/costStatistics/diseasesCostStatistics")
	public String diseasesCostStatistics() {
		return "costStatistics/diseasesCostStatistics";
	}
	@RequestMapping("/costStatistics/userManage")
	public String userManage() {
		return "costStatistics/userManage";
	}
	@RequestMapping("/costStatistics/userManageForm")
	public String userManageForm() {
		return "costStatistics/userManageForm";
	}
}
