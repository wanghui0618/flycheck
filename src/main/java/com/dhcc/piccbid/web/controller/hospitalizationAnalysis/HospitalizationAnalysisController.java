package com.dhcc.piccbid.web.controller.hospitalizationAnalysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-10-17 12:54:11
 * @version V1.0
 */
@Controller
public class HospitalizationAnalysisController {

	@RequestMapping("/hospitalizationAnalysis/hospitalizationAnalysis")
	public String hospitalizationAnalysis() {
		return "hospitalizationAnalysis/hospitalizationAnalysis";
	}

}
