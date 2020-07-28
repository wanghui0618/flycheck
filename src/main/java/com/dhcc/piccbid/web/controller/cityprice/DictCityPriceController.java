package com.dhcc.piccbid.web.controller.cityprice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-04-15 18:28:39
 * @version V1.0
 */
@Controller
public class DictCityPriceController {

	@RequestMapping("/cityprice/cityprice")
	public String dictCityPrice() {
		return "cityprice/cityprice";
	}
	@RequestMapping("/cityprice/citypriceinfo")
	public String dictCityPriceInfo() {
		return "cityprice/citypriceinfo";
	}

}
