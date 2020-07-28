package com.dhcc.piccbid.web.controller.fertility;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cy
 * @date 2019-01-28 15:11:12
 * @version V1.0
 */
@Controller
public class FertilityController {

	@RequestMapping("/fertility/fertility")
	public String fertility() {
		 return "cy_check/fertility";
	}
	@RequestMapping("/fertility/fertilityInfo")
	public String fertility1() {
		 return "cy_check/fertilityinfo";
	}
}