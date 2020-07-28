package com.dhcc.piccbid.web.controller.drugprice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-05-22 20:04:41
 * @version V1.0
 */
@Controller
public class DrugPriceController {

	@RequestMapping("/drugprice/drugPrice")
	public String drugPrice() {
		return "drugprice/drugPrice";
	}
	
	@RequestMapping("/drugprice/drugPriceinfo")
	public String drugPriceinfo() {
		return "/drugprice/drugPriceinfo";
	}
	
	@RequestMapping("/drugprice/drugPriceadd")
	public String drugPriceadd() {
		return "/drugprice/drugPriceadd";
	}
	
	@RequestMapping("/drugprice/drugPriceedit")
	public String drugPriceedit() {
		return "/drugprice/drugPriceedit";
	}

}
