package com.dhcc.piccbid.web.controller.unreasonableAdmission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zhouwei
 * @date 2019-11-23 15:20:20
 * @version V1.0
 */
@Controller
public class UnreasonableAdmissionController {

	@RequestMapping("/unreasonableAdmission/unreasonableAdmission")
	public String unreasonableAdmission() {
		return "unreasonableAdmission/unreasonableAdmission";
	}
	@RequestMapping("/unreasonableAdmission/caseInfo_form")
	public  String caseInfoForm(){
		return "unreasonableAdmission/caseInfo_form";
	}
}
