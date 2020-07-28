package com.dhcc.piccbid.web.controller.code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-07-10 16:09:03
 * @version V1.0
 */
@Controller
public class CodeController {

	@RequestMapping("/code/code")
	public String code() {
		return "code/code";
	}
	
	@RequestMapping("/code/codeAdd")
	public String codeAdd() {
		return "code/codeAdd";
	}
	@RequestMapping("/code/codeUpdate")
	public String codeUpdate() {
		return "code/codeUpdate";
	}

}
