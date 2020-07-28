package com.dhcc.piccbid.web.controller.flyMedicalInhos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-31 11:40:47
 * @version V1.0
 */
@Controller
public class FlyMedicalInhosController {

	@RequestMapping("/flyMedicalInhos/flyMedicalInhos")
	public String flyMedicalInhos() {
		return "flyMedicalInhos/flyMedicalInhos";
	}

	@RequestMapping("/flyMedicalInhos/statisticsMedicalInsurance")
	public String statisticsMedicalInsurance() {
		return "flyMedicalInhos/statisticsMedicalInsurance";
	}

}
