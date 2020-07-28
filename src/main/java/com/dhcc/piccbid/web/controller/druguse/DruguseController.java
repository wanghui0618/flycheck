package com.dhcc.piccbid.web.controller.druguse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-05-29 15:03:28
 * @version V1.0
 */
@Controller
public class DruguseController {

	@RequestMapping("/druguse/druguse")
	public String druguse() {
		return "druguse/druguse";
	}
	
	@RequestMapping("/druguse/druguseinfo")
	public String druguseinfo() {
		return "druguse/druguseinfo";
	}
	
	@RequestMapping("/druguse/druguseadd")
	public String druguseadd() {
		return "druguse/druguseadd";
	}
	
	@RequestMapping("/druguse/druguseedit")
	public String druguseedit() {
		return "druguse/druguseedit";
	}
	
	@RequestMapping("/druguse/druguseview")
	public String druguseview() {
		return "druguse/druguseview";
	}

}
