package com.dhcc.piccbid.web.controller.druglnstmction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ZWY
 * @date 2019-05-21 11:40:13
 * @version V1.0
 */
@Controller
public class DrugLnstmctionController {

	
	@RequestMapping("/druglnstmction/druglnstmctioninfo")
	public String drugLnstmctioninfo() {
		return "druglnstmction/druglnstmctioninfo";
	}
	@RequestMapping("/druglnstmction/druglnstmction")
	public String drugLnstmction() {
		return "/druglnstmction/druglnstmction";
	}
	
	@RequestMapping("/druglnstmction/drugview")
	public String druguseview() {
		return "druglnstmction/drugview";
	}
	
}
