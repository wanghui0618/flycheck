package com.dhcc.piccbid.web.controller.flyDrug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-16 15:05:04
 * @version V1.0
 */
@Controller
public class FlyDrugController {

	@RequestMapping("/flyDrug/flyDrug")
	public String flyDrug() {
		return "flyDrug/flyDrug";
	}

}
