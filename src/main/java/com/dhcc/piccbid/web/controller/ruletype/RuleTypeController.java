package com.dhcc.piccbid.web.controller.ruletype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-04-14 16:49:27
 * @version V1.0
 */
@Controller
public class RuleTypeController {

	@RequestMapping("/ruletype/ruleType")
	public String ruleType() {
		return "ruletype/ruleType";
	}
	
	@RequestMapping("/ruletype/ruleType2")
	public String ruleType2() {
		return "ruletype/ruleType2";
	}
	
	@RequestMapping("/ruletype/ruleTypeform")
	public String ruleTypeform() {
		return "ruletype/ruleTypeform";
	}
	
	@RequestMapping("/ruletype/dynamicRuleType")
	public String dynamicRuleType() {
		return "ruletype/dynamicRuleType";
	}

}
