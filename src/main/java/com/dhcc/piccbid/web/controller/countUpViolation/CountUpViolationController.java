package com.dhcc.piccbid.web.controller.countUpViolation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-09-29 15:20:39
 * @version V1.0
 */
@Controller
public class CountUpViolationController {

	@RequestMapping("/countUpViolation/countUpViolation")
	public String countUpViolation() {
		return "countUpViolation/countUpViolation";
	}

}
