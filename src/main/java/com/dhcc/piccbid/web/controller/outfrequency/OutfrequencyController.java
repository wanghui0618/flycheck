package com.dhcc.piccbid.web.controller.outfrequency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-11 18:02:25
 * @version V1.0
 */
@Controller
public class OutfrequencyController {

	@RequestMapping("/outfrequency/outfrequency")
	public String outfrequency() {
		return "outfrequency/outfrequency";
	}
	
	@RequestMapping("/outfrequency/outfrequencyinfo")
	public String outfrequencyinfo() {
		return "outfrequency/outfrequencyinfo";
	}
	
	@RequestMapping("/outfrequency/outfrequencyform")
	public String outfrequencyform() {
		return "outfrequency/outfrequencyform";
	}

}
