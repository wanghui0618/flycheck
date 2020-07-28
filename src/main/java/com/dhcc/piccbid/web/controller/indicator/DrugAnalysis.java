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
public class DrugAnalysis {
	
	@RequestMapping("/indicator/drugAnalysis")
	public String icd() {
		return "/indicator/drugAnalysis";
	}

}
