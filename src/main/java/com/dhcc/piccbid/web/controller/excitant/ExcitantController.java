package com.dhcc.piccbid.web.controller.excitant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-05-28 17:03:50
 * @version V1.0
 */
@Controller
public class ExcitantController {

	@RequestMapping("/excitant/excitant")
	public String excitant() {
		return "excitant/excitant";
	}
	
	@RequestMapping("/excitant/excitantinfo")
	public String excitantinfo() {
		return "excitant/excitantinfo";
	}
	
	@RequestMapping("/excitant/excitantadd")
	public String excitantadd() {
		return "excitant/excitantadd";
	}
	
	@RequestMapping("/excitant/excitantedit")
	public String excitantedit() {
		return "excitant/excitantedit";
	}


}
