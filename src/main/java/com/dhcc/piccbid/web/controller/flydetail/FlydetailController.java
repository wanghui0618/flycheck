package com.dhcc.piccbid.web.controller.flydetail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 14:19:36
 * @version V1.0
 */
@Controller
public class FlydetailController {

	@RequestMapping("/flydetail/flydetail")
	public String flydetail() {
		return "flydetail/flydetail";
	}
	
	@RequestMapping("/flydetail/flydetailinfo")
	public String flydetailinfo() {
		return "flydetail/flydetailinfo";
	}

}
