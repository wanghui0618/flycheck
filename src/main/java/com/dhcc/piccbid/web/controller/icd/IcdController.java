package com.dhcc.piccbid.web.controller.icd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-05-14 17:39:01
 * @version V1.0
 */
@Controller
public class IcdController {

	@RequestMapping("/icd/icd")
	public String icd() {
		return "icd/icd";
	}
	
	@RequestMapping("/icd/icdinfo")
	public String icdinfo() {
		return "icd/icdinfo";
	}
	
	@RequestMapping("/icd/icdinfo1")
	public String icdinfo1() {
		return "icd/icdinfo1";
	}
	
	@RequestMapping("/icd/icdinfo1form")
	public String icdinfo1form() {
		return "icd/icdinfo1form";
	}
	
	@RequestMapping("/icd/icdform")
	public String icdform() {
		return "icd/icdform";
	}
}
