package com.dhcc.piccbid.web.controller.treatmentData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-08-26 00:16:20
 * @version V1.0
 */
@Controller
public class TreatmentDataController {

	@RequestMapping("/treatmentData/treatmentData")
	public String treatmentData() {
		return "treatmentData/treatmentData";
	}

}
