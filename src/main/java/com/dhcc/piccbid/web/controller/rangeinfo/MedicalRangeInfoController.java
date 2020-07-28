/**
 * 
 */
package com.dhcc.piccbid.web.controller.rangeinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xtl
 *
 */
@Controller
public class MedicalRangeInfoController {
	@RequestMapping("/rangeinfo/rangeinfo")
	public String rangeinfo(){
		return "/rangeinfo/rangeinfo";
	}
	
	@RequestMapping("/rangeinfoinfo/rangeinfoinfo")
	public String rangeinfoinfo(){
		return "/rangeinfo/rangeinfoinfo";
	}
	@RequestMapping("/rangeinfoinfo/rangeinfoinfo-view")
	public String rangeinfoinfoView(){
		return "/rangeinfo/rangeinfoinfo-view";
	}
}
