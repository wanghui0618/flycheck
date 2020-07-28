package com.dhcc.piccbid.web.controller.consumablerule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-20 16:52:21
 * @version V1.0
 */
@Controller
public class ConsumableRuleController {

	@RequestMapping("/rulesManager/consumableRule")
	public String consumableRule() {
		return "rulesManager/consumableRule";
	}
	
	@RequestMapping("/consumablerule/consumableRuleInfo")
	public String consumableRuleInfo() {
		return "rulesManager/consumableRuleInfo";
	}
	
	@RequestMapping("/consumablerule/consumableInfo")
	public String consumableInfo() {
		return "rulesManager/consumableInfo";
	}

}
