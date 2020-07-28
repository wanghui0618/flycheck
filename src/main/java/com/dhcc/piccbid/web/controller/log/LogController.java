package com.dhcc.piccbid.web.controller.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {

	@RequestMapping("/log/log")
	public String menu() {
		return "/log/log";
	}
	
}
