package com.dhcc.piccbid.web.controller.hospitalviolationdetail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-05-27 17:22:38
 * @version V1.0
 */
@Controller
public class HospitalViolationDetailController {

	@RequestMapping("/hospitalViolationDetail/hospitalViolationDetail")
	public String hospitalViolationDetail() {
		return "hospitalViolationDetail/hospitalViolationDetail";
	}

}
