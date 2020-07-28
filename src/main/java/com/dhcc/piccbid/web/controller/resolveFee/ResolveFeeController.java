package com.dhcc.piccbid.web.controller.resolveFee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangyue
 * @date 2019-10-12 10:23:52
 * @version V1.0
 */
@Controller
public class ResolveFeeController {

	@RequestMapping("/resolveFee/resolveFee")
	public String resolveFee() {
		return "resolveFee/resolveFee";
	}
	
	@RequestMapping("/resolveFee/resolveFeeAdd")
	public String resolveFeeAdd() {
		return "resolveFee/resolveFeeAdd";
	}

}
