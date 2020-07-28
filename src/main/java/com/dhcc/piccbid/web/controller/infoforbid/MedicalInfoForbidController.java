/**
 * 
 */
package com.dhcc.piccbid.web.controller.infoforbid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xtl
 *
 */
@Controller
public class MedicalInfoForbidController {
	@RequestMapping("/infoforbid/infoforbid")
	public String rangeinfo(){
		return "/infoforbid/infoforbid";
	}
	
	@RequestMapping("/infoforbidinfo/infoforbidinfo")
	public String rangeinfoinfo(){
		return "/infoforbid/infoforbidinfo";
	}
	
	@RequestMapping("/infoforbidinfo/infoforbidinfoview")
	public String rangeinfoinfoView(){
		return "/infoforbid/infoforbidinfo-view";
	}
}
