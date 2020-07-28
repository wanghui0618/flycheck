package com.dhcc.piccbid.web.controller.child;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ljl
 * @date 2019-01-23 09:32:14
 * @version V1.0
 */
@Controller
public class ChildController {

	@RequestMapping("/child/child")
	public String child() {
		return "child/child1";
	}
	@RequestMapping("/child/childinfo")
	public String childinfo() {
		return "child/childinfo";
	}
}
