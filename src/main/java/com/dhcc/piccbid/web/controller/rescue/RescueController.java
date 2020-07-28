package com.dhcc.piccbid.web.controller.rescue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author LiPeng
 * @date 2019-01-26 13:52:01
 * @version V1.0
 */
@Controller
public class RescueController {

	@RequestMapping("/rescue/rescue")
	public String rescue() {
		return "rescue/rescue";
	}

	@RequestMapping("/rescue/rescuecheckinfo")
	public String drugcheckinfo() {
		return "rescue/rescuecheckinfo";
	}
}
