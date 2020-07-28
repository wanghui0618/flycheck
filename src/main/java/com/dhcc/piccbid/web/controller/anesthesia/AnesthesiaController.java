package com.dhcc.piccbid.web.controller.anesthesia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-24 09:57:03
 * @version V1.0
 */
@Controller
public class AnesthesiaController {

	@RequestMapping("/anesthesia/anesthesia")
	public String anesthesia() {
		return "anesthesia/anesthesia";
	}

	

}
