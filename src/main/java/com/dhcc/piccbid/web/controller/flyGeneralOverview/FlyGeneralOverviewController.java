package com.dhcc.piccbid.web.controller.flyGeneralOverview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-11-24 12:40:05
 * @version V1.0
 */
@Controller
public class FlyGeneralOverviewController {

	@RequestMapping("/flyGeneralOverview/flyGeneralOverview")
	public String flyGeneralOverview() {
		return "flyGeneralOverview/flyGeneralOverview";
	}

}
