package com.dhcc.piccbid.web.controller.medicalreport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MedicalReportController {
	
	@RequestMapping("/medicalreport/hospitalizationanalysis")
	public String hospitalizationanalysis() {
		return "/medicalreport/hospitalizationanalysis";
	}
	
	@RequestMapping("/medicalreport/outpatientanalysis")
	public String outpatientanalysis() {
		return "/medicalreport/outpatientanalysis";
	}

}
