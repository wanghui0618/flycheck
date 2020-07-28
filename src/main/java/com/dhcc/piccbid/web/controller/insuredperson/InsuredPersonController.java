package com.dhcc.piccbid.web.controller.insuredperson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-05-23 20:40:43
 * @version V1.0
 */
@Controller
public class InsuredPersonController {

	@RequestMapping("/insuredPerson/insuredPersonInfo")
	public String insuredPersonInfo() {
		return "insuredPerson/insuredPersonInfo";
	}
	
	@RequestMapping("/insuredPerson/insuredPerson")
	public String insuredPerson() {
		return "insuredPerson/insuredPerson";
	}

}
