package com.dhcc.piccbid.web.controller.flyTreatmentFeeCount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zfm
 * @date 2019-10-17 22:02:46
 * @version V1.0
 */
@Controller
public class FlyTreatmentFeeCountController {

	@RequestMapping("/flyTreatmentFeeCount/flyTreatmentFeeCount")
	public String flyTreatmentFeeCount() {
		return "flyTreatmentFeeCount/flyTreatmentFeeCount";
	}

    @RequestMapping("/flyTreatmentFeeCount/ultrasoundFeeCount")
    public String ultrasoundFeeCount() {
        return "flyTreatmentFeeCount/ultrasoundFeeCount";
    }

}
