package com.dhcc.piccbid.web.controller.outprice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-11 14:57:48
 * @version V1.0
 */
@Controller
public class OutpriceController {

	@RequestMapping("/outprice/outprice")
	public String outprice() {
		return "outprice/outprice";
	}
	
	@RequestMapping("/outprice/outpriceinfo")
	public String outpriceinfo() {
		return "outprice/outpriceinfo";
	}
	
	@RequestMapping("/outprice/outpriceform")
	public String outpriceform() {
		return "outprice/outpriceform";
	}

}
