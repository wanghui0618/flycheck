/**
 * 
 */
package com.dhcc.piccbid.web.controller.indicator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
public class DiseasesAnalysis {
	
	@RequestMapping("/indicator/diseasesAnalysis")
	public String icd() {
		return "/indicator/diseasesAnalysis";
	}

	@RequestMapping("/indicator/diseasesAnalysisTest")
	public String diseasesAnalysisTest() {
		return "/indicator/diseasesAnalysisTest";
	}

}
