package com.dhcc.piccbid.web.controller.coststatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-06 11:42:09
 * @version V1.0
 */
@Controller
public class HospitalCostStatisticsController {

	@RequestMapping("/coststatistics/hospitalCostStatistics")
	public String hospitalCostStatistics() {
		return "coststatistics/hospitalCostStatistics";
	}

}
