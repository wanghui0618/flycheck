package com.dhcc.piccbid.web.controller.indicationTreat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wy
 * @date 2019-10-12 15:21:21
 * @version V1.0
 */
@Controller
public class IndicationTreatController {

	@RequestMapping("/indicationTreat/indicationTreat")
	public String indicationTreat() {
		return "indicationTreat/indicationTreat";
	}
	
	@RequestMapping("/indicationTreat/indicationTreatAdd")
	public String indicationTreatAdd() {
		return "indicationTreat/indicationTreatAdd";
	}

}
