package com.dhcc.piccbid.web.controller.excessiveTreat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 10:11:17
 * @version V1.0
 */
@Controller
public class ExcessiveTreatController {

	@RequestMapping("/excessiveTreat/excessiveTreat")
	public String excessiveTreat() {
		return "excessiveTreat/excessiveTreat";
	}
	
	@RequestMapping("/excessiveTreat/excessiveTreatAdd")
	public String indicationTreatAdd() {
		return "excessiveTreat/excessiveTreatAdd";
	}
}
