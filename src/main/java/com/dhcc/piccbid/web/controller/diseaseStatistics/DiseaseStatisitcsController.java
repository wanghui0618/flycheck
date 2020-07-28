package com.dhcc.piccbid.web.controller.diseaseStatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DiseaseStatisitcsController {

	@RequestMapping("/diseaseStatistics/diseaseStatistics")
	public String diseaseAndDiagnosticStatistics() {
		return "diseaseStatistics/diseaseStatistics";
	}
}
