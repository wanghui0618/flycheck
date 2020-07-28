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
public class CostAnalysis {
	
	@RequestMapping("/indicator/costAnalysis")
	public String icd() {
		return "/indicator/costAnalysis";
	}
	
	@RequestMapping("/indicator/costAnalysis-zy")
	public String icdzy() {
		return "/indicator/costAnalysis-zy2";
	}

}
