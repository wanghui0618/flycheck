package com.dhcc.piccbid.web.controller.treatmentrule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-20 16:51:36
 * @version V1.0
 */
@Controller
public class TreatmentRuleController {

	@RequestMapping("/rulesManager/treatmentRule")
	public String treatmentRule() {
		return "rulesManager/treatmentRule";
	}
	
	@RequestMapping("/treatmentrule/treatmentRuleInfo")
	public String treatmentRuleInfo() {
		return "rulesManager/treatmentRuleInfo";
	}
	
	@RequestMapping("/treatmentrule/treatmentInfo")
	public String treatmentInfo() {
		return "rulesManager/treatmentInfo";
	}
	
	@RequestMapping("/rulesManager/dataQuality")
	public String dataQuality() {
		return "rulesManager/dataQuality";
	}
	
	@RequestMapping("/rulesManager/dataIntegrity")
	public String dataIntegrity() {
		return "rulesManager/dataIntegrity";
	}

}
