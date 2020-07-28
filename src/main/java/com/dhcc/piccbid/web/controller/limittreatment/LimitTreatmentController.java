package com.dhcc.piccbid.web.controller.limittreatment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tjb
 * @date 2019-10-11 10:35:45
 * @version V1.0
 */
@Controller
public class LimitTreatmentController {

	@RequestMapping("/limitTreatment/limitTreatment")
	public String limitTreatment() {
		return "limittreatment/limitTreatment";
	}

	@RequestMapping("/limittreatment/limittreatmentAdd")
	public String limittreatmentAdd() {
		return "limittreatment/limittreatmentAdd";
	}

}
