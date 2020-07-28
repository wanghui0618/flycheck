/**
 * 
 */
package com.dhcc.piccbid.web.controller.cityareanumber;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xtl
 *
 */
@Controller
public class CityAreaNumberController {
	
	@RequestMapping("/cityAreaNumber/cityAreaNumber")
	public String cityAreaNumberDetail(){
		return "/cityareanumber/cityareanumber";
	}
	@RequestMapping("/cityAreaRate/cityAreaRate")
	public String cityAreaRateDetail(){
		return "/cityareanumber/cityarearate";
	}
	@RequestMapping("/cityAreaControl/cityAreaControl")
	public String cityAreaControlDetail(){
		return "/cityareanumber/cityareacontrol";
	}
}
