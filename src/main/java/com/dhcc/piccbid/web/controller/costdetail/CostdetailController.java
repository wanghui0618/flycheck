package com.dhcc.piccbid.web.controller.costdetail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-18 16:23:28
 * @version V1.0
 */
@Controller
public class CostdetailController {

	@RequestMapping("/costdetail/costdetail")
	public String costdetail() {
		return "costdetail/costdetail";
	}

	@RequestMapping("/drugcheck/drugcheck")
	public String drugcheck() {
		return "gzw_check/drugcheck";
	}
	
	@RequestMapping("/drugcheck/sexcheck")
	public String sexcheck() {
		return "gzw_check/sexcheck";
	}
	
	@RequestMapping("/drugcheck/drugcheckinfo")
	public String drugcheckinfo() {
		return "gzw_check/drugcheckinfo";
	}
	
	@RequestMapping("/drugcheck/sexcheckinfo")
	public String sexcheckinfo() {
		return "gzw_check/sexcheckinfo";
	}
	
	@RequestMapping("/drugcheck/jinji")
	public String jinji() {
		return "gzw_check/jinji";
	}
	
	@RequestMapping("/drugcheck/jinjiinfo")
	public String jinjiinfo() {
		return "gzw_check/jinjiinfo";
	}
}
