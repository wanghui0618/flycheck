package com.dhcc.piccbid.web.controller.outMatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-10-11 17:42:18
 * @version V1.0
 */
@Controller
public class OutMatchController {

	@RequestMapping("/matchCheck/outMatch")
	public String outMatch() {
		return "matchCheck/outMatch";
	}

	@RequestMapping("/matchCheck/outMatchInfo")
	public String outMatchInfo() {
		return "matchCheck/outMatchInfo";
	}
}
