package com.dhcc.piccbid.web.controller.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-07-05 11:24:12
 * @version V1.0
 */
@Controller
public class HandleController {

	@RequestMapping("/handle/handle")
	public String handle() {
		return "handle/handle";
	}
	
	@RequestMapping("/handle/handleinfo")
	public String handleinfo() {
		return "handle/handleinfo";
	}
	
	@RequestMapping("/handle/handleadd")
	public String handleadd() {
		return "handle/handleadd";
	}
	
	@RequestMapping("/handle/handleedit")
	public String handleedit() {
		return "handle/handleedit";
	}

}
