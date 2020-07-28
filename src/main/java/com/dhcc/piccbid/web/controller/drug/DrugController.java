package com.dhcc.piccbid.web.controller.drug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-04-12 18:00:22
 * @version V1.0
 */
@Controller
public class DrugController {

	@RequestMapping("/drug/drug")
	public String drug() {
		return "drug/drug";
	}
	
	@RequestMapping("/drug/druginfo")
	public String cityinfo() {
		return "drug/druginfo";
	}
	
	@RequestMapping("/drug/druginfoshow")
	public String cityinfoshow() {
		return "drug/druginfoshow";
	}

}
