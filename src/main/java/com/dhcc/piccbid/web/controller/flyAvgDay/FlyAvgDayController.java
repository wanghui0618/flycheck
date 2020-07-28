package com.dhcc.piccbid.web.controller.flyAvgDay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wy
 * @date 2019-10-15 14:35:33
 * @version V1.0
 */
@Controller
public class FlyAvgDayController {

	@RequestMapping("/flyAvgDay/flyAvgDay")
	public String flyAvgDay() {
		return "flyAvgDay/flyAvgDay";
	}

}
