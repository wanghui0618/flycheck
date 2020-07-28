/**
 * 
 */
package com.dhcc.piccbid.web.controller.hospitalizationMonitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
public class HospitalizationMonitor {
	
	@RequestMapping("/hospitalizationMonitor/hospitalizationMonitor")
	public String icd() {
		return "/hospitalizationMonitor/hospitalizationMonitor";
	}

}
