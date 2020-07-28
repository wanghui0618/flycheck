package com.dhcc.piccbid.web.controller.dataquality;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-07-18 16:45:54
 * @version V1.0
 */
@Controller
public class DataqualityController {

	@RequestMapping("/dataquality/dataquality")
	public String dataquality() {
		return "dataquality/dataquality";
	}

}
