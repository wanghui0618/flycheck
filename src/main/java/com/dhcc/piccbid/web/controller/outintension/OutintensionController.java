package com.dhcc.piccbid.web.controller.outintension;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-12 10:32:16
 * @version V1.0
 */
@Controller
public class OutintensionController {

	@RequestMapping("/outintension/outintension")
	public String outintension() {
		return "outintension/outintension";
	}
	
	@RequestMapping("/outintension/outintensioninfo")
	public String outintensioninfo() {
		return "outintension/outintensioninfo";
	}
	
	@RequestMapping("/outintension/outintensionform")
	public String outintensionform() {
		return "outintension/outintensionform";
	}

}
