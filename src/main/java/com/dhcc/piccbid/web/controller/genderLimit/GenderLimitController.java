package com.dhcc.piccbid.web.controller.genderLimit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2020-01-03 10:32:21
 * @version V1.0
 */
@Controller
public class GenderLimitController {

	@RequestMapping("/genderLimit/genderLimit")
	public String genderLimit() {
		return "genderLimit/genderLimit";
	}

	@RequestMapping("/genderLimit/detail")
	public String detaileTable() {
		return "genderLimit/detail";
	}


}
