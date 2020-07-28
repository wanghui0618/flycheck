package com.dhcc.piccbid.web.controller.outtreatment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-11 11:06:28
 * @version V1.0
 */
@Controller
public class OuttreatmentController {

	@RequestMapping("/outtreatment/outtreatment")
	public String outtreatment() {
		return "outtreatment/outtreatment";
	}

	@RequestMapping("/outtreatment/outtreatmentAdd")
	public String outtreatmentAdd() {
		return "outtreatment/outtreatmentAdd";
	}

}
