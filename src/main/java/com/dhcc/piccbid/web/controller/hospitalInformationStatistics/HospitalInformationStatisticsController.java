package com.dhcc.piccbid.web.controller.hospitalInformationStatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HospitalInformationStatisticsController {

	@RequestMapping("/hospitalInformationStatistics/hospitalInformationStatistics")
	public String hospitalInformationStatistics() {
		return "hospitalInformationStatistics/hospitalInformationStatistics";
	}
	
}
