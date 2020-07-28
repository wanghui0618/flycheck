package com.dhcc.piccbid.web.controller.analysisOfPersonTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-23 15:00:28
 * @version V1.0
 */
@Controller
public class AnalysisOfPersonTimeController {

	@RequestMapping("/analysisOfPersonTime/analysisOfPersonTime")
	public String analysisOfPersonTime() {
		return "analysisOfPersonTime/analysisOfPersonTime";
	}

}
