package com.dhcc.piccbid.web.controller.power;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PowerController {
	
	@RequestMapping("/home/power")
	public String power() {
		return "/home/power";
	}
	
	@RequestMapping("/home/powerHome")
	public String powerHome() {
		return "/home/powerHome";
	}

}
