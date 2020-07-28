package com.dhcc.piccbid.web.controller.narcosis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-14 14:35:29
 * @version V1.0
 */
@Controller
public class NarcosisController {

	@RequestMapping("/narcosis/narcosis")
	public String narcosis() {
		return "narcosis/narcosis";
	}
	
	@RequestMapping("/narcosis/narcosisAdd")
	public String indicationTreatAdd() {
		return "narcosis/narcosisAdd";
	}
}
