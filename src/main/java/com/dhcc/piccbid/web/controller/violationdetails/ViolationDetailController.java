package com.dhcc.piccbid.web.controller.violationdetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-06-27 11:50:28
 * @version V1.0
 */
@Controller
public class ViolationDetailController {

	@RequestMapping("/violationdetails/violationDetail")
	public String violationDetail() {
		return "violationdetails/violationDetail";
	}
	@RequestMapping("/violationDetail/violationDetail")
	public String violationDetailIndex() {
		return "violationDetail/violationDetail";
	}
	@RequestMapping("/violationdetail/violationDetail1")
	public String violationDetail1() {
		return "violationDetail/violationDetail1";
	}
	@RequestMapping("/violationdetail/violationDetail2")
	public String violationDetail2() {
		return "violationDetail/violationDetail2";
	}
	
}
