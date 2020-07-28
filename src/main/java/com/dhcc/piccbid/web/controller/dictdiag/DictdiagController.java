package com.dhcc.piccbid.web.controller.dictdiag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-30 10:13:57
 * @version V1.0
 */
@Controller
public class DictdiagController {

	@RequestMapping("/dictdiag/dictdiag")
	public String dictdiag() {
		return "dictdiag/dictdiag";
	}
	
	@RequestMapping("/dictdiag/dictdiagAdd")
	public String dictdiagAdd() {
		return "dictdiag/dictdiagAdd";
	}
}
