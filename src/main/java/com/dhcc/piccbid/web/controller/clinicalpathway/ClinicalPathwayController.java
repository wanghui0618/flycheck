package com.dhcc.piccbid.web.controller.clinicalpathway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-04-12 11:35:14
 * @version V1.0
 */
@Controller
public class ClinicalPathwayController {

	@RequestMapping("/clinicalPathway/clinicalPathway")
	public String clinicalPathway() {
		return "/clinicalpathway/clinicalPathway";
	}
	@RequestMapping("/clinicalPathway/clinicalPathwayInfo")
	public String clinicalPathwayInfo() {
		return "/clinicalpathway/clinicalPathwayInfo";
	}
}
