package com.dhcc.piccbid.web.controller.drugstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-05-22 17:59:49
 * @version V1.0
 */
@Controller
public class DrugStoreController {

	@RequestMapping("/drugstore/drugStore")
	public String drugStore() {
		return "drugstore/drugStore";
	}
	
	@RequestMapping("/drugstore/drugStoreinfo")
	public String drugStoreinfo() {
		return "/drugstore/drugStoreinfo";
	}

}
