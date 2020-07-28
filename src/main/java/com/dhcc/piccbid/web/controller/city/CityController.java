package com.dhcc.piccbid.web.controller.city;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-04-11 15:54:57
 * @version V1.0
 */
@Controller
public class CityController {

	@RequestMapping("/city/city")
	public String city() {
		return "city/city";
	}
	
	
	@RequestMapping("/city/cityinfo")
	public String cityinfo() {
		return "city/cityinfo";
	}
	
	@RequestMapping("/city/cityadd")
	public String cityadd() {
		return "city/cityadd";
	}
	
	@RequestMapping("/city/cityedit")
	public String cityedit() {
		return "city/cityedit";
	}
	


}
