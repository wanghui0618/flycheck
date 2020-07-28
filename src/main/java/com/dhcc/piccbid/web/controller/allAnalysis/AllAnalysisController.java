package com.dhcc.piccbid.web.controller.allAnalysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-08-25 16:54:00
 * @version V1.0
 */
@Controller
public class AllAnalysisController {

	@RequestMapping("/allAnalysis/allAnalysis")
	public String allAnalysis() {
		return "allAnalysis/allAnalysis";
	}

}
