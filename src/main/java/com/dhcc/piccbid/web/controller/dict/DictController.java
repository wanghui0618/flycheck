package com.dhcc.piccbid.web.controller.dict;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-15 09:58:13
 * @version V1.0
 */
@Controller
public class DictController {

	@RequestMapping("/dict/dict")
	public String dict() {
		return "dict/dict";
	}

}
