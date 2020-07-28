package com.dhcc.piccbid.web.controller.button;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-07-10 09:17:54
 * @version V1.0
 */
@Controller
public class ButtonController {

	@RequestMapping("/button/button")
	public String button() {
		return "button/button";
	}

	@RequestMapping("/button/buttonAdd")
	public String buttonAdd() {
		return "button/buttonAdd";
	}

}
