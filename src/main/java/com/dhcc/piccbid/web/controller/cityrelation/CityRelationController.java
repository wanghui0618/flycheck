package com.dhcc.piccbid.web.controller.cityrelation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-04-23 10:57:40
 * @version V1.0
 */
@Controller
public class CityRelationController {

	@RequestMapping("/cityrelation/cityRelation")
	public String cityRelation() {
		return "cityrelation/cityRelation";
	}

}
