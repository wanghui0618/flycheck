package com.dhcc.piccbid.web.controller.pharmacy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-05-28 17:31:36
 * @version V1.0
 */
@Controller
public class PharmacyController {

	@RequestMapping("/pharmacy/pharmacy")
	public String pharmacy() {
		return "pharmacy/pharmacy";
	}
	
	@RequestMapping("/pharmacy/pharmacyinfo")
	public String pharmacyinfo() {
		return "pharmacy/pharmacyinfo";
	}
	
	@RequestMapping("/pharmacy/pharmacyadd")
	public String pharmacyinfoadd() {
		return "pharmacy/pharmacyadd";
	}
	
	@RequestMapping("/pharmacy/pharmacyedit")
	public String pharmacyedit() {
		return "pharmacy/pharmacyedit";
	}

}
