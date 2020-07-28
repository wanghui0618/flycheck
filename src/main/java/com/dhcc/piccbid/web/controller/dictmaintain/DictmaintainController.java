package com.dhcc.piccbid.web.controller.dictmaintain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-04-11 15:28:39
 * @version V1.0
 */
@Controller
public class DictmaintainController {

	@RequestMapping("/dictmaintain/dictmaintain")
	public String dictmaintain() {
		return "dictmaintain/dictmaintain";
	}
	@RequestMapping("/dictmaintain/dictmaintainform")
	public String dictmaintainform() {
		return "dictmaintain/dictmaintainform";
	}
	
}
