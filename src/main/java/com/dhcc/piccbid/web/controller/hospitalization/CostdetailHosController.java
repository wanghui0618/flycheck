package com.dhcc.piccbid.web.controller.hospitalization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author DongHuan
 * @date 2019-01-29 15:04:53
 * @version V1.0
 */
@Controller
public class CostdetailHosController {

	@RequestMapping("/hospitalization/costdetailHos")
	public String costdetailHos() {
		return "hospitalization/costdetailHos";
	}
	
	@RequestMapping("/hospitalizationcheck/hospitalizationcheck")
	public String hospitalizationcheck() {
		return "hospitalization_check/hospitalizationCheck";
	}

	@RequestMapping("/hospitalizationcheck/hospitalizationcheckinfo")
	public String hospitalizationcheckinfo(){
		return "hospitalization_check/hospitalizationCheckInfo";
	}
}
