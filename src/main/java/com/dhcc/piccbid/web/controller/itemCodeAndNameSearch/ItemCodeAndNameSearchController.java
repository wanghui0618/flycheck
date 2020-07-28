package com.dhcc.piccbid.web.controller.itemCodeAndNameSearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author Yangsx
 * @date 2019-12-12 16:18:53
 * @version V1.0
 */
@Controller
public class ItemCodeAndNameSearchController {

	@RequestMapping("/itemCodeAndNameSearch/itemCodeAndNameSearch")
	public String itemCodeAndNameSearch() {
		return "itemCodeAndNameSearch/itemCodeAndNameSearch";
	}

}
