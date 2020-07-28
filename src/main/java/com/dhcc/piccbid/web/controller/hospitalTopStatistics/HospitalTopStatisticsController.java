package com.dhcc.piccbid.web.controller.hospitalTopStatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-09-28 18:04:09
 * @version V1.0
 */
@Controller
public class HospitalTopStatisticsController {

	@RequestMapping("/hospitalTopStatistics/hospitalTopStatistics")
	public String hospitalTopStatistics() {
		return "hospitalTopStatistics/hospitalTopStatistics";
	}

}
