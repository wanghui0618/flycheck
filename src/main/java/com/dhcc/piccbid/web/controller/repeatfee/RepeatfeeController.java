package com.dhcc.piccbid.web.controller.repeatfee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 14:34:49
 * @version V1.0
 */
@Controller
public class RepeatfeeController {

	@RequestMapping("/repeatfee/repeatfee")
	public String repeatfee() {
		return "repeatfee/repeatfee";
	}
	
	@RequestMapping("/repeatfee/repeatfeeAdd")
	public String indicationTreatAdd() {
		return "repeatfee/repeatfeeAdd";
	}
}
