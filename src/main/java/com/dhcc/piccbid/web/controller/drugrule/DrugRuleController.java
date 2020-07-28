package com.dhcc.piccbid.web.controller.drugrule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-15 16:04:53
 * @version V1.0
 */
@Controller
public class DrugRuleController {

	@RequestMapping("/rulesManager/drugRule")
	public String drugRule() {
		return "rulesManager/drugRule";
	}
	@RequestMapping("/drugrule/drugRuleInfo")
	public String drugRuleInfo() {
		return "rulesManager/drugRuleInfo";
	}
	
	@RequestMapping("/drugrule/drugInfo")
	public String drugInfo() {
		return "rulesManager/drugInfo";
	}

}
