/**
 * 
 */
package com.dhcc.piccbid.web.controller.insureunit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xtl
 *
 */
@Controller
public class InsureUnitController {
	@RequestMapping("/insureunit/insureunit")
	public String rangeinfo(){
		return "/insureunit/insureunit";
	}
	
	@RequestMapping("/insureunitinfo/insureunitinfo")
	public String rangeinfoinfo(){
		return "/insureunit/insureunitinfo";
	}
	@RequestMapping("/insureunitinfo/insureunitinfo-view")
	public String rangeinfoinfoView(){
		return "/insureunit/insureunitinfo-view";
	}
}
