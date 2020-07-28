package com.dhcc.piccbid.web.controller.avgDays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-07-31 10:28:01
 * @version V1.0
 */
@Controller
public class AvgDaysController {

	@RequestMapping("/avgDays/avgDays")
	public String avgDays() {
		return "avgDays/avgDays";
	}

}
