package com.dhcc.piccbid.web.controller.outhospday;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-11 11:29:13
 * @version V1.0
 */
@Controller
public class OuthospdayController {

	@RequestMapping("/outhospday/outhospday")
	public String outhospday() {
		return "outhospday/outhospday";
	}

	@RequestMapping("/outhospday/outhospdayAdd")
	public String outhospdayAdd() {
		return "outhospday/outhospdayAdd";
	}

}
