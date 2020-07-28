package com.dhcc.piccbid.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-15 16:58:47
 * @version V1.0
 */
@Controller
public class RegionalfundsController {

	@RequestMapping("/home/regionalfunds")
	public String regionalfunds() {
		return "home/regionalfunds";
	}

}
