package com.dhcc.piccbid.web.controller.itemError;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-28 09:13:42
 * @version V1.0
 */
@Controller
public class ItemErrorController {

	@RequestMapping("/itemError/itemError")
	public String itemError() {
		return "itemError/itemError";
	}

}
