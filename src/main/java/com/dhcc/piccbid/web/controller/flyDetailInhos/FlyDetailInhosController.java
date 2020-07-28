package com.dhcc.piccbid.web.controller.flyDetailInhos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-29 16:37:25
 * @version V1.0
 */
@Controller
public class FlyDetailInhosController {

	@RequestMapping("/flyDetailInhos/flyDetailInhos")
	public String flyDetailInhos() {
		return "flyDetailInhos/flyDetailInhos";
	}

	@RequestMapping("/flyDetailInhos/statisticsDrugs")
	public String statisticsDrugs() {
		return "flyDetailInhos/statisticsDrugs";
	}

}
