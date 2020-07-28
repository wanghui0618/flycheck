package com.dhcc.piccbid.web.controller.selfpayratio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-05-23 14:53:43
 * @version V1.0
 */
@Controller
public class SelfPayRatioController {

	@RequestMapping("/selfPayRatio/selfPayRatio")
	public String selfPayRatio() {
		return "selfPayRatio/selfPayRatio";
	}
	@RequestMapping("/selfPayRatio/selfPayRatioInfo")
	public String selfPayRatioInfo() {
		return "selfPayRatio/selfPayRatioinfo";
	}

}
