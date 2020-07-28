package com.dhcc.piccbid.web.controller.childrensDrugs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2019-11-23 13:32:56
 * @version V1.0
 */
@Controller
public class ChildrensDrugsController {

	@RequestMapping("/childrensDrugs/childrensDrugs")
	public String childrensDrugs() {
		return "childrensDrugs/childrensDrugs";
	}


	@RequestMapping("/childrensDrugs/detail")
	public String detaileTable() {
		return "childrensDrugs/detail";
	}

}
