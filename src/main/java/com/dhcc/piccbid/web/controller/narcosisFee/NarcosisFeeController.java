package com.dhcc.piccbid.web.controller.narcosisFee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-10-11 17:43:06
 * @version V1.0
 */
@Controller
public class NarcosisFeeController {

	@RequestMapping("/matchCheck/narcosisFee")
	public String narcosisFee() {
		return "matchCheck/narcosisFee";
	}
	
	@RequestMapping("/matchCheck/narcosisFeeInfo")
	public String narcosisFeeInfo() {
		return "matchCheck/narcosisFeeInfo";
	}

}
